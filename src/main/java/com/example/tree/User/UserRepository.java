package com.example.tree.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User, Long> {

    User findByLoginId(String loginId);
}
