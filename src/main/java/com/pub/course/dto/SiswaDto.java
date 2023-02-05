package com.pub.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiswaDto {
    private String nis;
    private String nama;
    private String alamat;
    private Integer umur;
    private Integer fkKelasId;
}
