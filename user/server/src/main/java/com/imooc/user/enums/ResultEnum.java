package com.imooc.user.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    LOGIN_FAIL(1,"登录失败"),
    ROLE_ERROR(2,"角色权限有误"),
    ;

    private Integer code;
    private String desc;

    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
