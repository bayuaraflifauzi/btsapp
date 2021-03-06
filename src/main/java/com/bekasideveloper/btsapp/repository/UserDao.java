package com.bekasideveloper.btsapp.repository;

import com.bekasideveloper.btsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao
        extends JpaRepository<User, String> {
    User findByUserId(String userId);
}
