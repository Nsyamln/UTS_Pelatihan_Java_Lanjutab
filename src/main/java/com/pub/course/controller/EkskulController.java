package com.pub.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pub.course.dto.EkskulDto;
import com.pub.course.model.Ekskul;
import com.pub.course.repository.EkskulRepository;

@RestController
@RequestMapping("/ekskul")
public class EkskulController {

    @Autowired
    EkskulRepository ekskulRepository;

    @GetMapping("/find-all")
    public Object findAll() {
        return ekskulRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody EkskulDto ekskulDto) {
        Ekskul ekskul = new Ekskul();
        ekskul.setNamaEkskul(ekskulDto.getNamaEkskul());
        return ekskulRepository.save(ekskul);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody EkskulDto ekskulDto) {
        Ekskul ekskul = ekskulRepository.findById(id).orElse(null);
        if (ekskul == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }

        ekskul.setNamaEkskul(ekskulDto.getNamaEkskul());
        return ekskulRepository.save(ekskul);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        Ekskul ekskul = ekskulRepository.findById(id).orElse(null);
        if (ekskul == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }

        ekskulRepository.delete(ekskul);
        return null;
    }
    
}
