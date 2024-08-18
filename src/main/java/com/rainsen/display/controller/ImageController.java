package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Image;
import com.rainsen.display.model.request.ImageCreateRequest;
import com.rainsen.display.model.request.ImageUpdateRequest;
import com.rainsen.display.model.resource.ImageResource;
import com.rainsen.display.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(description = "List all project images.")
    @GetMapping("/index")
    public ApiResponse<Page<ImageResource>> index(@RequestParam Integer page, Integer size) {
        Page<ImageResource> images = imageService.index(page, size);
        return ApiResponse.success(images);
    }

    @Operation(description = "Show a specified project image.")
    @GetMapping("/show/{id}")
    public ApiResponse<ImageResource> show(@PathVariable Long id) {
        return ApiResponse.success(imageService.show(id));
    }

    @Operation(description = "Add an image record to a project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody ImageCreateRequest request) {
        imageService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an image record of a project.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody ImageUpdateRequest request) {
        imageService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete an image record of a project.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        imageService.delete(id);
        return ApiResponse.success();
    }
}
