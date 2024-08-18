package com.rainsen.display.model.entity;

import com.rainsen.display.model.resource.LinkResource;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
public class Link extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getProjectId() {
        return project != null ? project.getId() : null;
    }

    public void setProjectId(Long projectId) {
        this.project = new Project();
        this.project.setId(projectId);
    }

    public LinkResource toResource() {
        LinkResource resource = new LinkResource();
        BeanUtils.copyProperties(this, resource);
        return resource;
    }
}
