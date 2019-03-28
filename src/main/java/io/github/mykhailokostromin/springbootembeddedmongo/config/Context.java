package io.github.mykhailokostromin.springbootembeddedmongo.config;

import io.github.mykhailokostromin.springbootembeddedmongo.dao.entity.User;
import io.github.mykhailokostromin.springbootembeddedmongo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("default")
public class Context {

    @Autowired
    private UserRepository userRepository;

    /**
     * fill embedded mongo with some data
     */
    @PostConstruct
    public void setUp() {
        userRepository.save(new User().setName("John Smith"));
        userRepository.save(new User().setName("Cassandra"));
        userRepository.save(new User().setName("Rebeca"));
    }
}
