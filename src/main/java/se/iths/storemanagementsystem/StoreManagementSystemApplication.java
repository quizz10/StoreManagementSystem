package se.iths.storemanagementsystem;

import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.iths.storemanagementsystem.entity.RoleEntity;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.repository.RoleRepository;
import se.iths.storemanagementsystem.repository.UserRepository;
import se.iths.storemanagementsystem.service.UserService;

@SpringBootApplication
public class StoreManagementSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(StoreManagementSystemApplication.class, args);
    }

}
