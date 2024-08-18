package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Link;
import com.rainsen.display.model.request.LinkCreateRequest;
import com.rainsen.display.model.request.LinkUpdateRequest;
import com.rainsen.display.model.resource.LinkResource;
import com.rainsen.display.service.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @Operation(description = "List all links of a project.")
    @GetMapping("/index")
    public ApiResponse<Page<LinkResource>> index(@RequestParam Integer page, Integer size) {
        Page<LinkResource> links = linkService.index(page, size);
        return ApiResponse.success(links);
    }

    @Operation(description = "Show a specified link of a project.")
    @GetMapping("/show/{id}")
    public ApiResponse<LinkResource> show(@PathVariable Long id) {
        return ApiResponse.success(linkService.show(id));
    }

    @Operation(description = "Add a new link record for a project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody LinkCreateRequest request) {
        linkService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an existing link of a project.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody LinkUpdateRequest request) {
        linkService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete a link record of a project.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        linkService.delete(id);
        return ApiResponse.success();
    }
}
