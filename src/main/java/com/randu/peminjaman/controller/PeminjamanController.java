package com.randu.peminjaman.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.randu.peminjaman.model.Peminjaman;
import com.randu.peminjaman.service.PeminjamanService;
import com.randu.peminjaman.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {

    private static final Logger log = LoggerFactory.getLogger(PeminjamanController.class);

    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping
    public List<Peminjaman> getAllPeminjamans(){
        log.info("Request: Mengambil semua data peminjaman");
        return peminjamanService.gettAllPeminjamans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peminjaman> getPeminjamanById(@PathVariable Long id){
        log.info("Request: Mencari peminjaman ID: {}", id);
        Peminjaman peminjaman = peminjamanService.getPeminjamanById(id);
        return peminjaman != null ? ResponseEntity.ok(peminjaman) : ResponseEntity.notFound().build();
    }
    
    @GetMapping(path = "/{id}/detail")
    public ResponseEntity<ResponseTemplate> getPeminjamanWithBukuById(@PathVariable Long id) {
        log.info("Request: Mengambil detail peminjaman lengkap untuk ID: {}", id);
        ResponseTemplate vo = peminjamanService.getPeminjamanWithBukuById(id);
        return vo != null ? ResponseEntity.ok(vo) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Peminjaman createPeminjaman(@RequestBody Peminjaman peminjaman){
        log.info("Request: Membuat data peminjaman baru untuk Anggota: {} dan Buku: {}", 
                peminjaman.getAnggotaId(), peminjaman.getBukuId());
        return peminjamanService.createPeminjaman(peminjaman);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeminjaman(@PathVariable Long id){
        log.info("Request: Menghapus peminjaman ID: {}", id);
        peminjamanService.deletePeminjaman(id);
        return ResponseEntity.ok().build();
    }
}