package com.hikaru.common.utils;

import io.jsonwebtoken.Claims;

public class CheckResult {
    private int errorCode;
    private boolean success;
    private Claims claims;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public CheckResult() {

    }
}
