package com.tanpuh.kickshubservice.controller;

import com.tanpuh.kickshubservice.dto.request.ColorRequest;
import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import com.tanpuh.kickshubservice.dto.response.ColorResponse;
import com.tanpuh.kickshubservice.service.color.ColorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Color Controller")
public class ColorController {
    ColorService colorService;

    @GetMapping
    @Operation(summary = "get list of colors")
    public ApiResponse<List<ColorResponse>> getAll() {
        return ApiResponse.<List<ColorResponse>>builder()
                .message("Get list of colors successfully")
                .data(colorService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get color by id")
    public ApiResponse<ColorResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<ColorResponse>builder()
                .message("Get color successfully")
                .data(colorService.getById(id))
                .build();
    }

    @PostMapping
    @Operation(summary = "create new color")
    public ApiResponse<ColorResponse> create(@RequestBody @Valid ColorRequest request) {
        return ApiResponse.<ColorResponse>builder()
                .message("Create color successfully")
                .data(colorService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update color by id")
    public ApiResponse<ColorResponse> update(@PathVariable Integer id,
                                             @RequestBody @Valid ColorRequest request) {
        return ApiResponse.<ColorResponse>builder()
                .message("Update color successfully")
                .data(colorService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete color by id")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        colorService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete color successfully")
                .build();
    }
}
