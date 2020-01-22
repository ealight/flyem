package com.ddm.flyem.repository.impl;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.repository.MailTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;

@Repository
public class MailTokenRepositoryImpl implements MailTokenRepository {
    private static final String SAVE_TOKEN_QUERY = "INSERT INTO `email_tokens` (`user_id`, `token`) VALUES (?, ?)";
    private static final String DELETE_TOKEN_QUERY = "DELETE FROM `email_tokens` WHERE `user_id` = ?";
    private static final String GET_USER_ID_BY_TOKEN = "SELECT `user_id` FROM `email_tokens` WHERE `token` = ?";

    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveTokenForUser(User user, String token) {
        entityManager.createNativeQuery(SAVE_TOKEN_QUERY)
                .setParameter(1, user.getId())
                .setParameter(2, token)
                .executeUpdate();
    }

    @Override
    public void deleteTokenForUser(User user) {
        entityManager.createNativeQuery(DELETE_TOKEN_QUERY)
                .setParameter(1, user.getId())
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByToken(String token) {
        Query query = entityManager.createNativeQuery(GET_USER_ID_BY_TOKEN)
                .setParameter(1, token);

        Long userId = CollectionUtils.isEmpty(query.getResultList()) ? null : ((BigInteger) query.getResultList().get(0)).longValue();

        if(userId == null) {
            return null;
        }

        return entityManager.find(User.class, userId);
    }
}
