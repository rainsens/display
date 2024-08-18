package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LinkCreateRequest {

    @NotNull
    private Long projectId;
    @Size(min = 3, max = 128)
    private String title;
    @Size(min = 10, max = 256)
    private String link;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "LinkCreateRequest{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
