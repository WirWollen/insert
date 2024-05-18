package com.parse.steam.controllers;

import com.parse.steam.dtos.MatrixDto;
import com.parse.steam.exceptions.ElementNotFoundException;
import com.parse.steam.services.MatrixService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://locathost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/matrix")
public class MatrixController {
    private final MatrixService service;

    @PostMapping("/save")
    public MatrixDto saveMatrix(@RequestBody MatrixDto dto) {
        return service.saveMatrix(dto);
    }

    @PostMapping("/save-all")
    public List<MatrixDto> saveAllMatrix(@RequestBody List<MatrixDto> dtoList) {
        return service.saveMatrixList(dtoList);
    }

    @PostMapping("/archive")
    public MatrixDto archiveMatrix(@RequestParam Long id) throws ElementNotFoundException {
        return service.archiveMatrix(id);
    }

    @PostMapping("/unarchive")
    public MatrixDto unarchiveMatrix(@RequestParam Long id) throws ElementNotFoundException {
        return service.unarchiveMatrix(id);
    }

    @PostMapping("/update")
    public MatrixDto updateMatrix(@RequestBody MatrixDto dto) throws ElementNotFoundException {
        return service.updateMatrix(dto);
    }

    @GetMapping("/find-by-id")
    public MatrixDto findMatrixById(@RequestParam Long id) throws ElementNotFoundException {
        return service.getMatrixById(id);
    }

    @GetMapping("/get-all")
    public List<MatrixDto> getAllMatrix() {
        return service.getAllMatrix();
    }
}
