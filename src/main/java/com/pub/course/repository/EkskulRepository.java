package com.pub.course.repository;


import com.pub.course.model.Ekskul;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EkskulRepository extends JpaRepository<Ekskul, Integer> {
    
}
