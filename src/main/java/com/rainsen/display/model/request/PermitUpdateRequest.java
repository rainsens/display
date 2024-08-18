package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;

public class PermitUpdateRequest {

    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "PermitUpdateRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", projectId=" + projectId +
                '}';
    }
}
