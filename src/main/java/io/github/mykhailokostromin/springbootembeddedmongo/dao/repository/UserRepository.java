package io.github.mykhailokostromin.springbootembeddedmongo.dao.repository;

import io.github.mykhailokostromin.springbootembeddedmongo.dao.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface UserRepository extends MongoRepository<User, String> {

    Collection<User> findByName(String name);
}
