package com.ddm.flyem.repository;

import com.ddm.flyem.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

    User findFirstByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
