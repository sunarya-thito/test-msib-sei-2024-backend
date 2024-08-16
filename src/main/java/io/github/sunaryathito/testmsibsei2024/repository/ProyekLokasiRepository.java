package io.github.sunaryathito.testmsibsei2024.repository;

import io.github.sunaryathito.testmsibsei2024.entity.ProyekLokasiEntity;
import io.github.sunaryathito.testmsibsei2024.identifier.ProjekLokasiId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProyekLokasiRepository extends CrudRepository<ProyekLokasiEntity, ProjekLokasiId> {
    void deleteAllByLokasiId(int id);
    void deleteAllByProyekId(int id);
    List<ProyekLokasiEntity> findAllByProyekId(int proyekId);
}
