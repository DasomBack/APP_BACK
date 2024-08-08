package com._thefull.dasom_app_demo.domain.dasomLocation.service;

import com._thefull.dasom_app_demo.domain.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_app_demo.domain.dasomLocation.repository.DasomLocationRepository;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.domain.robot.domain.Robot;
import com._thefull.dasom_app_demo.domain.robot.repository.RobotRepository;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j @Transactional
@RequiredArgsConstructor
public class DasomLocationService {

    private final DasomLocationRepository dasomLocationRepository;
    private final RobotRepository robotRepository;

    public List<DasomLocationResponseDTO> findAllDasomLocationList(Store store) {
        Robot robot = robotRepository.findByStore(store)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "로봇을 찾을 수 없습니다"));
        List<DasomLocation> locationList = dasomLocationRepository.findAllByRobot(robot);

        return DasomLocationResponseDTO.toDTOList(locationList);

    }

    public DasomLocationResponseDTO findOneDasomLocation(Store store, Long locationId){
        Robot robot = robotRepository.findByStore(store)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "로봇을 찾을 수 없습니다"));
        DasomLocation dasomLocation = dasomLocationRepository.findById(locationId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        /* 유효하지 않은 사용자 검출 */
        if (!dasomLocation.getRobot().equals(robot))
            throw new AppException(ErrorCode.REQUEST_FORBIDDEN,"유효하지 않은 사용자입니다");
        return DasomLocationResponseDTO.of(dasomLocation);

    }

    public DasomLocationResponseDTO createDasomLocation(Store store, DasomLocationRequestDTO request) {
        Robot robot = robotRepository.findByStore(store)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "로봇을 찾을 수 없습니다"));

        DasomLocation newLocation = request.from(robot);
        DasomLocation savedNewLocation = dasomLocationRepository.save(newLocation);

        return DasomLocationResponseDTO.of(savedNewLocation);

    }

    public DasomLocationResponseDTO updateDasomLocation(LoginUser user,Store store, Long locationId, DasomLocationRequestDTO requestDTO){
        Robot robot = robotRepository.findByStore(store)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "로봇을 찾을 수 없습니다"));
        DasomLocation dasomLocation = dasomLocationRepository.findById(locationId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        /* 유효하지 않은 사용자 검출 */
        if (isValidUserForLocation(dasomLocation,user)){
            dasomLocation.update(requestDTO);
            DasomLocation updated = dasomLocationRepository.save(dasomLocation);

            return DasomLocationResponseDTO.of(updated);
        }
        else throw new AppException(ErrorCode.REQUEST_FORBIDDEN,"ID="+locationId+" 인 위치 설정에 대해 수정 권한이 없습니다");
    }

    public void deleteDasomLocation(LoginUser user, Store store, Long locationId) {
        Robot robot = robotRepository.findByStore(store)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "로봇을 찾을 수 없습니다"));
        DasomLocation dasomLocation = dasomLocationRepository.findById(locationId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        /* 유효하지 않은 사용자 검출 */
        if (isValidUserForLocation(dasomLocation,user ))
            dasomLocationRepository.deleteById(locationId);
        else throw new AppException(ErrorCode.REQUEST_FORBIDDEN,"ID="+locationId+" 인 위치 설정에 대해 삭제 권한이 없습니다");

    }

    public DasomLocationResponseDTO changeUse(LoginUser user, Store store, Long locationId, Boolean use) {
        Robot robot = robotRepository.findByStore(store)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ROBOT, "로봇을 찾을 수 없습니다"));
        DasomLocation dasomLocation = dasomLocationRepository.findById(locationId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_DASOM_LOCATION, "다솜 위치 설정 값을 찾을 수 없습니다"));

        /* 유효하지 않은 사용자 검출 */
        if(isValidUserForLocation(dasomLocation,user)){
            if (use==dasomLocation.getUse())
                throw new AppException(ErrorCode.ALREADY_SET,"이미 설정된 값입니다");

            dasomLocation.changeUse(use);
            DasomLocation saved = dasomLocationRepository.save(dasomLocation);

            return DasomLocationResponseDTO.of(saved);
        }else throw new AppException(ErrorCode.REQUEST_FORBIDDEN,"ID="+locationId+" 인 위치 설정에 대해 수정 권한이 없습니다");


    }

    private boolean isValidUserForLocation(DasomLocation location, LoginUser user){
        return location.getRobot().getStore().getId() == user.getStore().getId();

    }


}
