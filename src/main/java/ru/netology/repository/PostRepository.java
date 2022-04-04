package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private Map<Long, Post> postBase = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    public List<Post> all() {
        return new ArrayList<>(postBase.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postBase.get(id));
    }

    synchronized public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(counter.getAndIncrement());
        }
        return postBase.put(post.getId(), post);
    }

    synchronized public void removeById(long id) {
        postBase.remove(id);
    }
}
