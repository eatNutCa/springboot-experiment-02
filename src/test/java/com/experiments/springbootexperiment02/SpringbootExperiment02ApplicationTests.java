package com.experiments.springbootexperiment02;

import com.experiments.springbootexperiment02.entity.User;
import com.experiments.springbootexperiment02.entity.Address;
import com.experiments.springbootexperiment02.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.UserTransaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootExperiment02ApplicationTests {
    @Autowired
    UserRepository userRepository=new UserRepository();
    @Test
    public void contextLoads() {
        User user1=new User("user1");
        User user2=new User("user2");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        Address address1=new Address("001");
        Address address2=new Address("002");
        userRepository.addAddress(address1, 1);
        userRepository.addAddress(address2, 2);
        userRepository.updateUser(1, "user01");
        userRepository.updateAddress(2, 1);
        userRepository.listAddresses(1);
        userRepository.removeAddress(2);
        userRepository.removeUser(2);
    }
}
