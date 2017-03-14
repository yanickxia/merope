package info.yannxia.java.merope.handler.routing;

import io.vertx.core.Vertx;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;

public abstract class AbstractRoutingHandler {

    protected Vertx vertx;

    public AbstractRoutingHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public AsyncSQLClient getAsyncSQLClient() {
        return MySQLClient.createShared(vertx, null);
    }
}
