package com.pub.course.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ekskul_siswa")
public class EkskulSiswa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_id", referencedColumnName = "id")
    private Siswa siswa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ekskul_id", referencedColumnName = "id")
    private Ekskul ekskul;

}
