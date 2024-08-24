package com.itxushen.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    // 404 Not found
    private int code;
    private String msg;
    private T data;

    public CommonResult(int code, String msg) {
        this(code, msg, null);
    }
}
