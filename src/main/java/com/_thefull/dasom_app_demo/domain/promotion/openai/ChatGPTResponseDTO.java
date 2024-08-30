package com._thefull.dasom_app_demo.domain.promotion.openai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTResponseDTO {
    private List<Choice> choices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice{
        private Integer index;
        private MessageDTO message;
    }
}
