package info.yannxia.java.merope.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

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


    protected Handler<AsyncResult<SQLConnection>> connHandler(Future future, Handler<SQLConnection> handler) {
        return conn -> {
            if (conn.succeeded()) {
                final SQLConnection connection = conn.result();
                handler.handle(connection);
            } else {
                future.fail(conn.cause());
            }
        };
    }

    protected Handler<AsyncResult<ResultSet>> resultHandler(Future future, Handler<ResultSet> handler) {
        return result -> {
            if (result.succeeded()) {
                final ResultSet resultSet = result.result();
                handler.handle(resultSet);
            } else {
                future.fail(result.cause());
            }
        };
    }

}
