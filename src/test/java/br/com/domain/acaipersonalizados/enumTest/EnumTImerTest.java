package br.com.domain.acaipersonalizados.enumTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import br.com.domain.acaipersonalizados.validationEnum.EnumTimer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTImerTest {
	
	@Test
	public void deve_mostrar_o_tempo_de_5min() {
		EnumTimer time = EnumTimer.SMALL;
		Assert.assertEquals(time.getValue(), "5min");
	}
	@Test
	public void deve_mostrar_o_tempo_de_7min() {
		EnumTimer time = EnumTimer.SMALL;
		Assert.assertEquals(time.getValue(), "7min");
	}
	@Test
	public void deve_mostrar_o_tempo_de_10min() {
		EnumTimer time = EnumTimer.SMALL;
		Assert.assertEquals(time.getValue(), "10min");
	}

}
