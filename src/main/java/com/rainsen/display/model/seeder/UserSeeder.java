package com.rainsen.display.model.seeder;

import com.rainsen.display.model.entity.User;
import com.rainsen.display.model.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Component
public class UserSeeder {

    private final UserRepository userRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void seedUsers() {

        if (userRepository.count() > 0) return;

        Faker faker = new Faker();
        IntStream.range(0, 100).forEach(i -> {
            User user = new User();

            if (i == 0) {
                user.setUsername("admin");
                user.setEmail("admin@admin.com");
                user.setAdmin(true);
            } else {
                user.setUsername(faker.name().firstName());
                user.setEmail(faker.internet().emailAddress());
                user.setAdmin(false);
            }

            user.setPassword("4ihrQTZIKgAs2MvNr4cufg==");
            user.setTitle(faker.name().prefix() + " " + faker.name().fullName());
            user.setAddress(faker.australia().locations());
            user.setSocial("facebook");
            user.setInterest(faker.lorem().paragraph(1));

            userRepository.save(user);
        });
    }
}
