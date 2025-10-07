package com.robertojavadev.minicrm.security;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class LoginAttemptService {
    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME_MINUTES = 15;

    private final Map<String, Integer> attemptsCache = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> lockCache = new ConcurrentHashMap<>();

    public void loginSucceeded(String key) {
        attemptsCache.remove(key);
        lockCache.remove(key);
    }

    public void loginFailed(String key) {
        int attempts = attemptsCache.getOrDefault(key, 0);
        attempts++;
        attemptsCache.put(key, attempts);
        if (attempts >= MAX_ATTEMPTS) {
            lockCache.put(key, LocalDateTime.now().plusMinutes(LOCK_TIME_MINUTES));
        }
    }

    public boolean isBlocked(String key) {
        LocalDateTime lockTime = lockCache.get(key);
        if (lockTime == null) return false;
        if (lockTime.isBefore(LocalDateTime.now())) {
            lockCache.remove(key);
            attemptsCache.remove(key);
            return false;
        }
        return true;
    }
}
