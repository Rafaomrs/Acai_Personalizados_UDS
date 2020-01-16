package br.com.domain.acaipersonalizados.enumTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.domain.acaipersonalizados.validationEnum.EnumFlavor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumFlavorTest {
	
	@Test
	public void deve_mostrar_o_sabor_Morango() {
		EnumFlavor sabor = EnumFlavor.FLAVOR1;
		Assert.assertEquals(sabor.getValue(), "Morango");
	}
	@Test
	public void deve_mostrar_o_sabor_Banana() {
		EnumFlavor sabor = EnumFlavor.FLAVOR2;
		Assert.assertEquals(sabor.getValue(), "Banana");
	}
	@Test
	public void deve_mostrar_o_sabor_Kiwi() {
		EnumFlavor sabor = EnumFlavor.FLAVOR3;
		Assert.assertEquals(sabor.getValue(), "Kiwi");
	}
}
