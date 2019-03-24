package io.github.mykhailokostromin.springbootembeddedmongo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collection;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/users")
    public Collection<DBObject> getUsers() {
        return mongoTemplate.findAll(DBObject.class, User.class.getSimpleName().toLowerCase());
    }

    /**
     * fill embedded mongo with some data
     */
    @Configuration
    @Profile("default")
    class DefaultPostConstruct {
        @PostConstruct
        public void setUp() {
            DBObject objectToSave = BasicDBObjectBuilder.start()
                    .add("name", "Vasya Pupkin")
                    .get();
            mongoTemplate.save(objectToSave, User.class.getSimpleName().toLowerCase());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
