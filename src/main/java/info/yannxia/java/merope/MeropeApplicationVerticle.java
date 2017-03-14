package info.yannxia.java.merope;

import info.yannxia.java.merope.handler.routing.EchoRoutingHandler;
import info.yannxia.java.merope.handler.routing.LoginRoutingHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.web.Router;

public class MeropeApplicationVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        initData();

        router.route().path("/echo/:echoStr").handler(new EchoRoutingHandler(vertx));
        router.post().path("/login").handler(new LoginRoutingHandler(vertx));

        vertx.createHttpServer() // <4>
                .requestHandler(router::accept)
                .listen(8080);
    }

    /**
     * 1. init mysql
     */
    private void initData() {
        JsonObject mySQLClientConfig = new JsonObject()
                .put("host", "localhost")
                .put("username", "root")
                .put("password", "root")
                .put("database", "temp");
        MySQLClient.createShared(vertx, mySQLClientConfig);
    }

}
