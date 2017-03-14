package info.yannxia.java.merope.service;

import io.vertx.core.Vertx;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;

public abstract class AbstractService {

    protected Vertx vertx;

    public AbstractService(Vertx vertx) {
        this.vertx = vertx;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public AsyncSQLClient getAsyncSQLClient() {
        return MySQLClient.createShared(vertx, null);
    }
}
