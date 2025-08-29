package com.example.fruithub.seeder;

import com.example.fruithub.entity.Status;
import com.example.fruithub.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
public class StatusSeeder {

    @Autowired
    private StatusRepository statusRepository;


    @PostConstruct
    public void seedStatus() {
        if (statusRepository.count() == 0) {
            Status activeStatus = Status.builder()

                    .name("Active")
                    .description("User is currently active")
                    .parentStatus(null)
                    .build();
            Status approvedStatus = Status.builder()

                    .name("Approved")
                    .description("User successfully approved and account is complete")
                    .parentStatus(null)
                    .build();

            Status pendingStatus = Status.builder()
                    .name("Pending")
                    .description("Account is temporarily locked due to multiple failed login attempts. Please try again in 3 hours")
                    .parentStatus(null)
                    .build();

            Status deactivatedStatus = Status.builder()
                    .name("Deactivated")
                    .description("User account has been temporarily deactivated")
                    .parentStatus(null)
                    .build();

            Status deletedStatus = Status.builder()
                    .name("Deleted")
                    .description("User profile has been permanently deleted")
                    .parentStatus(null)
                    .build();
            Status inActiveStatus = Status.builder()
                    .name("Inactive")
                    .description("User is inactive")
                    .parentStatus(null)
                    .build();

            statusRepository.save(activeStatus);
            statusRepository.save(approvedStatus);
            statusRepository.save(pendingStatus);
            statusRepository.save(deactivatedStatus);
            statusRepository.save(deletedStatus);
            statusRepository.save(inActiveStatus);


        }
    }
}


