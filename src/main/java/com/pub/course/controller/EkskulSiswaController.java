package com.pub.course.controller;

import com.pub.course.dto.EkskulSiswaDto;
import com.pub.course.model.Ekskul;
import com.pub.course.model.EkskulSiswa;
import com.pub.course.model.Siswa;
import com.pub.course.repository.EkskulRepository;
import com.pub.course.repository.EkskulSiswaRepository;
import com.pub.course.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ekskul_siswa")
public class EkskulSiswaController{

    @Autowired
    SiswaRepository siswaRepository;

    @Autowired
    EkskulRepository ekskulRepository;
    
    @Autowired
    EkskulSiswaRepository ekskulSiswaRepository;

    @GetMapping("/find-all")
    public Object findAll() {
        return ekskulSiswaRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody EkskulSiswaDto ekskulSiswaDto) {
        // check siswa
        Siswa siswa = siswaRepository.findById(ekskulSiswaDto.getSiswaId()).orElse(null);
        if (siswa == null) {
            return ResponseEntity.badRequest().body("id siswa tidak ditemukan!");
        }

        // check ekskul
        Ekskul ekskul = ekskulRepository.findById(ekskulSiswaDto.getEkskulId()).orElse(null);
        if (ekskul == null) {
            return ResponseEntity.badRequest().body("id ekskul tidak ditemukan!");
        }

        // convert to siswa model
        EkskulSiswa ekskulSiswa = new EkskulSiswa();
        ekskulSiswa.setSiswa(siswa);;
        ekskulSiswa.setEkskul(ekskul);;

        // save and return
        return ResponseEntity.ok(ekskulSiswaRepository.save(ekskulSiswa));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody EkskulSiswaDto ekskulSiswaDto) {
        // check siswa
        Siswa siswa = siswaRepository.findById(ekskulSiswaDto.getSiswaId()).orElse(null);
        if (siswa == null) {
            return ResponseEntity.badRequest().body("id siswa tidak ditemukan!");
        }

        // check ekskul
        Ekskul ekskul = ekskulRepository.findById(ekskulSiswaDto.getEkskulId()).orElse(null);
        if (ekskul == null) {
            return ResponseEntity.badRequest().body("id ekskul tidak ditemukan!");
        }

        // convert to siswa model
        EkskulSiswa ekskulSiswa = new EkskulSiswa();
        ekskulSiswa.setSiswa(siswa);;
        ekskulSiswa.setEkskul(ekskul);;

        // save and return
        return ResponseEntity.ok(ekskulSiswaRepository.save(ekskulSiswa));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        // check if id exist
        EkskulSiswa ekskulSiswa = ekskulSiswaRepository.findById(id).orElse(null);
        if (ekskulSiswa == null) {
            return ResponseEntity.badRequest().body("id siswa tidak ditemukan!");
        }

        ekskulSiswaRepository.delete(ekskulSiswa);
        return null;
    }

}
