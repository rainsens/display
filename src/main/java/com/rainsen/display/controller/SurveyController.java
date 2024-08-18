package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.model.entity.Survey;
import com.rainsen.display.model.request.SurveyCreateRequest;
import com.rainsen.display.model.request.SurveyUpdateRequest;
import com.rainsen.display.model.resource.SurveyResource;
import com.rainsen.display.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Operation(description = "List all created questionnaires.")
    @GetMapping("/index")
    public ApiResponse<Page<SurveyResource>> index(@RequestParam Integer page, Integer size) {
        Page<SurveyResource> surveys = surveyService.index(page, size);
        return ApiResponse.success(surveys);
    }

    @Operation(description = "Show a certain questionnaire.")
    @GetMapping("/show/{id}")
    public ApiResponse<SurveyResource> show(@PathVariable Long id) {
        return ApiResponse.success(surveyService.show(id));
    }

    @Operation(description = "Create a new questionnaire.")
    @PostMapping("/create")
    public ApiResponse<Void> create(@Valid @RequestBody SurveyCreateRequest request) {
        surveyService.create(request);
        return ApiResponse.success();
    }

    @Operation(description = "Update an existing questionnaire.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody SurveyUpdateRequest request) {
        surveyService.update(request);
        return ApiResponse.success();
    }

    @Operation(description = "Delete an existing questionnaire.")
    @PutMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        surveyService.delete(id);
        return ApiResponse.success();
    }

    @Operation(description = "Generate a Qrcode for a given questionnaire")
    @PostMapping("/{id}/qrcode")
    public ApiResponse<String> qrcode(@PathVariable Long id) {
        String url = surveyService.qrcode(id);
        return ApiResponse.success(url);
    }
}
