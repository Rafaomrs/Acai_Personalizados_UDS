package br.com.uds.acaipersonalizados.api.builder;

import br.com.uds.acaipersonalizados.api.br.com.uds.acaipersonalizados.api.enums.Size;
import br.com.uds.acaipersonalizados.api.dto.CriarPedidoAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;

public class Builders {

    private Builders(){};

    public static CriarPedidoAcaiDTO buildCriarPedidoAcaiDTO(){
        return CriarPedidoAcaiDTO
                .builder()
                .size("Small")
                .flavor("Morango")
                .personalize("Pacoca")
                .price(10.0)
                .build();
    }
    public static CriarPedidoAcaiDTO buildOrderWithoutSize(){
        return CriarPedidoAcaiDTO
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
    }    public static OrderDTO buildOrderWithPersonalize(){
        return OrderDTO
                .builder()
                .id(1L)
                .personalize("Pacoca")
                .size("Small")
                .flavor("Morango")
                .price(10.0)
                .build();
    }
}
