package io.github.sunaryathito.testmsibsei2024.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
public class BasicResponse {
    protected String message;
}
