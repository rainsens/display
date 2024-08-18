package com.rainsen.display.model.request;

import jakarta.validation.constraints.*;

public class ProjectUpdateRequest {

    @NotNull
    private Long id;

    @Size(min = 3, max = 128)
    private String initiator;

    @Size(min = 3, max = 128)
    private String title;

    @Size(max = 256)
    private String cover;

    private String detail;

    private boolean team;

    @Min(0) @Max(100)
    private Integer progress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isTeam() {
        return team;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "ProjectUpdateRequest{" +
                "id=" + id +
                ", initiator='" + initiator + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", detail='" + detail + '\'' +
                ", team=" + team +
                ", progress=" + progress +
                '}';
    }
}
