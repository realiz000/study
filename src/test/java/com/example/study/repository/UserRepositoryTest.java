package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-1333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser: " + newUser);
    }

    @Test
    public void read() {
        Optional<User> user = userRepository.findById(2L);
//        User user = userRepository.findById(2L);
        // findById가 Optional을 반환

        // user가 값이 있으면 user출력한다.
        user.ifPresent(selectUser-> {
            System.out.println("user : " + selectUser);
            System.out.println("email : " + selectUser.getEmail());
        });


    }


    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser-> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("updated method()");
            userRepository.save(selectUser);
        });


    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent()); // true

        user.ifPresent(selectUser-> {
            userRepository.delete(selectUser); // delete는 반환형이 없음.
        });


        Optional<User> deleteUser = userRepository.findById(3L);
        Assert.assertFalse(deleteUser.isPresent()); // false
    }

}