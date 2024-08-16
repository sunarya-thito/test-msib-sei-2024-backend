package io.github.sunaryathito.testmsibsei2024.service;

import io.github.sunaryathito.testmsibsei2024.entity.LokasiEntity;
import io.github.sunaryathito.testmsibsei2024.entity.ProyekEntity;
import io.github.sunaryathito.testmsibsei2024.entity.ProyekLokasiEntity;
import io.github.sunaryathito.testmsibsei2024.pojo.Lokasi;
import io.github.sunaryathito.testmsibsei2024.repository.LokasiRepository;
import io.github.sunaryathito.testmsibsei2024.repository.ProyekLokasiRepository;
import io.github.sunaryathito.testmsibsei2024.repository.ProyekRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProyekLokasiService {

    private final ProyekLokasiRepository proyekLokasiRepository;
    private final LokasiRepository lokasiRepository;
    private final ProyekRepository proyekRepository;

    public ProyekLokasiService(ProyekLokasiRepository proyekLokasiRepository, LokasiRepository lokasiRepository, ProyekRepository proyekRepository) {
        this.proyekLokasiRepository = proyekLokasiRepository;
        this.lokasiRepository = lokasiRepository;
        this.proyekRepository = proyekRepository;
    }

    @Transactional
    public void setProyekLokasi(int proyekId, List<Lokasi> lokasi) {
        // delete all proyek lokasi
        proyekLokasiRepository.deleteAllByProyekId(proyekId);
        ProyekLokasiEntity proyekLokasiEntity = new ProyekLokasiEntity();
        ProyekEntity proyekEntity = proyekRepository.findById(proyekId).orElse(null);
        if (proyekEntity == null) {
            throw new IllegalArgumentException("Proyek not found");
        }
        proyekLokasiEntity.setProyekId(proyekId);
        for (Lokasi l : lokasi) {
            LokasiEntity lokasiEntity = lokasiRepository.findById(l.getId()).orElse(null);
            if (lokasiEntity == null) {
                throw new IllegalArgumentException("Lokasi not found");
            }
            proyekLokasiEntity.setLokasiId(l.getId());
            proyekLokasiRepository.save(proyekLokasiEntity);
        }
    }
}
