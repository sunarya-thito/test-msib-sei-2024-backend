package io.github.sunaryathito.testmsibsei2024.response;

import io.github.sunaryathito.testmsibsei2024.pojo.Proyek;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ProyekListResponse extends BasicResponse {
    private final List<Proyek> data;
}
