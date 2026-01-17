package com.randu.peminjaman.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(PeminjamanService.class);

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate;

    @jakarta.annotation.Resource
    private org.springframework.core.env.Environment env;

    public List<Peminjaman> gettAllPeminjamans(){
        return peminjamanRepository.findAll();
    }

    public Peminjaman getPeminjamanById(Long id){
        return peminjamanRepository.findById(id).orElse(null);
    }

    public Peminjaman createPeminjaman(Peminjaman peminjaman){
        log.info("Saving new peminjaman to database");
        Peminjaman savedPeminjaman = peminjamanRepository.save(peminjaman);
        
        // Notify via RabbitMQ
        try {
            String exchange = "notification.exchange";
            String routingKey = "notification.key";
            String message = "Peminjaman Baru: Buku ID " + savedPeminjaman.getBukuId() + " dipinjam oleh Anggota ID " + savedPeminjaman.getAnggotaId();
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            log.info("Notification sent to RabbitMQ for peminjaman ID: {}", savedPeminjaman.getId());
        } catch (Exception e) {
            log.error("Failed to send notification to RabbitMQ: {}", e.getMessage());
        }
        
        return savedPeminjaman;
    }

    public void deletePeminjaman(Long id){
        peminjamanRepository.deleteById(id);
    }

    public ResponseTemplate getPeminjamanWithBukuById(Long id){
        Peminjaman peminjaman = getPeminjamanById(id);
        if (peminjaman == null) {
            log.warn("Peminjaman dengan ID {} tidak ditemukan", id);
            return null;
        }

        Buku buku = null;
        Anggota anggota = null;
        Pengembalian pengembalian = null;

        try {
            log.info("Fetching data Buku dari Service Buku untuk ID: {}", peminjaman.getBukuId());
            buku = restTemplate.getForObject("http://localhost:8001/api/buku/" + peminjaman.getBukuId(), Buku.class);
        } catch (Exception e) {
            log.error("Gagal mengambil data Buku: {}", e.getMessage());
        }

        try {
            log.info("Fetching data Anggota dari Service Anggota untuk ID: {}", peminjaman.getAnggotaId());
            anggota = restTemplate.getForObject("http://localhost:8002/api/anggota/" + peminjaman.getAnggotaId(), Anggota.class);
        } catch (Exception e) {
            log.error("Gagal mengambil data Anggota: {}", e.getMessage());
        }

        try {
            log.info("Fetching data Pengembalian untuk ID: {}", peminjaman.getPengembalianId());
            pengembalian = restTemplate.getForObject("http://localhost:8003/api/pengembalian/" + peminjaman.getPengembalianId(), Pengembalian.class);
        } catch (Exception e) {
            log.error("Gagal mengambil data Pengembalian: {}", e.getMessage());
        }

        ResponseTemplate vo = new ResponseTemplate();
        vo.setPengembalian(pengembalian);
        vo.setPeminjaman(peminjaman);
        vo.setBuku(buku);
        vo.setAnggota(anggota);
        return vo;
    }
}