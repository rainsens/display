package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.request.ProjectCreateRequest;
import com.rainsen.display.model.request.ProjectUpdateRequest;
import com.rainsen.display.model.resource.ProjectResource;
import com.rainsen.display.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(description = "List all projects out.")
    @GetMapping("/index")
    public ApiResponse<Page<ProjectResource>> index(@RequestParam Integer page, @RequestParam Integer size) {
        Page<ProjectResource> projects = projectService.index(page, size);
        return ApiResponse.success(projects);
    }

    @Operation(description = "Show a specified project.")
    @GetMapping("/show/{id}")
    public ApiResponse<ProjectResource> show(@PathVariable Long id) {
        return ApiResponse.success(projectService.show(id));
    }

    @Operation(description = "Create an new project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody ProjectCreateRequest createRequest) {
        projectService.create(createRequest);
        return ApiResponse.success();
    }

    @Operation(description = "Update an existing project.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody ProjectUpdateRequest updateRequest) {
        projectService.update(updateRequest);
        return ApiResponse.success();
    }

    @Operation(description = "Delete an existing project.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ApiResponse.success();
    }

    @Operation(description = "Generate a QRcode for a given project.")
    @PostMapping("/{id}/qrcode")
    public ApiResponse<String> qrcode(@PathVariable Long id) {
        String url = projectService.qrcode(id);
        return ApiResponse.success(url);
    }
}
