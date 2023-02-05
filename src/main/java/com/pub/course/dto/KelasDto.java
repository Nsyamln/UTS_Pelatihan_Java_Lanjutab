package com.pub.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelasDto {
    private String namaKelas;

    private String jurusan;
    private Integer fkGuruId;
}
