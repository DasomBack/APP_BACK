package com._thefull.dasom_app_demo.domain.promotion.openai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String role;
    private String content;
}
