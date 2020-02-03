package br.com.uds.acaipersonalizados.api.dto;

import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderResourceException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    public Order conversor(OrderDTO orderResource) throws OrderResourceException {
        try {

            final Order order = Order
                    .builder()
                    .flavor(orderResource.getFlavor())
                    .size(orderResource.getSize())
                    .price(orderResource.getPrice())
                    .personalize(orderResource.getPersonalize())
                    .build();

            return order;

        } catch (Exception e) {
            throw new OrderResourceException(
                    "Falha ao converter o resource para entidade,"
                            + "resource: " + orderResource);
        }
    }
    public OrderDTO conversor(Order orderResource) throws OrderResourceException {
        try {

            final OrderDTO orderDTO = OrderDTO
                    .builder()
                    .flavor(orderResource.getFlavor())
                    .size(orderResource.getSize())
                    .price(orderResource.getPrice())
                    .personalize(orderResource.getPersonalize())
                    .build();

            return orderDTO;

        } catch (Exception e) {
            throw new OrderResourceException(
                    "Falha ao converter o resource para entidade,"
                            + "resource: " + orderResource);
        }
    }

    private BigDecimal checkPrice(String price) {
        return BigDecimal.valueOf(Double.parseDouble(price));
    }
    private LocalDateTime checkDate(String date){
        return LocalDateTime.parse(date);
    }



}
