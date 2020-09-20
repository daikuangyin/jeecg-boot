package org.jeecg.modules.recycle.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class ApiHandler {

    private HttpServletRequest request;

    private HttpServletResponse response;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }


    public Object getRequestAtt(String key) {
        return request.getAttribute(key);
    }

    public void setRequestAtt(String key, Object obj) {
        request.setAttribute(key, obj);
    }

    public String getRequestParameter(String name) {
        return request.getParameter(name);
    }

    public String getToken() {
        return request.getHeader(BaseConstants.HEADER_TOKEN_KEY);
    }
}
