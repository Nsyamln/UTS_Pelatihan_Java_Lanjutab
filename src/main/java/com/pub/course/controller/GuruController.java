package com.pub.course.controller;

import com.pub.course.dto.GuruDto;
import com.pub.course.model.Guru;
import com.pub.course.model.Mapel;
import com.pub.course.repository.GuruRepository;
import com.pub.course.repository.MapelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guru")
public class GuruController {

    @Autowired
    GuruRepository guruRepository;
    
    @Autowired
    MapelRepository mapelRepository;

    @GetMapping("/find-all")
    public Object findAll() {
        return guruRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody GuruDto guruDto) {
        // check mapel
        Mapel mapel = mapelRepository.findById(guruDto.getFkMapelId()).orElse(null);
        if (mapel == null) {
            return ResponseEntity.badRequest().body("id mapel tidak ditemukan!");
        }
        // input 
        Guru guru = new Guru();
        guru.setNip(guruDto.getNip());
        guru.setNamaGuru(guruDto.getNamaGuru());
        guru.setJenisKelamin(guruDto.getJenisKelamin());
        guru.setNoTelepon(guruDto.getNoTelepon());
        guru.setMapel(mapel);
        return guruRepository.save(guru);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody GuruDto guruDto) {
        // check guru
        Guru guru = guruRepository.findById(id).orElse(null);
        if (guru == null) {
            return ResponseEntity.badRequest().body("id guru tidak ditemukan!");
        }

        // check mapel
        Mapel mapel = mapelRepository.findById(guruDto.getFkMapelId()).orElse(null);
        if (mapel == null) {
            return ResponseEntity.badRequest().body("id mapel tidak ditemukan!");
        }

        guru.setNip(guruDto.getNip());
        guru.setNamaGuru(guruDto.getNamaGuru());
        guru.setJenisKelamin(guruDto.getJenisKelamin());
        guru.setNoTelepon(guruDto.getNoTelepon());
        guru.setMapel(mapel);
        return guruRepository.save(guru);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        Guru guru = guruRepository.findById(id).orElse(null);
        if (guru == null) {
            return ResponseEntity.badRequest().body("id guru tidak ditemukan!");
        }

        guruRepository.delete(guru);
        return null;
    }

}
