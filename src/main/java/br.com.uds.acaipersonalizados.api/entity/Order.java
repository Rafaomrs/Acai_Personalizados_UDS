package br.com.uds.acaipersonalizados.api.entity;

import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.exception.OrderResourceException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "pedido")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "size")
    private String size;
    @Column(name = "flavor")
    private String flavor;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "timer")
    private LocalDateTime timer;
    @Column(name = "personalize")
    private String personalize;

    @Builder
    public Order(String size,
                 String flavor,
                 LocalDateTime timer,
                 BigDecimal price,
                 String personalize
    ) {
        this.size = size;
        this.flavor = flavor;
        this.timer = timer;
        this.price = price;
        this.personalize = personalize;
    }

    public static Order of(OrderDTO orderDTO) throws OrderResourceException {
        try {
            return Order.builder()
                    .flavor(orderDTO.getFlavor())
                    .timer(orderDTO.getTimer())
                    .personalize(orderDTO.getPersonalize())
                    .price(orderDTO.getPrice())
                    .size(orderDTO.getSize())
                    .build();
        } catch (Exception e) {
            throw new OrderResourceException(
                    "Falha ao converter o resource para entidade,"
                            + "resource: " + orderDTO);
        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTimer(LocalDateTime timer) {
        this.timer = timer;
    }

    public void setPersonalize(String personalize) {
        this.personalize = personalize;
    }

}
