package io.github.mykhailokostromin.springbootembeddedmongo.dao;

import io.github.mykhailokostromin.springbootembeddedmongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

// it needs for test Jackson2RepositoryPopulatorFactoryBean
public interface UserRepository extends MongoRepository<User, String> {
}
