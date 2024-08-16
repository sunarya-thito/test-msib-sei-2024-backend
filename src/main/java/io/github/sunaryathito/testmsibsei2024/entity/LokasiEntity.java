package io.github.sunaryathito.testmsibsei2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "lokasi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LokasiEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama_lokasi")
    private String namaLokasi;

    @Column(name = "negara")
    private String negara;

    @Column(name = "provinsi")
    private String provinsi;

    @Column(name = "kota")
    private String kota;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
