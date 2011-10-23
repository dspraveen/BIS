package com.bis.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {

    protected ModelAndView json(Object model, HttpServletResponse response) {
        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;
        try {
            jsonConverter.write(model, jsonMimeType, new ServletServerHttpResponse(response));
        } catch (HttpMessageNotWritableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
