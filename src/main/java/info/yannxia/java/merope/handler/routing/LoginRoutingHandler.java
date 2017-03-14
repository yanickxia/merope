package info.yannxia.java.merope.handler.routing;

import info.yannxia.java.merope.domain.User;
import info.yannxia.java.merope.service.UserService;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class LoginRoutingHandler extends AbstractRoutingHandler implements Handler<RoutingContext> {

    private UserService userService;

    public LoginRoutingHandler(Vertx vertx) {
        super(vertx);
        userService = new UserService(vertx);
    }

    @Override
    public void handle(RoutingContext routingContext) {

        HttpServerRequest httpServerRequest = routingContext.request();
        String username = httpServerRequest.getParam("username");
        String password = httpServerRequest.getParam("password");
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);

        userService.findUser(username, password).setHandler(res -> {
            if (res.succeeded()) {
                Optional<User> userOptional = res.result();
                if (userOptional.isPresent()) {
                    response.write(userOptional.get().toString()).end();
                } else {
                    response.write("none").end();
                }
            } else {
                log.error(res.cause());
                response.write("error").end();
            }
        });
    }
}


