package io.github.sunaryathito.testmsibsei2024.response;

import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProyekAddResponse extends BasicResponse {
    @Nullable
    private final Integer proyekId;
}
