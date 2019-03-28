package io.github.mykhailokostromin.springbootembeddedmongo.controller;

import io.github.mykhailokostromin.springbootembeddedmongo.dao.entity.User;

import java.util.Collection;

public interface UserController {

    Collection<User> getUsers(String name);
}
