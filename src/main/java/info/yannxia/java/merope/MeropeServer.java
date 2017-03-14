package info.yannxia.java.merope;

import io.vertx.core.Vertx;

public class MeropeServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        MeropeApplicationVerticle meropeApplicationVerticle = new MeropeApplicationVerticle();
        vertx.deployVerticle(meropeApplicationVerticle);
    }
}
