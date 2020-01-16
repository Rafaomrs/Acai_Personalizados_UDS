package br.com.domain.acaipersonalizados.enumTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.domain.acaipersonalizados.validationEnum.EnumSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumSizeTest {
	
	
	@Test
	public void deve_mostrar_o_valor_de_300ml() {
		EnumSize tamanho = EnumSize.SMALL;
		Assert.assertEquals(tamanho.getValue(), "300ml");
	}
	@Test
	public void deve_mostrar_o_valor_de_500ml() {
		EnumSize tamanho = EnumSize.MEDIUM;
		Assert.assertEquals(tamanho.getValue(), "500ml");
	}
	@Test
	public void deve_mostrar_o_valor_de_700ml() {
		EnumSize tamanho = EnumSize.LARGE;
		Assert.assertEquals(tamanho.getValue(), "700ml");
	}
//	@Test
//	public void temp() {
//		EnumTemp temp = EnumTemp.SMALL;
//		Assert.assertArrayEquals(temp);
//	}
}
