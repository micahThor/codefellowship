package com.micahthor.codefellowship.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByApplicationUserId(long applicationUserId);
}
