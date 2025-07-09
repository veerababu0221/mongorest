package com.example.mongorest.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.mongorest.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MongoTemplate mongoTemplate;

    public User createUser(User user) {
        return mongoTemplate.save(user);
    }

    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    public User getUserById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public User updateUser(String id, User updatedUser) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("name", updatedUser.getName())
                .set("email", updatedUser.getEmail())
                .set("age", updatedUser.getAge());

        mongoTemplate.updateFirst(query, update, User.class);
        return mongoTemplate.findById(id, User.class);
    }

    public void deleteUser(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
}
