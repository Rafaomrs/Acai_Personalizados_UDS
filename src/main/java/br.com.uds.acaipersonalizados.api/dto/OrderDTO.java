package br.com.uds.acaipersonalizados.api.dto;

import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderResourceException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Getter
@JsonInclude
public class OrderDTO {
    @JsonProperty("acaiSize")
    private String size;
    @JsonProperty("acai_flavor")
    private String flavor;
    @JsonProperty("acai_price")
    private BigDecimal price;
    @JsonProperty("acai_prepare_timer")
    private LocalDateTime timer;
    @JsonProperty("acai_personalizer")
    private String personalize;

    public static OrderDTO from(Order order) throws OrderResourceException {
        try {
            return OrderDTO
                    .builder()
                    .flavor(order.getFlavor())
                    .size(order.getSize())
                    .price(order.getPrice())
                    .personalize(order.getPersonalize())
                    .timer(order.getTimer())
                    .build();
        } catch (Exception e) {
            throw new OrderResourceException(
                    "Falha ao converter o resource para entidade,"
                            + "resource: " + order);
        }
    }

}
