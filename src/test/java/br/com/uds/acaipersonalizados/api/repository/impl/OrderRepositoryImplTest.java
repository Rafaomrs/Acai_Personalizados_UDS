package br.com.uds.acaipersonalizados.api.repository.impl;

import br.com.uds.acaipersonalizados.api.AbstractBaseTest;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.repository.OrderJpaRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderRepositoryImplTest extends AbstractBaseTest {
    @Autowired
    private OrderRepositoryImplTest orderRepositoryImplTest;
    @Autowired
    private OrderJpaRepository orderJpaRepository;

    private Order orderTest;

    @Before
    public void setup(){

    }
}
