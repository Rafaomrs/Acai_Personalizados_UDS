package br.com.uds.acaipersonalizados.api.endpoint;

import br.com.uds.acaipersonalizados.api.builder.Builders;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderEndPoint.class)
@ActiveProfiles("test")
public class OderEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private OrderService orderServiceMock;

    private Gson gson;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gson = new GsonBuilder().serializeNulls().create();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deveTerSucessoAoBuscarOrderPorId() throws Exception {
        final OrderDTO order = Builders.buildOrder();

        given(orderServiceMock.retornaPorId(1L)).willReturn(order);
        this.mockMvc.perform(get("/acai-personalizados/order/{id}", 1L).contentType(
                MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.acai_price").value(order.getPrice()))
                .andExpect(jsonPath("$.acaiSize").value(order.getSize()))
                .andExpect(jsonPath("$.acai_flavor").value(order.getFlavor()))
                .andExpect(jsonPath("$.acai_personalizer").value(order.getPersonalize()));
        verify(orderServiceMock).retornaPorId(order.getId());
    }
    @Test
    public void deveFalharQuandoNaoEncontraUmOrderPorId() throws Exception {
        given(orderServiceMock.retornaPorId(3L)).willThrow(EntityNotFoundException.class);
        final MvcResult mvcResult = this.mockMvc.perform(get("/acai-personalizados/order/{id}", 3L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();
        final String value = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(value);
    }
    @Test
    public void deveCriarUmOrder() throws Exception {
        final OrderDTO orderDTO = Builders.buildOrder();

        given(orderServiceMock.createOrder(orderDTO)).willReturn(Order.of(orderDTO));

        this.mockMvc.perform(post("/acai-personalizados/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(orderDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderDTO.getId()))
                .andExpect(jsonPath("$.acai_price").value(orderDTO.getPrice()))
                .andExpect(jsonPath("$.acaiSize").value(orderDTO.getSize()))
                .andExpect(jsonPath("$.acai_flavor").value(orderDTO.getFlavor()))
                .andExpect(jsonPath("$.acai_personalizer").value(orderDTO.getPersonalize()))
                .andReturn();

        verify(orderServiceMock).createOrder(orderDTO);

    }

    @Test
    public void deveDeletarOrderPorId() throws Exception {
        final OrderDTO orderDTO = Builders.buildOrder();

        given(orderServiceMock.deleteById(orderDTO.getId())).willReturn(true);

        this.mockMvc.perform(delete("/acai-personalizados/order/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(orderDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        verify(orderServiceMock).deleteById(orderDTO.getId());
    }
    @Test
    public void deveAtualizarOPedidoComOPersonalize() throws Exception {
        final OrderDTO orderWithoutPersonalize = Builders.buildOrderWithoutPersonalize();
        orderWithoutPersonalize.setPersonalize("Pacoca");

//        doReturn(orderWithoutPersonalize)
//                .when(orderServiceMock)
//                .personalizeOrder(orderWithoutPersonalize.getId(), "Granola");

        given()

        this.mockMvc.perform(put("/acai-personalizados/order/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.acai_price", is(10.0)))
                .andExpect(jsonPath("$.acaiSize", is("Small")))
                .andExpect(jsonPath("$.acai_flavor", is("Morango")))
                .andExpect(jsonPath("$.acai_personalizer", is("Granola")))
                .andReturn();

    }

}
