package io.github.mykhailokostromin.springbootembeddedmongo;

import lombok.Data;

@Data
public class User {
    // org.springframework.data.annotation.Id isn't need
    private String id, name;
}
