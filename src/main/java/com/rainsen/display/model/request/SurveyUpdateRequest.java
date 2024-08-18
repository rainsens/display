package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SurveyUpdateRequest {

    @NotNull
    private Long id;

    @Size(min = 3, max = 256)
    private String title;
    private boolean open;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "SurveyUpdateRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", open=" + open +
                '}';
    }
}
