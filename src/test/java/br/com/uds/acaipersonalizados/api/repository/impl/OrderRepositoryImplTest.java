package br.com.uds.acaipersonalizados.api.repository.impl;

import br.com.uds.acaipersonalizados.api.AbstractBaseTest;
import br.com.uds.acaipersonalizados.api.builder.Builders;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderNotFoundException;
import br.com.uds.acaipersonalizados.api.repository.impl.impl.OrderRepositoryImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class OrderRepositoryImplTest extends AbstractBaseTest {

    @Autowired
    private OrderRepositoryImpl orderRepositoryImpl;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    private Order orderTest;

    @Test
    public void deveFalharAoBuscarOrderPorId() {
        try {
            orderRepositoryImpl.retrieveOrderById(1L);
            fail("Exceção esperada");
        } catch (OrderNotFoundException ex) {
            assertThat(ex.getMessage(), is("Order não encontrado."));
        }
    }

    @Test
    public void deveBuscarOrderPorId() {
        final Order order = Builders.builder();
        final Order orderCriado = orderJpaRepository.save(order);
        final Order orderEncontrado = orderRepositoryImpl.retrieveOrderById(orderCriado.getId());
        assertThat(orderEncontrado.getId(), is(orderCriado.getId()));
    }

    @Test
    public void deveCriarUmOrder() {
        final Order order = Builders.builder();
        orderRepositoryImpl.saveOrder(order);
        Order orderEncontrado = orderRepositoryImpl.retrieveOrderById(order.getId());
        assertThat(orderEncontrado.getId(), is(order.getId()));
    }

    @Test
    public void deveFalharAoCriarUmOrderSemTamanho() {
        final Order order = Builders.builderSemTamanho();
        try {
            orderRepositoryImpl.saveOrder(order);
            fail("Exceção esperada.");
        } catch (DataIntegrityViolationException ex) {
            assertNotNull(ex.getMessage());
        }
    }

}
