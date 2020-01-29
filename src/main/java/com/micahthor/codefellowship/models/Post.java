package com.micahthor.codefellowship.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    ApplicationUser applicationUser;

    private String body;
    private String timestamp;

    public Post(ApplicationUser applicationUser, String body, LocalDateTime timestamp) {
        this.applicationUser = applicationUser;
        this.body = body;
        this.timestamp = timestamp.toString();
    }

    public Post() {

    }

    public long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getBody() {
        return body;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
