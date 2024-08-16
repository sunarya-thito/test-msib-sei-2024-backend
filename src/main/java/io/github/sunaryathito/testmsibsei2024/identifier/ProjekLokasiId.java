package io.github.sunaryathito.testmsibsei2024.identifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjekLokasiId implements Serializable {
    private int proyekId;
    private int lokasiId;
}
