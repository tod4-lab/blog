package com.hikaru.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hikaru.common.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class ResponseUtil {
    public static <T> void out(HttpServletResponse response, R<T> r) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        Gson gson = new Gson();
        String json = gson.toJson(r);
        response.getWriter().write(json);
    }
}
