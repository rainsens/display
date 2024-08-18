package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ImageRepository extends JpaRepository<Image, Long> {

    boolean existsById(@NonNull Long id);

    void deleteAllByProject_Id(Long projectId);
}
