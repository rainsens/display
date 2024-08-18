package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Question;
import com.rainsen.display.model.request.QuestionCreateRequest;
import com.rainsen.display.model.request.QuestionUpdateRequest;
import com.rainsen.display.model.resource.QuestionResource;
import com.rainsen.display.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Operation(description = "List all questionnaire questions.")
    @GetMapping("/index")
    public ApiResponse<Page<QuestionResource>> index(@RequestParam Integer page, Integer size) {
        Page<QuestionResource> questions = questionService.index(page, size);
        return ApiResponse.success(questions);
    }

    @Operation(description = "List all questions for a specified questionnaire.")
    @GetMapping("/survey/{id}")
    public ApiResponse<List<QuestionResource>> survey(@PathVariable Long id) {
        List<QuestionResource> questions = questionService.survey(id);
        return ApiResponse.success(questions);
    }

    @Operation(description = "Show a specified questionnaire question.")
    @GetMapping("/show/{id}")
    public ApiResponse<QuestionResource> show(@PathVariable Long id) {
        return ApiResponse.success(questionService.show(id));
    }

    @Operation(description = "Add a new question to a specified questionnaire.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody QuestionCreateRequest request) {
        questionService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an existing question of a questionnaire.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody QuestionUpdateRequest request) {
        questionService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete a questionnaire question.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ApiResponse.success();
    }
}
