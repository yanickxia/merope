package info.yannxia.java.merope.handler.routing;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EchoRoutingHandler extends AbstractRoutingHandler implements Handler<RoutingContext> {

    public EchoRoutingHandler(Vertx vertx) {
        super(vertx);
    }

    @Override
    public void handle(RoutingContext routingContext) {
        final String key = routingContext.request().getParam("echoStr");
        routingContext.response().end(key);
    }
}
