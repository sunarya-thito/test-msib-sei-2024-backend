package io.github.sunaryathito.testmsibsei2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "proyek")
public class ProyekEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nama_proyek")
    private String namaProyek;

    @Column(name = "client")
    private String client;

    @Column(name = "tgl_mulai")
    private LocalDate tanggalMulai;

    @Column(name = "tgl_selesai")
    private LocalDate tanggalSelesai;

    @Column(name = "pimpinan_proyek")
    private String pimpinanProyek;

    @Column(name = "keterangan", columnDefinition = "TEXT")
    private String keterangan;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
