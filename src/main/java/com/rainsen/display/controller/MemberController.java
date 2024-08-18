package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Member;
import com.rainsen.display.model.request.MemberCreateRequest;
import com.rainsen.display.model.request.MemberUpdateRequest;
import com.rainsen.display.model.resource.MemberResource;
import com.rainsen.display.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(description = "List all related members of projects.")
    @GetMapping("/index")
    public ApiResponse<Page<MemberResource>> index(@RequestParam Integer page, Integer size) {
        Page<MemberResource> members = memberService.index(page, size);
        return ApiResponse.success(members);
    }

    @Operation(description = "Show a certain member of a project.")
    @GetMapping("/show/{id}")
    public ApiResponse<MemberResource> show(@PathVariable Long id) {
        return ApiResponse.success(memberService.show(id));
    }

    @Operation(description = "Add a member for an existing project.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody MemberCreateRequest request) {
        memberService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update a member record of a project.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody MemberUpdateRequest request) {
        memberService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete a member from a project.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ApiResponse.success();
    }
}
