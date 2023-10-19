package br.com.fiap.parking.meter;


import org.assertj.core.api.HamcrestCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ParkingMeterServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Deveria devolver status code 200")
	void consultar_condutores() throws Exception {
		var response = mockMvc.perform(get("/condutor"))
				.andReturn().getResponse();

		Assert
	}

}
