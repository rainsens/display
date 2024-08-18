package com.rainsen.display.controller;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.util.UploadUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Operation(description = "Upload a file including image, video, pdf, word, excel, ppt etc.")
    @PostMapping("/file")
    public ApiResponse<String> file(@RequestParam("file") MultipartFile uploadedFile) {
        String urlPath = UploadUtil.upload(uploadedFile);
        return ApiResponse.success(urlPath);
    }
}
