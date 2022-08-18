package com.alzakhar.user_service.repositories;

import com.alzakhar.user_service.model.User;
import com.alzakhar.user_service.model.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUsersByStatus(UserStatus status);

    List<User> findUsersByEmail(String email);

    List<User> findUsersByPhoneNumber(String phoneNumber);
}
