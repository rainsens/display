package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Slide;
import com.rainsen.display.model.request.SlideCreateRequest;
import com.rainsen.display.model.request.SlideUpdateRequest;
import com.rainsen.display.model.resource.SlideResource;
import com.rainsen.display.service.SlideService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slide")
public class SlideController {

    private final SlideService slideService;

    @Autowired
    public SlideController(SlideService slideService) {
        this.slideService = slideService;
    }

    @Operation(description = "List all slides of projects.")
    @GetMapping("/index")
    public ApiResponse<Page<SlideResource>> index(@RequestParam Integer page, Integer size) {
        Page<SlideResource> slides = slideService.index(page, size);
        return ApiResponse.success(slides);
    }

    @Operation(description = "Show a specified slide of a project.")
    @GetMapping("/show/{id}")
    public ApiResponse<SlideResource> show(@PathVariable Long id) {
        return ApiResponse.success(slideService.show(id));
    }

    @Operation(description = "Add a new image slide to a project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody SlideCreateRequest request) {
        slideService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an existing slide record of a project.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody SlideUpdateRequest request) {
        slideService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete an existing slide of a project.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        slideService.delete(id);
        return ApiResponse.success();
    }
}
