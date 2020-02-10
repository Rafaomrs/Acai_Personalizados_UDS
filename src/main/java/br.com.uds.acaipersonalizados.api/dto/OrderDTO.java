package br.com.uds.acaipersonalizados.api.dto;

import br.com.uds.acaipersonalizados.api.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
@JsonInclude
public class OrderDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("size")
    private String size;
    @JsonProperty("flavor")
    private String flavor;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("timer")
    private LocalDateTime timer;
    @JsonProperty("personalize")
    private String personalize;

    public static OrderDTO from(Order order) {
        return OrderDTO
                .builder()
                .id(order.getId())
                .flavor(order.getFlavor())
                .size(order.getSize())
                .price(order.getPrice())
                .personalize(order.getPersonalize())
                .timer(order.getTimer())
                .build();
    }
}
