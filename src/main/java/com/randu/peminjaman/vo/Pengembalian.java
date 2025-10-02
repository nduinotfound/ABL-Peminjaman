package com.randu.peminjaman.vo;

import java.util.Date;

public class Pengembalian {
    private Long id;
    private Date tanggal_kembali;
    private String terlambat;
    private Double denda;
    private Long peminjamanId;

    public Pengembalian() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(Date tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    public String getTerlambat() {
        return terlambat;
    }

    public void setTerlambat(String terlambat) {
        this.terlambat = terlambat;
    }

    public Double getDenda() {
        return denda;
    }

    public void setDenda(Double denda) {
        this.denda = denda;
    }

    public Long getPeminjamanId() {
        return peminjamanId;
    }

    public void setPeminjamanId(Long peminjamanId) {
        this.peminjamanId = peminjamanId;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }   

}
