package com.alzakhar.user_service.scheduled_jobs;

import com.alzakhar.user_service.model.User;
import com.alzakhar.user_service.model.UserStatus;
import com.alzakhar.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckStatusJob {

    @Value("${online.status.expired.limit}")
    private long ONLINE_STATUS_EXPIRED_LIMIT;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "${update.status.cron.expression}")
    private void checkAndUpdateStatus() {
        List<User> onlineUserList = userService.getUsersByStatus(UserStatus.ONLINE);
        List<User> usersListToUpdate = onlineUserList.stream()
                .filter(user ->
                        (Timestamp.from(Instant.now()).getTime() - user.getStatusChangedTimestamp().getTime()) / (60 * 1000)
                                > ONLINE_STATUS_EXPIRED_LIMIT)
                .collect(Collectors.toList());
        usersListToUpdate.forEach(user -> userService.changeStatus(user, UserStatus.AWAY));
    }
}
