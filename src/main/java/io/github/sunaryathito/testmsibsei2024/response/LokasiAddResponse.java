package io.github.sunaryathito.testmsibsei2024.response;

import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LokasiAddResponse extends BasicResponse {
    @Nullable
    private final Integer id;

    public LokasiAddResponse(String message, Integer id) {
        super(message);
        this.id = id;
    }
}
