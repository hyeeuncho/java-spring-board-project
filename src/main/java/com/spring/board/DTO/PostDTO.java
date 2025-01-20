package com.spring.board.DTO;

public class PostDTO {
    private Long post_id;
    private String title;
    private String content;
    private String username;

    public void PostDto(Long post_id, String title, String content, String username) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}