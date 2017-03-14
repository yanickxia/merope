package info.yannxia.java.merope.handler.routing;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginRoutingHandler extends AbstractRoutingHandler implements Handler<RoutingContext> {

    public LoginRoutingHandler(Vertx vertx) {
        super(vertx);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json");
        response.write("HI").end();
    }
}


