package com.example.thigiuaki.sv;

import java.io.Serializable;
import java.util.Objects;

public class SinhVien implements Serializable {
    private String MaSV,HoTen;
    private String SoTC;

    public SinhVien() {
    }

    public SinhVien(String maSV, String hoTen, String soTC) {
        MaSV = maSV;
        HoTen = hoTen;
        SoTC = soTC;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSoTC() {
        return SoTC;
    }

    public void setSoTC(String soTC) {
        SoTC = soTC;
    }

    @Override
    public String toString() {
        return  MaSV + "-" +
                HoTen + "-" +
                SoTC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinhVien sinhVien = (SinhVien) o;
        return Objects.equals(MaSV, sinhVien.MaSV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaSV);
    }
}
