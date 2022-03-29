package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        doResponse(response, service::all);
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        doResponse(response, () -> service.getById(id));
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        doResponse(response, () -> service.save(post));
    }

    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
    }

    public <T> void doResponse(HttpServletResponse response, ResponseHandler<T> responseHandler) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var data = responseHandler.getData();
        response.getWriter().print(gson.toJson(data));
    }

    interface ResponseHandler<T> {
        T getData();
    }
}
