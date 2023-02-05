package com.pub.course.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="kelas")
public class Kelas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nama_kelas", nullable = false)
    private String namaKelas;


    @Column(name="jurusan",nullable = false)
    private String jurusan;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_guru_id", referencedColumnName = "id")
    private Guru guru;
}
