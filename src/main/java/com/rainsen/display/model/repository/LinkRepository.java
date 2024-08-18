package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface LinkRepository extends JpaRepository<Link, Long> {

    boolean existsById(@NonNull Long id);

    void deleteAllByProject_Id(Long projectId);
}
