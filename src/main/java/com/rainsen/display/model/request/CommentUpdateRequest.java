package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentUpdateRequest {

    @NotNull
    private Long id;
    @NotNull
    private Long projectId;
    @NotNull
    private Long userId;
    @Size(min = 3, max = 100)
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentUpdateRequest{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
    }
}
