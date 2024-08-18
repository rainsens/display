package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsById(@NonNull Long id);

    boolean existsByTitle(String title);
}
