package org.example;

import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    @Test
    void GET_read_session_should_return_404_when_first_request() {
        JavalinTest.test(new Application().app, (server, client) -> {
            // when
            Response response = client.get("/read-session");

            // then
            assertEquals(404, response.code());
            assertEquals("empty", response.body().string());
        });
    }

    @Test
    void GET_read_session_should_return_200_when_stored_first() {
        JavalinTest.test(new Application().app, (server, client) -> {
            // given
            client.get("/store-session");

            // when
            Response response = client.get("/read-session");

            // then
            assertEquals(200, response.code());
            assertEquals("tast", response.body().string());
        });
    }
}