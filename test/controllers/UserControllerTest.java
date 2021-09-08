package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class UserControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void givenUserPostData_whenCreatingUser_ThenShouldReturnCreatedUser() {
        User user = new User();
        LocalDateTime birthday1 = LocalDateTime.of(1980, 9, 9, 00, 00, 00);
        user.setFirstName("Martin");
        user.setLastName("Fowler");
        user.setDateOfBirth(birthday1);
        user.setEmail("test@gmail.com");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(user, JsonNode.class);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyJson(node)
                .uri("/");

        Result result = route(app, request);
        assertEquals(CREATED, result.status());
        assertTrue(result.contentType().isPresent());
        assertEquals("application/json", result.contentType().get());
    }

}
