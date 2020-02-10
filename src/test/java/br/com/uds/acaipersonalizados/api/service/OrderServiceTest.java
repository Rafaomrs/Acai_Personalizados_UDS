package br.com.uds.acaipersonalizados.api.service;

import br.com.uds.acaipersonalizados.api.AbstractBaseTest;
import br.com.uds.acaipersonalizados.api.builder.Builders;
import br.com.uds.acaipersonalizados.api.dto.AlterarPedidoDeAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.CriarPedidoAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderNotFoundException;
import br.com.uds.acaipersonalizados.api.repository.OrderJpaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class OrderServiceTest extends AbstractBaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private OrderService orderService;
    @Mock
    private OrderJpaRepository orderRepositoryMock;

    @Before
    public void setup() {
        this.orderService = new OrderService(
                orderRepositoryMock);
    }

    @Test
    public void deveRealizarUmPedidoComSucesso() {
        final CriarPedidoAcaiDTO criarPedidoAcaiDTO = Builders.buildCriarPedidoAcaiDTO();

        final Order order = Order.of(criarPedidoAcaiDTO);

        when(orderRepositoryMock.saveAndFlush(order))
                .thenReturn(Order.of(criarPedidoAcaiDTO));

        final Order result = orderService.createOrder(criarPedidoAcaiDTO);

        assertThat(result.getSize(), is(criarPedidoAcaiDTO.getSize()));
        assertThat(result.getFlavor(), is(criarPedidoAcaiDTO.getFlavor()));
        assertThat(result.getPersonalize(), is(criarPedidoAcaiDTO.getPersonalize()));
        assertThat(result.getPrice(), is(criarPedidoAcaiDTO.getPrice()));


        verify(orderRepositoryMock).saveAndFlush(order);

    }

    @Test
    public void naoDeveRealizarOPedidoPoisNaoFoiDefinidoOTamanho() {
        final CriarPedidoAcaiDTO orderWithoutSize = Builders.buildOrderWithoutSize();

        try {
            orderService.createOrder(orderWithoutSize);
            fail("Execao esperada!");
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Dados do pedido estão incompletos."));
        }
    }

    @Test
    public void deveEncontrarPedidoById() {
        final Order order = Order.of(Builders.buildCriarPedidoAcaiDTO());
        final Optional<Order> optionalOrder = Optional.of(order);

        when(orderRepositoryMock.findOrderById(1L)).thenReturn(optionalOrder);

        final OrderDTO result = orderService.retornaPorId(1L);

        assertThat(result.getSize(), is(order.getSize()));
        assertThat(result.getFlavor(), is(order.getFlavor()));
        assertThat(result.getPrice(), is(order.getPrice()));
        assertThat(result.getPersonalize(), is(order.getPersonalize()));
        assertThat(result.getSize(), is(order.getSize()));

        verify(orderRepositoryMock).findOrderById(eq(1L));
    }

    @Test
    public void deveDeletarPedidoById() throws OrderNotFoundException {
        final Order order = Order.of(Builders.buildCriarPedidoAcaiDTO());
        final Optional<Order> optionalOrder = Optional.of(order);

        when(orderRepositoryMock.findOrderById(1L)).thenReturn(optionalOrder);
        doNothing().when(orderRepositoryMock).deleteById(1L);

        final boolean result = orderService.deleteById(1L);

        assertTrue(result);

        verify(orderRepositoryMock).findOrderById(1L);
        verify(orderRepositoryMock).deleteById(1L);
    }

    @Test
    public void deveAdicionarOAtributoPersonalize(){
        final OrderDTO orderWithoutPersonalize = Builders.buildOrderWithoutPersonalize();
        final OrderDTO orderComPersonalize = Builders.buildOrderWithPersonalize();
        final Optional<Order> optionalOrder = Optional.of(Order.of(orderWithoutPersonalize));

        when(orderRepositoryMock.findOrderById(orderWithoutPersonalize.getId())).thenReturn(optionalOrder);
        final Order order = Order.of(orderWithoutPersonalize);

        when(orderRepositoryMock.save(any(Order.class)))
                .thenReturn(Order.of(orderComPersonalize));


        final AlterarPedidoDeAcaiDTO alterarPedidoDeAcaiDTO = AlterarPedidoDeAcaiDTO
                .builder()
                .personalize("Granola")
                .build();

        final OrderDTO result = orderService
                .personalizeOrder(orderWithoutPersonalize.getId(), alterarPedidoDeAcaiDTO);

        assertThat(result.getSize(), is(orderComPersonalize.getSize()));
        assertThat(result.getFlavor(), is(orderComPersonalize.getFlavor()));
        assertThat(result.getPersonalize(), is(orderComPersonalize.getPersonalize()));
        assertThat(result.getPrice(), is(orderComPersonalize.getPrice()));

        final ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);

        verify(orderRepositoryMock).save(argumentCaptor.capture());
        verify(orderRepositoryMock).findOrderById(orderWithoutPersonalize.getId());

        final Order orderArgumentValue = argumentCaptor.getValue();

        assertThat(orderArgumentValue.getPrice(), is(orderWithoutPersonalize.getPrice()));
        assertThat(orderArgumentValue.getSize(), is(orderWithoutPersonalize.getSize()));
        assertThat(orderArgumentValue.getFlavor(), is(orderWithoutPersonalize.getFlavor()));
        assertThat(orderArgumentValue.getPersonalize(), is(alterarPedidoDeAcaiDTO.getPersonalize()));
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(orderRepositoryMock);
    }

}