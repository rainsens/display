package com.rainsen.display.model.entity;

import com.rainsen.display.model.resource.SurveyResource;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public SurveyResource toResource() {
        SurveyResource resource = new SurveyResource();
        BeanUtils.copyProperties(this, resource);
        return resource;
    }
}
