package com.pub.course.controller;

import com.pub.course.dto.SiswaDto;
import com.pub.course.model.Kelas;
import com.pub.course.model.Siswa;
import com.pub.course.repository.KelasRepository;
import com.pub.course.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siswa")
public class SiswaController {

    @Autowired
    SiswaRepository siswaRepository;

    @Autowired
    KelasRepository kelasRepository;

    @GetMapping("/find-all")
    public Object findAll() {
        return siswaRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody SiswaDto siswaDto) {
        // check kelas
        Kelas kelas = kelasRepository.findById(siswaDto.getFkKelasId()).orElse(null);
        if (kelas == null) {
            return ResponseEntity.badRequest().body("id kelas tidak ditemukan!");
        }

        // convert to siswa model
        Siswa siswa = new Siswa();
        siswa.setNim(siswaDto.getNis());
        siswa.setNama(siswaDto.getNama());
        siswa.setAlamat(siswaDto.getAlamat());
        siswa.setUmur(siswaDto.getUmur());
        siswa.setKelas(kelas);

        // save and return
        return ResponseEntity.ok(siswaRepository.save(siswa));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody SiswaDto siswaDto) {
        // check siswa
        Siswa siswa = siswaRepository.findById(id).orElse(null);
        if (siswa == null) {
            return ResponseEntity.badRequest().body("id siswa tidak ditemukan!");
        }
        
        // check kelas
        Kelas kelas = kelasRepository.findById(id).orElse(null);
        if (kelas == null) {
            return ResponseEntity.badRequest().body("id kelas tidak ditemukan!");
        }

        siswa.setNim(siswaDto.getNis());
        siswa.setNama(siswaDto.getNama());
        siswa.setAlamat(siswaDto.getAlamat());
        siswa.setUmur(siswaDto.getUmur());
        siswa.setKelas(kelas);

        return siswaRepository.save(siswa);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        // check if id exist
        Siswa siswa = siswaRepository.findById(id).orElse(null);
        if (siswa == null) {
            return ResponseEntity.badRequest().body("id siswa tidak ditemukan!");
        }

        siswaRepository.delete(siswa);
        return null;
    }

}
