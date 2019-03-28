package io.github.mykhailokostromin.springbootembeddedmongo.dao.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    // org.springframework.data.annotation.Id isn't need
    private String id;
    private String name;
}
