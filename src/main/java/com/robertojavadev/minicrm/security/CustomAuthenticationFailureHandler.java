package com.robertojavadev.minicrm.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        int remaining = loginAttemptService.remainingAttempts(username.toLowerCase());

        if (username != null && !username.isBlank()) {
            loginAttemptService.loginFailed(username.toLowerCase());

            if (loginAttemptService.isBlocked(username.toLowerCase())) {
                LocalDateTime unlockTime = loginAttemptService.getUnlockTime(username.toLowerCase());
                String unlockAt = unlockTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                request.getSession().setAttribute("loginErrorMessage",
                        "Konto tymczasowo zablokowane do " + unlockAt + ".");
                response.sendRedirect("/");
                return;
            }
        }

        request.getSession().setAttribute(
                "loginErrorMessage",
                "Niepoprawny login lub hasło. Pozostało prób: " + remaining);
        response.sendRedirect("/");
    }
}
