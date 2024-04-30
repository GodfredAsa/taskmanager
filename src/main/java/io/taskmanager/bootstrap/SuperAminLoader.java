package io.taskmanager.bootstrap;

import io.taskmanager.model.User;
import io.taskmanager.repository.UserRepository;
import io.taskmanager.util.RandomUUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;


import static io.taskmanager.constant.Authority.SUPER_ADMIN_AUTHORITIES;
import static io.taskmanager.enumeration.Role.SUPERADMIN;
import static io.taskmanager.service.impl.UserServiceImpl.encodePassword;

@Slf4j
@Component
public class SuperAminLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final String SUPER_ADMIN_PASSWORD = "super1234";

    @Autowired
    public SuperAminLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        saveSuperAmin();
    }

    private synchronized void saveSuperAmin(){
        log.info("=========== CREATING SUPER ADMIN ================");
        User superAdmin = buildUser();
        if(!userRepository.existsByEmail(superAdmin.getEmail())){
            System.out.println("=============== Login Credentials  ===================");
            userRepository.save(superAdmin);
            log.info(String.format("username: %s, password: %s ", superAdmin.getUsername(), SUPER_ADMIN_PASSWORD));
        }else{
            System.out.println("=============== Login Credentials  ===================");
            log.info(String.format("username: %s, password: %s ", superAdmin.getUsername(), SUPER_ADMIN_PASSWORD));
        }
    }

    private synchronized User buildUser(){
        return User.builder()
                .userId(new RandomUUIDGenerator().generateRandomUUID())
                .username("superadmin")
                .gender("MALE")
                .email("superadmin@admin.com")
                .password(encodePassword(SUPER_ADMIN_PASSWORD))
                .joinDate(new Date())
                .role(SUPERADMIN)
                .authorities(SUPER_ADMIN_AUTHORITIES)
                .build();
    }
}
