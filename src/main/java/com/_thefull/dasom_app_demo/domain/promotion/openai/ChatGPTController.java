package com._thefull.dasom_app_demo.domain.promotion.openai;

import com._thefull.dasom_app_demo.domain.menu.domain.Menu;
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
        Menu menuById = menuService.findMenuById(store, dto.getMenuId());
        String prompt = createPrompt(dto, menu.getName(), menuById);



        ChatGPTRequestDTO req = new ChatGPTRequestDTO(model, prompt);
        ChatGPTResponseDTO res=template.postForObject(apiURL,req, ChatGPTResponseDTO.class);
        return ResponseEntity.ok().body(OpenAiResponseDTO.builder()
                .content(res.getChoices().get(0).getMessage().getContent()).build());

    }

    private String createPrompt(ForMentOfMenuPromotionDTO dto, String menuName, Menu menu){
        String prompt = "카페에서 할인 홍보 멘트를 만들려고 합니다. 메뉴 이름은 "+menuName+"이고,"
                +dto.getDiscType() + "은 다음과 같고, 할인하는 값은 " + dto.getDiscVal() + dto.getDiscType()+"입니다. "
                + "할인기간은" + dto.getStartDate() + "부터 " + dto.getEndDate() + "까지 유효하며, "
                + "할인 시간은" + dto.getStartTime() + "부터 " + dto.getEndTime() + "까지입니다. "
                + (dto.getIsAddDiscCond() ? "추가 조건: " + dto.getAddDiscCond() + " " : "")
                + (dto.getIsAddMenuDesc() ? "추가 메뉴 설명: " + dto.getAddMenuDesc() : "")
                + "메뉴의 정가는 "+menu.getPrice()+"이고, 정가에서 얼마나 할인했는지 꼭 포함해서 설명해주세요."
                +"멘트에는 연도를 제외한 할인기간의 시작과 끝, 그리고 할인 시간을 꼭 포함해서 말하고 싶어요. 그리고 나머지 정보를 참고해서 300자의 홍보멘트를 만들어주세요. 로봇이 말할거야. 숫자는 숫자로, 나머지는 한글로, 매우 자연스럽게 작성해주세요.";

        /* 500자 버전 */
        // String prompt = "카페에서 할인 홍보 멘트를 만들려고 합니다. 메뉴 이름은 "+menuName+"이고,"+dto.getDiscType() + "은 다음과 같고, 할인 값은 " + dto.getDiscVal() + "%입니다. " + "할인기간은" + dto.getStartDate() + "부터 " + dto.getEndDate() + "까지 유효하며, " + "할인 시간은" + dto.getStartTime() + "부터 " + dto.getEndTime() + "까지입니다. " + (dto.getIsAddDiscCond() ? "추가 조건: " + dto.getAddDiscCond() + " " : "") + (dto.getIsAddMenuDesc() ? "추가 메뉴 설명: " + dto.getAddMenuDesc() : "")+"멘트에는 연도를 제외한 할인기간의 시작과 끝, 그리고 할인 시간을 꼭 포함해서 말하고 싶어요. 그리고 나머지 정보를 참고해서 500자의 홍보멘트를 만들어주세요. 로봇이 말할거야. 한글로, 매우 자연스럽게 작성해주세요.";

        return prompt;
    }
}
