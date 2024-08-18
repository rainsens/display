package com.rainsen.display.model.request;

import jakarta.validation.constraints.Size;

public class SurveyCreateRequest {

    @Size(min = 3, max = 256)
    private String title;

    private boolean open;

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
        return "SurveyCreateRequest{" +
                "title='" + title + '\'' +
                ", open=" + open +
                '}';
    }
}
