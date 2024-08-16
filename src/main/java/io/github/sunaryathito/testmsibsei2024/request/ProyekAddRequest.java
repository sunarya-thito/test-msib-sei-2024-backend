package io.github.sunaryathito.testmsibsei2024.request;

import io.github.sunaryathito.testmsibsei2024.pojo.Lokasi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProyekAddRequest {
    private String namaProyek;
    private String client;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String pimpinanProyek;
    private String keterangan;

    private List<Lokasi> lokasi;
}
