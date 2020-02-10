package br.com.uds.acaipersonalizados.api.endpoint;

import br.com.uds.acaipersonalizados.api.builder.Builders;
import br.com.uds.acaipersonalizados.api.dto.AlterarPedidoDeAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.CriarPedidoAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderNotFoundException;
import br.com.uds.acaipersonalizados.api.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderEndPoint.class)
@ActiveProfiles("test")
public class OrderEndpointTest {

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
        final OrderDTO order = Builders.buildOrderWithPersonalize();

        given(orderServiceMock.retornaPorId(1L)).willReturn(order);
        this.mockMvc.perform(get("/acai-personalizados/order/{id}", 1L).contentType(
                MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.price").value(order.getPrice()))
                .andExpect(jsonPath("$.size").value(order.getSize()))
                .andExpect(jsonPath("$.flavor").value(order.getFlavor()))
                .andExpect(jsonPath("$.personalize").value(order.getPersonalize()));
        verify(orderServiceMock).retornaPorId(order.getId());
    }

    @Test
    public void deveFalharQuandoNaoEncontraUmOrderPorId() throws Exception {
        given(orderServiceMock.retornaPorId(1L)).willThrow(OrderNotFoundException.class);
        this.mockMvc.perform(get("/acai-personalizados/order/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())

                .andReturn();

    }

    @Test
    public void deveCriarUmOrder() throws Exception {
        final OrderDTO orderDTO = Builders.buildOrderWithPersonalize();

        given(orderServiceMock.createOrder(any(CriarPedidoAcaiDTO.class))).willReturn(Order.of(orderDTO));

        this.mockMvc.perform(post("/acai-personalizados/order")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(orderDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderDTO.getId()))
                .andExpect(jsonPath("$.price").value(orderDTO.getPrice()))
                .andExpect(jsonPath("$.size").value(orderDTO.getSize()))
                .andExpect(jsonPath("$.flavor").value(orderDTO.getFlavor()))
                .andExpect(jsonPath("$.personalize").value(orderDTO.getPersonalize()))
                .andReturn();

    }

    @Test
    public void deveDeletarOrderPorId() throws Exception {
        final OrderDTO orderDTO = Builders.buildOrderWithPersonalize();

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
        final OrderDTO orderWithoutPersonalize = Builders.buildOrderWithPersonalize();

        final AlterarPedidoDeAcaiDTO alterarPedidoDeAcaiDTO = AlterarPedidoDeAcaiDTO
                .builder()
                .personalize("Granola")
                .build();
        given(orderServiceMock
                .personalizeOrder(any(Long.class), any(AlterarPedidoDeAcaiDTO.class)))
                .willReturn(orderWithoutPersonalize);

        this.mockMvc.perform(put("/acai-personalizados/order/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(alterarPedidoDeAcaiDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
