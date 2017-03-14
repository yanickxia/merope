package info.yannxia.java.merope.service;

import info.yannxia.java.merope.domain.User;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

import java.util.List;
import java.util.Optional;

public class UserService extends AbstractService {

    public UserService(Vertx vertx) {
        super(vertx);
    }
    
    public Future<Optional<User>> findUser(String username, String password) {
        Future<Optional<User>> future = Future.future();

        getAsyncSQLClient().getConnection(res -> {
            if (res.succeeded()) {
                SQLConnection connection = res.result();
                connection.queryWithParams("select * from user where username = ? and password = ?", new JsonArray().add(username).add(password), result -> {

                    if (result.succeeded()) {
                        ResultSet resultSet = result.result();
                        List<JsonObject> rows = resultSet.getRows();

                        if (rows.isEmpty()) {
                            future.complete(Optional.empty());
                        } else {
                            future.complete(Optional.of(new User(rows.get(0))));
                        }
                    }
                });
            }
        });

        return future;
    }

}
