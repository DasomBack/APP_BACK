package com._thefull.dasom_app_demo.domain.dasomLocation.controller;

import com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto.DasomLocationRequestDTO;
import com._thefull.dasom_app_demo.domain.dasomLocation.domain.dto.DasomLocationResponseDTO;
import com._thefull.dasom_app_demo.domain.dasomLocation.service.DasomLocationService;
import com._thefull.dasom_app_demo.global.auth.AuthUser;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
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

    @GetMapping("/all")
    public ResponseEntity<List<DasomLocationResponseDTO>> findAllDasomLocationList(@AuthUser LoginUser user){
        Store store = user.getStore();
        List<DasomLocationResponseDTO> responseList = dasomLocationService.findAllDasomLocationList(store);

        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping
    public ResponseEntity<DasomLocationResponseDTO> findDasomLocation(@AuthUser LoginUser user,
                                                                      @RequestParam(name = "id")Long id){
        Store store = user.getStore();
        DasomLocationResponseDTO response = dasomLocationService.findOneDasomLocation(store, id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<DasomLocationResponseDTO> createDasomLocation(@AuthUser LoginUser user,
                                                                        @RequestBody @Valid final DasomLocationRequestDTO request){

        Store store = user.getStore();

        DasomLocationResponseDTO response = dasomLocationService.createDasomLocation(store, request);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/use")
    public ResponseEntity<DasomLocationResponseDTO> changeWhetherUse(@AuthUser LoginUser user,
                                                                     @RequestParam(name = "id")Long id,
                                                                     @RequestParam(name = "use") Boolean use){

        Store store = user.getStore();
        DasomLocationResponseDTO response = dasomLocationService.changeUse(user, store, id, use);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<DasomLocationResponseDTO> updateDasomLocation(@AuthUser LoginUser user,
                                                                        @RequestParam(name = "id")Long id,
                                                                        @RequestBody @Valid final DasomLocationRequestDTO request){

        Store store = user.getStore();
        DasomLocationResponseDTO response = dasomLocationService.updateDasomLocation(user,store,id, request);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDasomLocation(@AuthUser LoginUser user,
                                                      @RequestParam(name = "id")Long id){
        Store store = user.getStore();
        dasomLocationService.deleteDasomLocation(user,store,id);

        return ResponseEntity.ok().body("카페봇 위치 설정이 성공적으로 삭제되었습니다");
    }


}
