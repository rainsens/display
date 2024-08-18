package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.request.AnswerCreateRequest;
import com.rainsen.display.model.request.AnswerUpdateRequest;
import com.rainsen.display.model.resource.AnswerResource;
import com.rainsen.display.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Operation(description = "List all questionnaire answers by pagination.")
    @GetMapping("/index")
    public ApiResponse<Page<AnswerResource>> index(@RequestParam Integer page, Integer size) {
        Page<AnswerResource> answers = answerService.index(page, size);
        return ApiResponse.success(answers);
    }

    @Operation(description = "Show an specified questionnaire answer.")
    @GetMapping("/show/{id}")
    public ApiResponse<AnswerResource> show(@PathVariable Long id) {
        return ApiResponse.success(answerService.show(id));
    }

    @Operation(description = "Answer a questionnaire question.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody AnswerCreateRequest request) {
        answerService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an answered questionnaire question.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody AnswerUpdateRequest request) {
        answerService.update(request);
        return ApiResponse.success();
    }
}
