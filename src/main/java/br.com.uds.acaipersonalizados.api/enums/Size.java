package br.com.uds.acaipersonalizados.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Size {
    SMALL("Small"), MEDIUM("Medium"), LARGE("Large");

    private String size;

    public static Size of(String value) {
        return Arrays.stream(Size.values())
                .filter(sizeDeclarado -> sizeDeclarado.size.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Dados do pedido estão incompletos."));
    }

}
