package io.github.sunaryathito.testmsibsei2024.controller;

import io.github.sunaryathito.testmsibsei2024.response.DashboardResponse;
import io.github.sunaryathito.testmsibsei2024.service.LokasiService;
import io.github.sunaryathito.testmsibsei2024.service.ProyekService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
@CrossOrigin
public class DashboardController {

    private final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    private final LokasiService lokasiService;
    private final ProyekService proyekService;

    public DashboardController(LokasiService lokasiService, ProyekService proyekService) {
        this.lokasiService = lokasiService;
        this.proyekService = proyekService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard() {
        try {
            long totalLokasi = lokasiService.countLokasi();
            long totalProyek = proyekService.countProyek();
            return ResponseEntity.ok(
                    DashboardResponse.builder()
                            .message("Dashboard berhasil diambil")
                            .totalLokasi(totalLokasi)
                            .totalProyek(totalProyek)
                            .build()
            );
        } catch (Exception e) {
            logger.info("Error in getDashboard", e);
            return ResponseEntity.internalServerError()
                    .body(DashboardResponse.builder()
                            .message(e.getMessage())
                            .build()
                    );
        }
    }
}
