package br.com.uds.acaipersonalizados.api.service;

import br.com.uds.acaipersonalizados.api.AbstractBaseTest;
import br.com.uds.acaipersonalizados.api.builder.Builders;
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
        final OrderDTO orderDTO = Builders.buildOrder();
        when(orderRepositoryMock.existsBySize(orderDTO.getSize()))
                .thenReturn(true);

        final Order order = Order.of(orderDTO);
        System.out.println("Sou o Order definido no Test: " + order);

        when(orderRepositoryMock.saveAndFlush(order))
                .thenReturn(Order.of(orderDTO));

        final Order result = orderService.createOrder(orderDTO);

        assertThat(result.getSize(), is(orderDTO.getSize()));
        assertThat(result.getFlavor(), is(orderDTO.getFlavor()));
        assertThat(result.getPersonalize(), is(orderDTO.getPersonalize()));
        assertThat(result.getPrice(), is(orderDTO.getPrice()));


        verify(orderRepositoryMock).existsBySize(orderDTO.getSize());
        verify(orderRepositoryMock).saveAndFlush(order);

    }

    @Test
    public void naoDeveRealizarOPedidoPoisNaoFoiDefinidoOTamanho() {
        final OrderDTO orderWithoutSize = Builders.buildOrderWithoutSize();

        when(orderRepositoryMock.existsBySize(orderWithoutSize.getSize()))
                .thenReturn(false);
        try {
            orderService.createOrder(orderWithoutSize);
            fail("Execao esperada!");
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Dados do pedido estão incompletos."));
        }
        verify(orderRepositoryMock).existsBySize(orderWithoutSize.getSize());
    }

    @Test
    public void deveEncontrarPedidoById() {
        final Order order = Order.of(Builders.buildOrder());
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
        final Order order = Order.of(Builders.buildOrder());
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
        orderWithoutPersonalize.setPersonalize("Pacoca");
        final Optional<Order> optionalOrder = Optional.of(Order.of(orderWithoutPersonalize));

        when(orderRepositoryMock.findOrderById(orderWithoutPersonalize.getId())).thenReturn(optionalOrder);
        final Order order = Order.of(orderWithoutPersonalize);

        when(orderRepositoryMock.save(any(Order.class)))
                .thenReturn(Order.of(orderWithoutPersonalize));


        final OrderDTO result = orderService.personalizeOrder(orderWithoutPersonalize.getId(), "Pacoca");

        assertThat(result.getSize(), is(orderWithoutPersonalize.getSize()));
        assertThat(result.getFlavor(), is(orderWithoutPersonalize.getFlavor()));
        assertThat(result.getPersonalize(), is(orderWithoutPersonalize.getPersonalize()));
        assertThat(result.getPrice(), is(orderWithoutPersonalize.getPrice()));

        final ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);

        verify(orderRepositoryMock).save(argumentCaptor.capture());
        verify(orderRepositoryMock).findOrderById(orderWithoutPersonalize.getId());

        final Order orderArgumentValue = argumentCaptor.getValue();

        assertThat(orderArgumentValue.getPrice(), is(orderWithoutPersonalize.getPrice()));
        assertThat(orderArgumentValue.getSize(), is(orderWithoutPersonalize.getSize()));
        assertThat(orderArgumentValue.getFlavor(), is(orderWithoutPersonalize.getFlavor()));
        assertThat(orderArgumentValue.getPersonalize(), is(orderWithoutPersonalize.getPersonalize()));
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(orderRepositoryMock);
    }

}