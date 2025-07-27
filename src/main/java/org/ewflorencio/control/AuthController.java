package org.ewflorencio.control;


import org.ewflorencio.service.AuthService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthController extends HttpServlet {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean ok = authService.authenticate(username, password);

        if (ok) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Login successful");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Invalid credentials");
        }

    }
}

