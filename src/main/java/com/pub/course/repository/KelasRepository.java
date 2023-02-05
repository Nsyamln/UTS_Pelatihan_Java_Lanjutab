package com.pub.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pub.course.model.Kelas;

public interface KelasRepository extends JpaRepository<Kelas, Integer>{
    
}
