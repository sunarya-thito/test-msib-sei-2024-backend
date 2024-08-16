package io.github.sunaryathito.testmsibsei2024.response;

import io.github.sunaryathito.testmsibsei2024.pojo.Lokasi;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class LokasiListResponse extends BasicResponse {
    @Nullable
    private final List<Lokasi> data;
}
