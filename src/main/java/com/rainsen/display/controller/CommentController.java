package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.request.CommentCreateRequest;
import com.rainsen.display.model.request.CommentUpdateRequest;
import com.rainsen.display.model.resource.CommentResource;
import com.rainsen.display.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(description = "List all project comments.")
    @GetMapping("/index")
    public ApiResponse<Page<CommentResource>> index(@RequestParam Integer page, Integer size) {
        Page<CommentResource> comments = commentService.index(page, size);
        return ApiResponse.success(comments);
    }

    @Operation(description = "List all comments of a specific project.")
    @GetMapping("/project/{id}")
    public ApiResponse<Page<CommentResource>> project(@PathVariable Long id, @RequestParam Integer page, Integer size) {
        Page<CommentResource> comments = commentService.project(id, page, size);
        return ApiResponse.success(comments);
    }

    @Operation(description = "Show a specified comment of a project.")
    @GetMapping("/show/{id}")
    public ApiResponse<CommentResource> show(@PathVariable Long id) {
        return ApiResponse.success(commentService.show(id));
    }

    @Operation(description = "Give a comment to certain project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody CommentCreateRequest request) {
        commentService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update a submitted project comment.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody CommentUpdateRequest request) {
        commentService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete a submitted project comment.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ApiResponse.success();
    }
}
