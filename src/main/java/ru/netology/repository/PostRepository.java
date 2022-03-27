package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Stub
public class PostRepository {
    private Map<Long, Post> postBase = new ConcurrentHashMap<>();
    private long counter = 0;

    public List<Post> all() {
        return new ArrayList<>(postBase.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postBase.get(id));
    }

    public Post save(Post post) {
        long id = post.getId();
        if (id == 0) {
            id = counter;
            counter += 1;
        }
        return postBase.put(id, post);
    }

    public void removeById(long id) {
        postBase.remove(id);
    }
}
