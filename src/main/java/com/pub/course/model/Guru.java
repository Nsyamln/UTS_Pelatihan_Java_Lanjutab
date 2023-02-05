package com.pub.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guru")
public class Guru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nip", nullable = false, length = 12)
    private String nip;

    @Column(name = "nama_guru", nullable = false)
    private String namaGuru;

    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;

    @Column(name = "no_telepon", length = 15)
    private String noTelepon;
    
    // add foreign key
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_mapel_id", referencedColumnName = "id")
    private Mapel mapel;

}
