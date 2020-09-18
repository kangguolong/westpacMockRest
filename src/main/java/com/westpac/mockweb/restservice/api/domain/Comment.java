package com.westpac.mockweb.restservice.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;
}
