package br.com.uds.acaipersonalizados.api.builder;

import br.com.uds.acaipersonalizados.api.dto.OrderDTO;

public class Builders {

    private Builders(){};

    public static OrderDTO buildOrder(){
        return OrderDTO
                .builder()
                .id(1L)
                .size("Small")
                .flavor("Morango")
                .personalize("Pacoca")
                .price(10.0)
                .build();
    }
    public static OrderDTO buildOrderWithoutSize(){
        return OrderDTO
                .builder()
                .flavor("Morango")
                .personalize("Pacoca")
                .price(10.0)
                .build();
    }
    public static OrderDTO buildOrderWithoutPersonalize(){
        return OrderDTO
                .builder()
                .id(1L)
                .size("Small")
                .flavor("Morango")
                .price(10.0)
                .build();
    }
}
