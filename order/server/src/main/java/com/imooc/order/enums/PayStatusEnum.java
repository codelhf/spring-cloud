package com.imooc.order.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;
    private String desc;

    PayStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
