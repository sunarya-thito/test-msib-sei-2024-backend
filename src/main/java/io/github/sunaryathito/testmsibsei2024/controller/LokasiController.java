package io.github.sunaryathito.testmsibsei2024.controller;

import io.github.sunaryathito.testmsibsei2024.pojo.Lokasi;
import io.github.sunaryathito.testmsibsei2024.request.LokasiAddRequest;
import io.github.sunaryathito.testmsibsei2024.response.BasicResponse;
import io.github.sunaryathito.testmsibsei2024.response.LokasiAddResponse;
import io.github.sunaryathito.testmsibsei2024.response.LokasiListResponse;
import io.github.sunaryathito.testmsibsei2024.service.LokasiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lokasi")
@CrossOrigin
public class LokasiController {

    private final LokasiService lokasiService;
    private final Logger logger = LoggerFactory.getLogger(LokasiController.class);

    public LokasiController(LokasiService lokasiService) {
        this.lokasiService = lokasiService;
    }

    @GetMapping
    public ResponseEntity<LokasiListResponse> getAllLokasi() {
        try {
            List<Lokasi> allLokasi = lokasiService.getAllLokasi();
            return ResponseEntity.ok(
                    LokasiListResponse.builder()
                            .data(allLokasi)
                            .message("Lokasi berhasil diambil")
                            .build()
            );
        } catch (Exception e) {
            logger.info("Error in getAllLokasi", e);
            return ResponseEntity.internalServerError()
                    .body(LokasiListResponse.builder()
                            .message(e.getMessage())
                            .build()
                    );
        }
    }

    @PostMapping
    public ResponseEntity<LokasiAddResponse> addLokasi(@RequestBody LokasiAddRequest request) {
        try {
            String namaLokasi = request.getNamaLokasi();
            String negara = request.getNegara();
            String provinsi = request.getProvinsi();
            String kota = request.getKota();
            if (namaLokasi == null || namaLokasi.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(LokasiAddResponse.builder()
                                .message("Nama lokasi tidak boleh kosong")
                                .build()
                        );
            }
            if (negara == null || negara.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(LokasiAddResponse.builder()
                                .message("Negara tidak boleh kosong")
                                .build()
                        );
            }
            if (provinsi == null || provinsi.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(LokasiAddResponse.builder()
                                .message("Provinsi tidak boleh kosong")
                                .build()
                        );
            }
            if (kota == null || kota.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(LokasiAddResponse.builder()
                                .message("Kota tidak boleh kosong")
                                .build()
                        );
            }
            Lokasi lokasi = new Lokasi();
            lokasi.setNamaLokasi(namaLokasi);
            lokasi.setNegara(negara);
            lokasi.setProvinsi(provinsi);
            lokasi.setKota(kota);
            lokasi = lokasiService.addLokasi(lokasi);
            return ResponseEntity.ok(
                    LokasiAddResponse.builder()
                            .id(lokasi.getId())
                            .message("Lokasi berhasil ditambahkan")
                            .build()
            );
        } catch (Exception e) {
            logger.info("Error in addLokasi", e);
            return ResponseEntity.internalServerError()
                    .body(LokasiAddResponse.builder()
                            .message(e.getMessage())
                            .build()
                    );
        }
    }

    @PutMapping
    public ResponseEntity<BasicResponse> updateLokasi(@RequestBody Lokasi lokasi) {
        try {
            lokasiService.updateLokasi(lokasi);
            return ResponseEntity.ok(
                    BasicResponse.builder()
                            .message("Lokasi berhasil diupdate")
                            .build()
            );
        } catch (Exception e) {
            logger.info("Error in updateLokasi", e);
            return ResponseEntity.internalServerError()
                    .body(BasicResponse.builder()
                            .message(e.getMessage())
                            .build()
                    );
        }
    }

    @DeleteMapping
    public ResponseEntity<BasicResponse> deleteLokasi(@RequestParam int id) {
        try {
            lokasiService.deleteLokasi(id);
            return ResponseEntity.ok(
                    BasicResponse.builder()
                            .message("Lokasi berhasil dihapus")
                            .build()
            );
        } catch (Exception e) {
            logger.info("Error in deleteLokasi", e);
            return ResponseEntity.internalServerError()
                    .body(BasicResponse.builder()
                            .message(e.getMessage())
                            .build()
                    );
        }
    }
}
