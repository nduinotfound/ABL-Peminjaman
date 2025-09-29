package com.randu.peminjaman.vo;

import com.randu.peminjaman.model.Peminjaman;

public class ResponseTemplate {
    Peminjaman peminjaman;
    Buku buku;
    Anggota anggota;
    Pengembalian pengembalian;

    public ResponseTemplate() {
    }

    public ResponseTemplate(Peminjaman peminjaman, Buku buku, Anggota anggota, Pengembalian pengembalian) {
        this.peminjaman = peminjaman;
        this.buku = buku;
        this.anggota = anggota;
        this.pengembalian = pengembalian;
    }
    
    public Peminjaman getPeminjaman() {
        return peminjaman;
    }
    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }
    public Buku getBuku() {
        return buku;
    }
    public void setBuku(Buku buku) {
        this.buku = buku;
    }
    public Anggota getAnggota() {
        return anggota;
    }
    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }
    public Pengembalian getPengembalian() {
        return pengembalian;
    }
    public void setPengembalian(Pengembalian pengembalian) {
        this.pengembalian = pengembalian;
    }

    
}