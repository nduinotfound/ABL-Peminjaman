package com.randu.peminjaman.vo;

import java.util.Date;

public class Pengembalian {
    private Long id;
    private Date tanggal_dikembalikan;
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

    public Date getTanggal_dikembalikan() {
        return tanggal_dikembalikan;
    }

    public void setTanggal_dikembalikan(Date tanggal_dikembalikan) {
        this.tanggal_dikembalikan = tanggal_dikembalikan;
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
