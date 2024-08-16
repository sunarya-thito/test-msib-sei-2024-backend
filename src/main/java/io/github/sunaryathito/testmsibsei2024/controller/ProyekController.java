package io.github.sunaryathito.testmsibsei2024.controller;

import io.github.sunaryathito.testmsibsei2024.pojo.Lokasi;
import io.github.sunaryathito.testmsibsei2024.pojo.Proyek;
import io.github.sunaryathito.testmsibsei2024.request.ProyekAddRequest;
import io.github.sunaryathito.testmsibsei2024.request.ProyekUpdateRequest;
import io.github.sunaryathito.testmsibsei2024.response.BasicResponse;
import io.github.sunaryathito.testmsibsei2024.response.ProyekAddResponse;
import io.github.sunaryathito.testmsibsei2024.response.ProyekListResponse;
import io.github.sunaryathito.testmsibsei2024.service.LokasiService;
import io.github.sunaryathito.testmsibsei2024.service.ProyekLokasiService;
import io.github.sunaryathito.testmsibsei2024.service.ProyekService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("proyek")
@CrossOrigin
public class ProyekController {

    private final Logger logger = LoggerFactory.getLogger(ProyekController.class);

    private final ProyekService proyekService;
    private final LokasiService lokasiService;
    private final ProyekLokasiService proyekLokasiService;

    public ProyekController(ProyekService proyekService, LokasiService lokasiService, ProyekLokasiService proyekLokasiService) {
        this.proyekService = proyekService;
        this.lokasiService = lokasiService;
        this.proyekLokasiService = proyekLokasiService;
    }

    @DeleteMapping
    public ResponseEntity<BasicResponse> deleteProyek(@RequestParam int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder().message("ID proyek tidak valid").build()
            );
        }
        try {
            proyekService.deleteProyek(id);
            return ResponseEntity.ok(
                    BasicResponse.builder().message("Proyek berhasil dihapus").build()
            );
        } catch (Exception e) {
            logger.info("Error in deleteProyek", e);
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder()
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping
    public ResponseEntity<ProyekListResponse> getAll() {
        try {
            List<Proyek> proyeks = proyekService.getAllProyek();
            return ResponseEntity.ok(
                    ProyekListResponse.builder().message("Data proyek berhasil diambil")
                            .data(proyeks)
                            .build()
            );
        } catch (Exception e) {
            logger.info("Error in getAll", e);
            return ResponseEntity.internalServerError().body(
                    ProyekListResponse.builder()
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PostMapping
    public ResponseEntity<ProyekAddResponse> createProyek(@RequestBody ProyekAddRequest request) {
        String namaProyek = request.getNamaProyek();
        String client = request.getClient();
        String tanggalMulai = request.getTanggalMulai();
        String tanggalSelesai = request.getTanggalSelesai();
        String pimpinanProyek = request.getPimpinanProyek();
        String keterangan = request.getKeterangan();

        List<Lokasi> lokasiList = request.getLokasi();

        for (Lokasi lokasi : lokasiList) {
            Integer lokasiId = lokasi.getId();
            if (lokasiId != null) {
                Lokasi lokasiEntity = lokasiService.getLokasiById(lokasiId);
                if (lokasiEntity == null) {
                    return ResponseEntity.badRequest().body(
                            ProyekAddResponse.builder().message("Lokasi tidak ditemukan").build()
                    );
                }
            } else {
                BasicResponse newLokasi = createNewLokasi(lokasi);
                if (newLokasi != null) {
                    return ResponseEntity.badRequest().body(
                            ProyekAddResponse.builder().message(newLokasi.getMessage()).build()
                    );
                }
            }
        }

        if (namaProyek == null || namaProyek.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    ProyekAddResponse.builder().message("Nama proyek tidak boleh kosong").build()
            );
        }
        if (client == null || client.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    ProyekAddResponse.builder().message("Client tidak boleh kosong").build()
            );
        }
        if (tanggalMulai == null || tanggalMulai.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    ProyekAddResponse.builder().message("Tanggal mulai tidak boleh kosong").build()
            );
        }
        if (tanggalSelesai == null || tanggalSelesai.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    ProyekAddResponse.builder().message("Tanggal selesai tidak boleh kosong").build()
            );
        }
        if (pimpinanProyek == null || pimpinanProyek.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    ProyekAddResponse.builder().message("Pimpinan proyek tidak boleh kosong").build()
            );
        }
        Proyek proyek = new Proyek();
        proyek.setNamaProyek(namaProyek);
        proyek.setClient(client);
        proyek.setTanggalMulai(tanggalMulai);
        proyek.setTanggalSelesai(tanggalSelesai);
        proyek.setPimpinanProyek(pimpinanProyek);
        proyek.setKeterangan(keterangan);
        try {
            proyek = proyekService.addProyek(proyek);
            proyekLokasiService.setProyekLokasi(proyek.getId(), lokasiList);
            return ResponseEntity.ok(
                    ProyekAddResponse.builder().message("Proyek berhasil ditambahkan")
                            .proyekId(proyek.getId()).build()
            );
        } catch (Exception e) {
            logger.info("Error in createProyek", e);
            return ResponseEntity.badRequest().body(
                    ProyekAddResponse.builder()
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    private BasicResponse createNewLokasi(Lokasi lokasi) {
        Lokasi lokasiEntity = new Lokasi();
        String namaLokasi = lokasi.getNamaLokasi();
        String negara = lokasi.getNegara();
        String kota = lokasi.getKota();
        String provinsi = lokasi.getProvinsi();
        if (namaLokasi == null || namaLokasi.isEmpty()) {
            return BasicResponse.builder().message("Nama lokasi tidak boleh kosong").build();
        }
        if (negara == null || negara.isEmpty()) {
            return BasicResponse.builder().message("Negara tidak boleh kosong").build();
        }
        if (kota == null || kota.isEmpty()) {
            return BasicResponse.builder().message("Kota tidak boleh kosong").build();
        }
        if (provinsi == null || provinsi.isEmpty()) {
            return BasicResponse.builder().message("Provinsi tidak boleh kosong").build();
        }
        lokasiEntity.setNamaLokasi(namaLokasi);
        lokasiEntity.setNegara(negara);
        lokasiEntity.setKota(kota);
        lokasiEntity.setProvinsi(provinsi);
        lokasiService.addLokasi(lokasiEntity);
        lokasi.setId(lokasiEntity.getId());
        return null;
    }

    @PutMapping
    public ResponseEntity<BasicResponse> updateProyek(@RequestBody ProyekUpdateRequest request) {
        int proyekId = request.getId();
        String namaProyek = request.getNamaProyek();
        String client = request.getClient();
        String tanggalMulai = request.getTanggalMulai();
        String tanggalSelesai = request.getTanggalSelesai();
        String pimpinanProyek = request.getPimpinanProyek();
        String keterangan = request.getKeterangan();

        List<Lokasi> lokasiList = request.getLokasi();

        if (lokasiList != null) {
            for (Lokasi lokasi : lokasiList) {
                Integer lokasiId = lokasi.getId();
                if (lokasiId != null) {
                    Lokasi lokasiEntity = lokasiService.getLokasiById(lokasiId);
                    if (lokasiEntity == null) {
                        return ResponseEntity.badRequest().body(
                                BasicResponse.builder().message("Lokasi tidak ditemukan").build()
                        );
                    }
                } else {
                    BasicResponse newLokasi = createNewLokasi(lokasi);
                    if (newLokasi != null) {
                        return ResponseEntity.badRequest().body(newLokasi);
                    }
                }
            }
        }

        if (proyekId <= 0) {
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder().message("ID proyek tidak valid").build()
            );
        }
        if (namaProyek == null || namaProyek.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder().message("Nama proyek tidak boleh kosong").build()
            );
        }
        if (client == null || client.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder().message("Client tidak boleh kosong").build()
            );
        }
        if (tanggalMulai == null || tanggalMulai.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder().message("Tanggal mulai tidak boleh kosong").build()
            );
        }
        if (tanggalSelesai == null || tanggalSelesai.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder().message("Tanggal selesai tidak boleh kosong").build()
            );
        }
        if (pimpinanProyek == null || pimpinanProyek.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder().message("Pimpinan proyek tidak boleh kosong").build()
            );
        }
        Proyek proyek = new Proyek();
        proyek.setId(proyekId);
        proyek.setNamaProyek(namaProyek);
        proyek.setClient(client);
        proyek.setTanggalMulai(tanggalMulai);
        proyek.setTanggalSelesai(tanggalSelesai);
        proyek.setPimpinanProyek(pimpinanProyek);
        proyek.setKeterangan(keterangan);
        try {
            proyekService.updateProyek(proyek);
            if (lokasiList != null) {
                proyekLokasiService.setProyekLokasi(proyekId, lokasiList);
            }
            return ResponseEntity.ok(
                    BasicResponse.builder().message("Proyek berhasil diupdate").build()
            );
        } catch (Exception e) {
            logger.info("Error in updateProyek", e);
            return ResponseEntity.badRequest().body(
                    BasicResponse.builder()
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
