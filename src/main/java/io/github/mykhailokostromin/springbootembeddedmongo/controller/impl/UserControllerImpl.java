package io.github.mykhailokostromin.springbootembeddedmongo.controller.impl;

import io.github.mykhailokostromin.springbootembeddedmongo.controller.UserController;
import io.github.mykhailokostromin.springbootembeddedmongo.dao.entity.User;
import io.github.mykhailokostromin.springbootembeddedmongo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @Override
    public Collection<User> getUsers(@RequestParam(required = false) String name) {
        return name == null ? userRepository.findAll() : userRepository.findByName(name);
    }
}
