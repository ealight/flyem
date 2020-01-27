package com.ddm.flyem.repository;

import com.ddm.flyem.dao.User;

public interface MailTokenRepository {
    void saveTokenForUser(User user, String token);
    void deleteTokenForUser(User user);
    User getUserByToken(String token);
}
