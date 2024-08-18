package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    boolean existsById(@NonNull Long id);

    void deleteAllByProject_Id(Long projectId);

    Page<Comment> findAllByProject_Id(Long projectId, Pageable pageable);
}
