package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;

public class MemberCreateRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long projectId;

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
        return "MemberCreateRequest{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                '}';
    }
}
