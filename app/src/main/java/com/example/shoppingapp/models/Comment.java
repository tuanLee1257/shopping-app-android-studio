package com.example.shoppingapp.models;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String comment;
    private String commentDate;
    private User userComment;

    public Comment(Long id, String comment, String commentDate, User userComment) {
        this.id = id;
        this.comment = comment;
        this.commentDate = commentDate;
        this.userComment = userComment;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(User userComment) {
        this.userComment = userComment;
    }
}
