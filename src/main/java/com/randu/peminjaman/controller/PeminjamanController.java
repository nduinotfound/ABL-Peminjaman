package com.randu.peminjaman.controller;

import java.util.List;

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
    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping
    public List<Peminjaman> getAllPeminjamans(){
        return peminjamanService.gettAllPeminjamans();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Peminjaman> getPeminjamanById(@PathVariable Long id){
        Peminjaman peminjaman = peminjamanService.getPeminjamanById(id);
        return peminjaman !=null ? ResponseEntity.ok(peminjaman) : ResponseEntity.notFound().build();
    }
    
    @GetMapping(path = "/{id}/detail")
    public ResponseEntity<ResponseTemplate> getPeminjamanWithBukuById(@PathVariable Long id) {
        ResponseTemplate vo = peminjamanService.getPeminjamanWithBukuById(id);
        return vo != null ? ResponseEntity.ok(vo) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Peminjaman createPeminjaman(@RequestBody Peminjaman peminjaman){
        return peminjamanService.createPeminjaman(peminjaman);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeminjaman(@PathVariable Long id){
        peminjamanService.deletePeminjaman(id);
        return ResponseEntity.ok().build();
    }
}
