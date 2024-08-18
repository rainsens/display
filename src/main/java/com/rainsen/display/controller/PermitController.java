package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Permit;
import com.rainsen.display.model.request.PermitCreateRequest;
import com.rainsen.display.model.request.PermitUpdateRequest;
import com.rainsen.display.model.resource.PermitResource;
import com.rainsen.display.service.PermitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permit")
public class PermitController {

    private final PermitService permitService;

    @Autowired
    public PermitController(PermitService permitService) {
        this.permitService = permitService;
    }

    @Operation(description = "List all access permissions of projects.")
    @GetMapping("/index")
    public ApiResponse<Page<PermitResource>> index(@RequestParam Integer page, Integer size) {
        Page<PermitResource> permits = permitService.index(page, size);
        return ApiResponse.success(permits);
    }

    @Operation(description = "Show a permission record of a project.")
    @GetMapping("/show/{id}")
    public ApiResponse<PermitResource> show(@PathVariable Long id) {
        return ApiResponse.success(permitService.show(id));
    }

    @Operation(description = "Add an access permission to a project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody PermitCreateRequest request) {
        permitService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an existing permission record of a project.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody PermitUpdateRequest request) {
        permitService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete a permission of a project.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        permitService.delete(id);
        return ApiResponse.success();
    }
}
