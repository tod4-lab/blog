package com.hikaru.common.result;


import com.hikaru.common.constance.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private Integer code;
    private T data;
    private String message;


    public static <T> R<T> successWithData(T data) {
        return new R<>(ResultCode.SUCCESS, data, null);
    }

    public static <T> R<T> successWithoutData() {
        return new R<>(ResultCode.SUCCESS, null, null);
    }

    public static <T> R<T> fail(String message) {
        return new R<>(ResultCode.ERROR, null, message);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return new R<>(code, null, message);
    }
}
