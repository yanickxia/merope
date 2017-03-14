package info.yannxia.java.merope.service;

import info.yannxia.java.merope.domain.User;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;

import java.util.Optional;

public class UserService extends AbstractService {

    public UserService(Vertx vertx) {
        super(vertx);
    }

    public Future<Optional<User>> findUser(String username, String password) {
        Future<Optional<User>> future = Future.future();

        getAsyncSQLClient().getConnection(connHandler(future, connection -> {
            connection.queryWithParams("select * from USER where username = ? and password = ?",
                    new JsonArray().add(username).add(password), resultHandler(future, result -> {
                        if (result.getNumRows() == 0) {
                            future.complete(Optional.empty());
                        } else {
                            future.complete(Optional.of(new User(result.getRows().get(0))));
                        }
                    }));
        }));

        return future;
    }

}
