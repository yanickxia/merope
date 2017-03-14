package info.yannxia.java.merope.domain;

import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;



    public User(JsonObject jsonObject) {
        User user = new User();
        user.password = jsonObject.getString("username");
    }
}
