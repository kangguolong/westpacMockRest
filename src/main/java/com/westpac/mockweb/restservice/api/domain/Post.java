package com.westpac.mockweb.restservice.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
