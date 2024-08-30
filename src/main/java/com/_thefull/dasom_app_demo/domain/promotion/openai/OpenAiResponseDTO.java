package com._thefull.dasom_app_demo.domain.promotion.openai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class OpenAiResponseDTO {
    private String content;
}
