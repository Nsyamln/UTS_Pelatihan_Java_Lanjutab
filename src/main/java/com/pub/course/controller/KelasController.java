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

import com.pub.course.dto.KelasDto;
import com.pub.course.model.Guru;
import com.pub.course.model.Kelas;
import com.pub.course.repository.GuruRepository;
import com.pub.course.repository.KelasRepository;

@RestController
@RequestMapping("/kelas")
public class KelasController {

    @Autowired
    KelasRepository kelasRepository;
    
    @Autowired
    GuruRepository guruRepository;

    @GetMapping("/find-all")
    public Object findAll() {
        return kelasRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody KelasDto kelasDto) {
        // check guru
        Guru guru = guruRepository.findById(kelasDto.getFkGuruId()).orElse(null);
        if (guru == null) {
            return ResponseEntity.badRequest().body("id guru tidak ditemukan!");
        }
        // input 
        Kelas kelas = new Kelas();
        kelas.setNamaKelas(kelasDto.getNamaKelas());
        kelas.setJurusan(kelasDto.getJurusan());
        kelas.setGuru(guru);
        return kelasRepository.save(kelas);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody KelasDto kelasDto) {
        // check kelas
        Kelas kelas = kelasRepository.findById(id).orElse(null);
        if (kelas == null) {
            return ResponseEntity.badRequest().body("id kelas tidak ditemukan!");
        }

        // check guru
        Guru guru = guruRepository.findById(id).orElse(null);
        if (guru == null) {
            return ResponseEntity.badRequest().body("id guru tidak ditemukan!");
        }

        // input
        kelas.setNamaKelas(kelasDto.getNamaKelas());
        kelas.setJurusan(kelasDto.getJurusan());
        kelas.setGuru(guru);
        return kelasRepository.save(kelas);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        Kelas kelas = kelasRepository.findById(id).orElse(null);
        if (kelas == null) {
            return ResponseEntity.badRequest().body("id kelas tidak ditemukan!");
        }

        kelasRepository.delete(kelas);
        return null;
    }
}
