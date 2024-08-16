package io.github.sunaryathito.testmsibsei2024.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LokasiAddRequest {
    private String namaLokasi;
    private String negara;
    private String provinsi;
    private String kota;
}
