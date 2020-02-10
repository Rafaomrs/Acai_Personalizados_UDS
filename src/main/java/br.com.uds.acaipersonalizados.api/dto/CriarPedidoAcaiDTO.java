package br.com.uds.acaipersonalizados.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Getter
public class CriarPedidoAcaiDTO {
    @NotNull(message = "Campo obrigatório!")
    @JsonProperty("size")
    private String size;

    @Size(min = 1, max = 10, message = "Tamanho deve estar entre 1 e 10")
    @NotBlank(message = "Flavor não estar em branco")
    @JsonProperty("flavor")
    private String flavor;

    @Positive
    @JsonProperty("price")
    private Double price;

    @JsonProperty("timer")
    private LocalDateTime timer;

    @JsonProperty("personalize")
    private String personalize;
}
