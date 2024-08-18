package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Material;
import com.rainsen.display.model.request.MaterialCreateRequest;
import com.rainsen.display.model.request.MaterialUpdateRequest;
import com.rainsen.display.model.resource.MaterialResource;
import com.rainsen.display.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Operation(description = "List all materials of a project.")
    @GetMapping("/index")
    public ApiResponse<Page<MaterialResource>> index(@RequestParam Integer page, Integer size) {
        Page<MaterialResource> materials = materialService.index(page, size);
        return ApiResponse.success(materials);
    }

    @Operation(description = "Show a specified material of a project.")
    @GetMapping("/show/{id}")
    public ApiResponse<MaterialResource> show(@PathVariable Long id) {
        return ApiResponse.success(materialService.show(id));
    }

    @Operation(description = "Add a material for a project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody MaterialCreateRequest request) {
        materialService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an existing material of a project.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody MaterialUpdateRequest request) {
        materialService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete an existing material of a project.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return ApiResponse.success();
    }
}
