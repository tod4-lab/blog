package com.hikaru.serviceUtil.exception;


import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.constance.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthException extends RuntimeException{
    private Integer code = ResultCode.ERROR;
    private String msg = AuthConstance.MESSAGE_SYSTEM_ERROR;
}
