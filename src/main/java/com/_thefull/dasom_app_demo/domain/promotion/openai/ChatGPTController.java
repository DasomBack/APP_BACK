package com._thefull.dasom_app_demo.domain.promotion.openai;

import com._thefull.dasom_app_demo.domain.menu.domain.dto.MenuResponseDTO;
import com._thefull.dasom_app_demo.domain.menu.service.MenuService;
import com._thefull.dasom_app_demo.domain.promotion.domain.dto.ForMentOfMenuPromotionDTO;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import com._thefull.dasom_app_demo.domain.store.service.StoreService;
import com._thefull.dasom_app_demo.global.auth.AuthUser;
import com._thefull.dasom_app_demo.global.auth.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/openai")
@RequiredArgsConstructor
public class ChatGPTController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    private final MenuService menuService;
    private final StoreService storeService;

    @PostMapping("/testchat")
    public String chat(@RequestParam(name = "prompt")String prompt){
        ChatGPTRequestDTO req = new ChatGPTRequestDTO(model, prompt);
        ChatGPTResponseDTO res=template.postForObject(apiURL,req, ChatGPTResponseDTO.class);
        return res.getChoices().get(0).getMessage().getContent();
    }
    @PostMapping("/ment")
    public ResponseEntity<OpenAiResponseDTO> createAiMent(@AuthUser LoginUser user,
                                                          @RequestBody final ForMentOfMenuPromotionDTO dto){
        Store store = user.getStore();
        MenuResponseDTO menu = menuService.getMenuById(store,dto.getMenuId());
        String prompt = createPrompt(dto, menu.getName());
        ChatGPTRequestDTO req = new ChatGPTRequestDTO(model, prompt);
        ChatGPTResponseDTO res=template.postForObject(apiURL,req, ChatGPTResponseDTO.class);
        return ResponseEntity.ok().body(OpenAiResponseDTO.builder()
                .content(res.getChoices().get(0).getMessage().getContent()).build());

    }

    private String createPrompt(ForMentOfMenuPromotionDTO dto, String menuName){

        String prompt = "메뉴 할인 홍보 문구를 만들려고 합니다. 메뉴 이름은 "+menuName+"이고,"+dto.getDiscType() + "은 다음과 같고, 할인 값은 " + dto.getDiscVal() + "%입니다. " +
                "프로모션은 " + dto.getStartDate() + "부터 " + dto.getEndDate() + "까지 유효하며, " +
                "적용 시간은 " + dto.getStartTime() + "부터 " + dto.getEndTime() + "까지입니다. " +
                (dto.getIsAddDiscCond() ? "추가 조건: " + dto.getAddDiscCond() + " " : "") +
                (dto.getIsAddMenuDesc() ? "추가 메뉴 설명: " + dto.getAddMenuDesc() : "")+"할인 혜택의 주요 측면을 강조하는 2~3문장 홍보 메시지를 작성하세요. 숫자는 작성하지 말고 전부 한글로, 매우 자연스럽게 작성해주세요.";

        return prompt;
    }
}
