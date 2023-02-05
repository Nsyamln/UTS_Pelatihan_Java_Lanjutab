package com.pub.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pub.course.model.KelasMapel;

public interface KelasMapelRepository extends JpaRepository<KelasMapel,Integer>{
    
    // @Query("select gk.siswa from GuruKelas gk where gk.guru.id =:guruId")
    // public List<Siswa> findSiswaByGuru(Integer guruId);
}
