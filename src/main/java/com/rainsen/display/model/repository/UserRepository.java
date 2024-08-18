package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(@NonNull Long id);

    User findByEmail(String email);

    User findByUsernameOrEmail(String username, String email);
}
