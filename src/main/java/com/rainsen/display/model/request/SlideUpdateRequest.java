package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SlideUpdateRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long projectId;

    @Size(min = 10, max = 256)
    private String path;

    @Size(min = 3, max = 128)
    private String title;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SlideUpdateRequest{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
