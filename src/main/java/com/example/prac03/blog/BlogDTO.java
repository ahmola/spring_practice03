package com.example.prac03.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlogDTO {

    private Long id;

    private String subject;

    private String body;

    private Long user_id;
}
