package com.education.schoolautomation.controller;

import com.education.schoolautomation.mapper.BranchMapper;
import com.education.schoolautomation.request.BranchRequest;
import com.education.schoolautomation.response.BranchResponse;
import com.education.schoolautomation.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BranchController {

    private final BranchService service;
    private final BranchMapper branchMapper;


    @PostMapping
    @Transactional
    public BranchResponse create(@RequestBody BranchRequest request) {
        return branchMapper.toResponse(service.create(branchMapper.toDto(request)));
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "branchId") UUID branchId) {
        service.delete(branchId);
    }

    @PutMapping
    public BranchResponse update(@RequestParam(value = "branchId") UUID branchId, @RequestBody BranchRequest request) {
        return branchMapper.toResponse(service.update(branchId, branchMapper.toDto(request)));
    }

    @GetMapping
    public List<BranchResponse> getAll() {
        return branchMapper.toResponseList(service.getAll());
    }

}
