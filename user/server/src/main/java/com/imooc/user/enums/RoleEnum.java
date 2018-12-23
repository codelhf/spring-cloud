package com.imooc.user.enums;

import lombok.Getter;

@Getter
public enum  RoleEnum {

    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;

    private Integer code;
    private String desc;

    RoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
