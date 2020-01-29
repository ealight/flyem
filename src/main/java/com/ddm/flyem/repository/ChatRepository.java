package com.ddm.flyem.repository;

import com.ddm.flyem.dao.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findFirstChatByToken(String token);
}
