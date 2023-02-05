package com.pub.course.controller;

import com.pub.course.dto.KelasMapelDto;
import com.pub.course.model.Kelas;
import com.pub.course.model.KelasMapel;
import com.pub.course.model.Mapel;
import com.pub.course.repository.KelasMapelRepository;
import com.pub.course.repository.KelasRepository;
import com.pub.course.repository.MapelRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kelas_mapel")
public class KelasMapelController {

    @Autowired
    MapelRepository mapelRepository;

    @Autowired
    KelasRepository kelasRepository;
    
    @Autowired
    KelasMapelRepository kelasMapelRepository;

    @GetMapping("/find-all")
    public Object findAll() {
        return kelasMapelRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody KelasMapelDto kelasMapelDto) {
        // check mapel
        Mapel mapel = mapelRepository.findById(kelasMapelDto.getMapelId()).orElse(null);
        if (mapel == null) {
            return ResponseEntity.badRequest().body("id mapel tidak ditemukan!");
        }

        // check kelas
        Kelas kelas = kelasRepository.findById(kelasMapelDto.getKelasId()).orElse(null);
        if (kelas == null) {
            return ResponseEntity.badRequest().body("id kelas tidak ditemukan!");
        }

        // convert to siswa model
        KelasMapel kelasMapel = new KelasMapel();
        kelasMapel.setMapel(mapel);;
        kelasMapel.setKelas(kelas);;

        // save and return
        return ResponseEntity.ok(kelasMapelRepository.save(kelasMapel));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody KelasMapelDto kelasMapelDto) {
        // check mapel
        Mapel mapel = mapelRepository.findById(kelasMapelDto.getMapelId()).orElse(null);
        if (mapel == null) {
            return ResponseEntity.badRequest().body("id mapel tidak ditemukan!");
        }

        // check kelas
        Kelas kelas = kelasRepository.findById(kelasMapelDto.getKelasId()).orElse(null);
        if (kelas == null) {
            return ResponseEntity.badRequest().body("id kelas tidak ditemukan!");
        }

        // convert to siswa model
        KelasMapel kelasMapel = new KelasMapel();
        kelasMapel.setMapel(mapel);;
        kelasMapel.setKelas(kelas);;

        // save and return
        return ResponseEntity.ok(kelasMapelRepository.save(kelasMapel));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        // check if id exist
        KelasMapel kelasMapel = kelasMapelRepository.findById(id).orElse(null);
        if (kelasMapel == null) {
            return ResponseEntity.badRequest().body("id siswa tidak ditemukan!");
        }

        kelasMapelRepository.delete(kelasMapel);
        return null;
    }

}
