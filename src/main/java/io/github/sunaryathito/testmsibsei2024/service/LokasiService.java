package io.github.sunaryathito.testmsibsei2024.service;

import io.github.sunaryathito.testmsibsei2024.entity.LokasiEntity;
import io.github.sunaryathito.testmsibsei2024.entity.ProyekLokasiEntity;
import io.github.sunaryathito.testmsibsei2024.pojo.Lokasi;
import io.github.sunaryathito.testmsibsei2024.repository.LokasiRepository;
import io.github.sunaryathito.testmsibsei2024.repository.ProyekLokasiRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LokasiService extends AbstractService {
    private final LokasiRepository lokasiRepository;
    private final ProyekLokasiRepository proyekLokasiRepository;

    // note: hindari menggunakan @Autowired
    public LokasiService(LokasiRepository lokasiRepository, ProyekLokasiRepository proyekLokasiRepository) {
        this.lokasiRepository = lokasiRepository;
        this.proyekLokasiRepository = proyekLokasiRepository;
    }

    public long countLokasi() {
        return lokasiRepository.count();
    }

    public List<Lokasi> getAllLokasi() {
        Iterable<LokasiEntity> lokasiIterable = lokasiRepository.findAll();
        List<Lokasi> lokasiList = new ArrayList<>();
        for (LokasiEntity lokasiEntity : lokasiIterable) {
            Lokasi lokasi = convertLokasi(lokasiEntity);
            lokasiList.add(lokasi);
        }
        return lokasiList;
    }

    public List<Lokasi> getLokasiByProyekId(int proyekId) {
        List<ProyekLokasiEntity> proyekLokasiEntityList = proyekLokasiRepository.findAllByProyekId(proyekId);
        List<Lokasi> lokasiList = new ArrayList<>();
        for (ProyekLokasiEntity proyekLokasiEntity : proyekLokasiEntityList) {
            LokasiEntity lokasiEntity = lokasiRepository.findById(proyekLokasiEntity.getLokasiId()).orElse(null);
            if (lokasiEntity != null) {
                Lokasi lokasi = convertLokasi(lokasiEntity);
                lokasiList.add(lokasi);
            }
        }
        return lokasiList;
    }

    public Lokasi getLokasiById(int id) {
        LokasiEntity lokasiEntity = lokasiRepository.findById(id).orElse(null);
        if (lokasiEntity == null) {
            return null;
        }
        return convertLokasi(lokasiEntity);
    }

    private Lokasi convertLokasi(LokasiEntity lokasiEntity) {
        Lokasi lokasi = new Lokasi();
        lokasi.setId(lokasiEntity.getId());
        lokasi.setNamaLokasi(lokasiEntity.getNamaLokasi());
        lokasi.setNegara(lokasiEntity.getNegara());
        lokasi.setProvinsi(lokasiEntity.getProvinsi());
        lokasi.setKota(lokasiEntity.getKota());
        lokasi.setCreatedAt(formatDateTime(lokasiEntity.getCreatedAt()));
        return lokasi;
    }

    public Lokasi addLokasi(Lokasi lokasi) {
        LokasiEntity lokasiEntity = new LokasiEntity();
        lokasiEntity.setNamaLokasi(lokasi.getNamaLokasi());
        lokasiEntity.setNegara(lokasi.getNegara());
        lokasiEntity.setProvinsi(lokasi.getProvinsi());
        lokasiEntity.setKota(lokasi.getKota());
        lokasiRepository.save(lokasiEntity);
        lokasi.setId(lokasiEntity.getId());
        return lokasi;
    }

    public void updateLokasi(Lokasi lokasi) {
        LokasiEntity lokasiEntity = lokasiRepository.findById(lokasi.getId()).orElse(null);
        if (lokasiEntity != null) {
            lokasiEntity.setNamaLokasi(lokasi.getNamaLokasi());
            lokasiEntity.setNegara(lokasi.getNegara());
            lokasiEntity.setProvinsi(lokasi.getProvinsi());
            lokasiEntity.setKota(lokasi.getKota());
            lokasiRepository.save(lokasiEntity);
        }
    }

    @Transactional
    public void deleteLokasi(int id) {
        proyekLokasiRepository.deleteAllByLokasiId(id);
        lokasiRepository.deleteById(id);
    }
}
