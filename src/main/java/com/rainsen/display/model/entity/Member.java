package com.rainsen.display.model.entity;

import com.rainsen.display.model.resource.MemberResource;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public void setUserId(Long userId) {
        this.user = new User();
        this.user.setId(userId);
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

    public MemberResource toResource() {
        MemberResource resource = new MemberResource();
        BeanUtils.copyProperties(this, resource);
        return resource;
    }
}
