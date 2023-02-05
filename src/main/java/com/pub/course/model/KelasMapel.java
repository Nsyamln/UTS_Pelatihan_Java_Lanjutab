package com.pub.course.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="kelas_mapel")
public class KelasMapel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mapel_id", referencedColumnName = "id")
    private Mapel mapel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelas_id", referencedColumnName = "id")
    private Kelas kelas;

}
