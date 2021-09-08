package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.UserService;
import utils.Util;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UserController extends Controller {

    private HttpExecutionContext ec;
    private UserService userService;

    @Inject
    public UserController(HttpExecutionContext ec, UserService userService) {
        this.userService = userService;
        this.ec = ec;
    }

    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return supplyAsync(() -> {
            if (json == null) {
                return badRequest(Util.createResponse("Expecting Json data", false));
            }

            Optional<User> studentOptional = userService.addUser(Json.fromJson(json, User.class));
            return studentOptional.map(student -> {
                JsonNode jsonObject = Json.toJson(student);
                return created(Util.createResponse(jsonObject, true));
            }).orElse(internalServerError(Util.createResponse("Could not create data.", false)));
        }, ec.current());
    }

    public CompletionStage<Result> sendBirthdayEmail(int numOfHoursBefore) {
        return supplyAsync(() -> {
            boolean status = userService.sendEmail(numOfHoursBefore);
            if (!status) {
                return notFound(Util.createResponse("Users not found", false));
            }
            return ok(Util.createResponse("successfully send email to the user", true));
        }, ec.current());
    }

    public CompletionStage<Result> listUsers() {
        return supplyAsync(() -> {
            Set<User> result = userService.getAllUsers();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(Util.createResponse(jsonData, true));
        }, ec.current());
    }

}
