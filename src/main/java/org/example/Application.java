package org.example;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;

public class Application {

    public Javalin app;

    public Application() {
        app = Javalin
                .create()
                .routes(() -> {
                    get("/store-session", (ctx) -> {
                        ctx.sessionAttribute("test", "tast");
                    });
                    get("/read-session", (ctx) -> {
                        String value = ctx.sessionAttribute("test");
                        if (value == null) {
                            ctx.result("empty");
                            ctx.status(404);
                        } else {
                            ctx.result(value);
                        }
                    });
                });
    }
}
