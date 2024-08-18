package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.filter.UserFilter;
import com.rainsen.display.model.entity.Comment;
import com.rainsen.display.model.repository.CommentRepository;
import com.rainsen.display.model.request.CommentCreateRequest;
import com.rainsen.display.model.request.CommentUpdateRequest;
import com.rainsen.display.model.resource.CommentResource;
import com.rainsen.display.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository repository;

    @Autowired
    public CommentServiceImp(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<CommentResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Comment> comments = repository.findAll(pageable);
        return comments.map(comment -> {
            CommentResource commentResource = comment.toResource();
            commentResource.setUser(comment.getUser().toResource());
            return commentResource;
        });
    }

    @Override
    public Page<CommentResource> project(Long projectId, Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Comment> comments = repository.findAllByProject_Id(projectId, pageable);
        return comments.map(comment -> {
            CommentResource commentResource = comment.toResource();
            commentResource.setUser(comment.getUser().toResource());
            return commentResource;
        });
    }

    @Override
    public CommentResource show(Long id) {
        Comment comment = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        CommentResource commentResource = comment.toResource();
        commentResource.setUser(comment.getUser().toResource());
        return commentResource;
    }

    @Override
    public void create(CommentCreateRequest info) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(info, comment);
        comment.setUserId(UserFilter.user().getId());
        repository.save(comment);
    }

    @Override
    public void update(CommentUpdateRequest info) {
        Comment comment = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        if (!comment.getUserId().equals(UserFilter.user().getId())) {
            throw new DisException(DisExceptionEnum.INVALID_OPERATION);
        }
        BeanUtils.copyProperties(info, comment);
        repository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DisException(DisExceptionEnum.RECORD_NOT_EXISTS);
        }
        repository.deleteById(id);
    }
}
