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

import com.pub.course.dto.MapelDto;
import com.pub.course.model.Mapel;
import com.pub.course.repository.MapelRepository;

@RestController
@RequestMapping("/mapel")
public class MapelController {
    
    @Autowired
    MapelRepository mapelRepository;


    @GetMapping("/find-all")
    public Object findAll() {
        return mapelRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody MapelDto mapelDto) {
        // convert to siswa model
        Mapel mapel = new Mapel();
        mapel.setNamaPelajaran(mapelDto.getNamaPelajaran());

        // save and return
        return ResponseEntity.ok(mapelRepository.save(mapel));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,
                         @RequestBody MapelDto mapelDto) {

        // check if id exist
        Mapel mapel = mapelRepository.findById(id).orElse(null);
        if (mapel == null) {
            return ResponseEntity.badRequest().body("id mapel tidak ditemukan!");
        }
        mapel.setNamaPelajaran(mapelDto.getNamaPelajaran());

        return ResponseEntity.ok(mapelRepository.save(mapel));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        // check if id exist
        Mapel mapel = mapelRepository.findById(id).orElse(null);
        if (mapel == null) {
            return ResponseEntity.badRequest().body("id mapel tidak ditemukan!");
        }
        // delete mapel by id
        mapelRepository.delete(mapel);
        return null;
    }

}
