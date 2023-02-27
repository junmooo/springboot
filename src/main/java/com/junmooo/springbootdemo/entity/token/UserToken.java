package com.junmooo.springbootdemo.entity.token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToken {
    private String id;
    private String name;
    private String email;
    private String avatar;
}
