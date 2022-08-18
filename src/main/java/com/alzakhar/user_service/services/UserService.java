package com.alzakhar.user_service.services;

import com.alzakhar.user_service.dto.ChangeStatusDto;
import com.alzakhar.user_service.exceptions.CommonException;
import com.alzakhar.user_service.model.User;
import com.alzakhar.user_service.model.UserStatus;
import com.alzakhar.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Long saveNewUser(User user) {
        userRepository.save(user);
        setStatusChangedTimestamp(user);
        return user.getId();
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else throw new CommonException("Could not find user with ID " + userId);
    }

    @Transactional
    public ChangeStatusDto changeStatus(ChangeStatusDto changeStatusRequest) {
        User user = getUserById(changeStatusRequest.getUserId());
        ChangeStatusDto changeStatusDto = new ChangeStatusDto();
        changeStatusDto.setUserId(changeStatusRequest.getUserId());
        changeStatusDto.setOldStatus(user.getStatus());
        changeStatusDto.setNewStatus(changeStatusRequest.getNewStatus());
        user.setStatus(changeStatusRequest.getNewStatus());
        setStatusChangedTimestamp(user);
        userRepository.save(user);
        return changeStatusDto;
    }

    public void changeStatus(User user, UserStatus status) {
        user.setStatus(status);
        setStatusChangedTimestamp(user);
        userRepository.save(user);
    }

    public List<User> getUsersByStatus(UserStatus status) {
        return userRepository.findUsersByStatus(status);
    }

    public boolean emailExists(String email) {
        return !userRepository.findUsersByEmail(email).isEmpty();
    }

    public boolean phoneNumberExists(String phoneNumber) {
        return !userRepository.findUsersByPhoneNumber(phoneNumber).isEmpty();
    }

    private void setStatusChangedTimestamp(User user) {
        user.setStatusChangedTimestamp(Timestamp.from(Instant.now()));
    }


}
