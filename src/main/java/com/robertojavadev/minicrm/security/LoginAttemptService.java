package com.robertojavadev.minicrm.security;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class LoginAttemptService {
    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME_MINUTES = 3;

    private final Map<String, Integer> attemptsCache = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> lockCache = new ConcurrentHashMap<>();

    public void loginSucceeded(String username) {
        if (username == null) {
            return;
        }
        attemptsCache.remove(username);
        lockCache.remove(username);
    }

    public void loginFailed(String username) {
        if (username == null) {
            return;
        }

        if (isBlocked(username)) {
            return;
        }

        int attempts = attemptsCache.getOrDefault(username, 0) + 1;
        attemptsCache.put(username, attempts);
        if (attempts >= MAX_ATTEMPTS) {
            lockCache.put(username, LocalDateTime.now().plusMinutes(LOCK_TIME_MINUTES));
        }
    }

    public boolean isBlocked(String username) {
        LocalDateTime lockTime = lockCache.get(username);
        if (lockTime == null) {
            return false;
        }

        if (lockTime.isBefore(LocalDateTime.now())) {
            lockCache.remove(username);
            attemptsCache.remove(username);
            return false;
        }
        return true;
    }

    public int remainingAttempts(String username) {
        return Math.max(0, MAX_ATTEMPTS - attemptsCache.getOrDefault(username, 0));
    }

    public LocalDateTime getUnlockTime(String username) {
        return lockCache.get(username);
    }
}
