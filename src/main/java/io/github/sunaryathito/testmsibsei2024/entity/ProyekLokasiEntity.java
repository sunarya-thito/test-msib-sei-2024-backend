package io.github.sunaryathito.testmsibsei2024.entity;

import io.github.sunaryathito.testmsibsei2024.identifier.ProjekLokasiId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proyek_lokasi")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProjekLokasiId.class)
public class ProyekLokasiEntity {
    @Id
    @Column(name = "proyek_id")
    private int proyekId;

    @Id
    @Column(name = "lokasi_id")
    private int lokasiId;
}
