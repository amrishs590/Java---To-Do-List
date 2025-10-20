package com.app.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

    private static final SecureRandom random = new SecureRandom();

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        addSecurityHeaders(res);

        HttpSession session = req.getSession(true);
        ensureCsrfToken(session);

        String method = req.getMethod();
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
            String tokenFromRequest = req.getParameter("csrfToken");
            String tokenFromSession = (String) session.getAttribute("CSRF_TOKEN");
            if (tokenFromSession == null || tokenFromRequest == null || !tokenFromSession.equals(tokenFromRequest)) {
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                res.getWriter().write("CSRF validation failed");
                return;
            }
        }

        XSSRequestWrapper wrapped = new XSSRequestWrapper(req);
        chain.doFilter(wrapped, response);
    }

    public void destroy() {}

    private void addSecurityHeaders(HttpServletResponse res) {
        res.setHeader("X-Content-Type-Options", "nosniff");
        res.setHeader("X-Frame-Options", "DENY");
        res.setHeader("Referrer-Policy", "no-referrer");
        res.setHeader("Permissions-Policy", "geolocation=(), microphone=()");
        res.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        String csp = "default-src 'self'; " +
                "script-src 'self'; " +
                "style-src 'self' 'unsafe-inline'; " +
                "object-src 'none'; " +
                "frame-ancestors 'none';";
        res.setHeader("Content-Security-Policy", csp);
    }

    private void ensureCsrfToken(HttpSession session) {
        if (session.getAttribute("CSRF_TOKEN") == null) {
            byte[] bytes = new byte[32];
            random.nextBytes(bytes);
            String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
            session.setAttribute("CSRF_TOKEN", token);
        }
    }
}
