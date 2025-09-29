package com.randu.peminjaman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.randu.peminjaman.model.Peminjaman;
import com.randu.peminjaman.repository.PeminjamanRepository;
import com.randu.peminjaman.vo.ResponseTemplate;
import com.randu.peminjaman.vo.Buku;
import com.randu.peminjaman.vo.Anggota; 
import com.randu.peminjaman.vo.Pengembalian;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Peminjaman> gettAllPeminjamans(){
        return peminjamanRepository.findAll();
    }

    public Peminjaman getPeminjamanById(Long id){
        return peminjamanRepository.findById(id).orElse(null);
    }

    public Peminjaman createPeminjaman(Peminjaman peminjaman){
        return peminjamanRepository.save(peminjaman);
    }

    public void deletePeminjaman(Long id){
        peminjamanRepository.deleteById(id);
    }
    public ResponseTemplate getPeminjamanWithBukuById(Long id){
        Peminjaman peminjaman = getPeminjamanById(id);
        if (peminjaman == null) {
            return null;
        }

        Buku buku = null;
        Anggota anggota = null;
        Pengembalian pengembalian = null;
        try {
            buku = restTemplate.getForObject("http://localhost:8001/api/buku/" + peminjaman.getBukuId(), Buku.class);
        } catch (Exception e) {
            // remote service might be down; leave buku null
        }
        try {
            anggota = restTemplate.getForObject("http://localhost:8002/api/anggota/" + peminjaman.getAnggotaId(), Anggota.class);
        } catch (Exception e) {
            // leave anggota null
        }
        try {
            pengembalian = restTemplate.getForObject("http://localhost:8003/api/pengembalian/" + peminjaman.getPengembalianId(), Pengembalian.class);
        } catch (Exception e) {
            // leave pengembalian null
        }

        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setBuku(buku);
        vo.setAnggota(anggota);
        vo.setPengembalian(pengembalian);
        return vo;
    }
}
