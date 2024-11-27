package com.metacoding.authblog._core.filter;

import com.metacoding.authblog.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        HttpServletResponse resp = (HttpServletResponse) response;

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login-form");
        } else {
            chain.doFilter(request, response);
        }

    }
}
