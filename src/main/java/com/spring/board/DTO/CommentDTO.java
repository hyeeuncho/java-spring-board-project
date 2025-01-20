package com.spring.board.DTO;

public class CommentDTO {
    private Long comment_id;
    private String content;
    private String username;
    private Long postId;

    public void CommentDto(Long comment_id, String content, String username, Long postId) {
        this.comment_id = comment_id;
        this.content = content;
        this.username = username;
        this.postId = postId;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}