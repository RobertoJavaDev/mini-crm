package com.robertojavadev.minicrm.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String username = request.getParameter("username");

        if (username != null && !username.isBlank()) {
            loginAttemptService.loginFailed(username.toLowerCase());

            if (loginAttemptService.isBlocked(username.toLowerCase())) {
                request.getSession().setAttribute("loginErrorMessage",
                        "Konto tymczasowo zablokowane po zbyt wielu nieudanych próbach. Spróbuj ponownie za 3 minuty.");
                response.sendRedirect("/");
                return;
            }
        }

        request.getSession().setAttribute("loginErrorMessage", "Niepoprawny login lub hasło");
        response.sendRedirect("/");
    }
}
