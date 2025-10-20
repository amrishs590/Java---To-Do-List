package com.app.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.stream.Collectors;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return sanitize(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) return null;
        return Arrays.stream(values).map(this::sanitize).toArray(String[]::new);
    }

    private String sanitize(String value) {
        if (value == null) return null;
        String result = value;
        result = result.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        result = result.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        result = result.replaceAll("'", "&#39;").replaceAll("\"", "&quot;");
        result = result.replaceAll("(?i)eval\\((.*)\\)", "");
        result = result.replaceAll("(?i)javascript:", "");
        result = result.replaceAll("(?i)script", "");
        return result;
    }
}
