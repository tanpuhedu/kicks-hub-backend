package com.tanpuh.kickshubservice.service.size;

import com.tanpuh.kickshubservice.dto.request.SizeRequest;
import com.tanpuh.kickshubservice.dto.response.SizeResponse;

import java.util.List;

public interface SizeService {
    List<SizeResponse> getAll();
    SizeResponse getById(Integer id);
    SizeResponse create(SizeRequest request);
    SizeResponse update(Integer id, SizeRequest request);
    void delete(Integer id);
}
