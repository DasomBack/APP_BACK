package com._thefull.dasom_app_demo.dasomLocation.controller;

import com._thefull.dasom_app_demo.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_app_demo.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_app_demo.dasomLocation.service.DasomLocationService;
import com._thefull.dasom_app_demo.global.exception.AppException;
import com._thefull.dasom_app_demo.global.exception.ErrorCode;
import com._thefull.dasom_app_demo.store.domain.Store;
import com._thefull.dasom_app_demo.store.repository.StoreRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settings/dasom-locations")
public class DasomLocationController {

    private final DasomLocationService dasomLocationService;

    /* store 임시 찾기 */
    private final StoreRepository storeRepository;

    public Store findTempStore(){
        return storeRepository.findById(1l).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_STORE,"매장을 찾을 수 없습니다"));
    }

    /**************************************************************/

    @GetMapping("/all")
    public ResponseEntity<List<DasomLocationResponseDTO>> findAllDasomLocationList(){
        Store tempStore = findTempStore();

        List<DasomLocationResponseDTO> responseList = dasomLocationService.findAllDasomLocationList(tempStore);

        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping
    public ResponseEntity<DasomLocationResponseDTO> findDasomLocation(@RequestParam(name = "id")Long id){
        Store tempStore = findTempStore();
        DasomLocationResponseDTO response = dasomLocationService.findOneDasomLocation(tempStore, id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<DasomLocationResponseDTO> createDasomLocation(@RequestBody @Valid final DasomLocationRequestDTO request){
        Store tempStore = findTempStore();

        System.out.println("dasomLocationController.createDasomLocation");
        System.out.println(request.getLocation());

        DasomLocationResponseDTO response = dasomLocationService.createDasomLocation(tempStore, request);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/use")
    public ResponseEntity<DasomLocationResponseDTO> changeWhetherUse(@RequestParam(name = "id")Long id,
                                                                     @RequestParam(name = "use") Boolean use){
        Store tempStore = findTempStore();
        DasomLocationResponseDTO response = dasomLocationService.changeUse(tempStore, id, use);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<DasomLocationResponseDTO> updateDasomLocation(@RequestParam(name = "id")Long id,
                                                                        @RequestBody @Valid final DasomLocationRequestDTO request){
        Store tempStore = findTempStore();
        DasomLocationResponseDTO response = dasomLocationService.updateDasomLocation(tempStore,id, request);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDasomLocation(@RequestParam(name = "id")Long id){
        Store tempStore = findTempStore();
        dasomLocationService.deleteDasomLocation(tempStore,id);

        return ResponseEntity.ok().body("카페봇 위치 설정이 성공적으로 삭제되었습니다");
    }



}
