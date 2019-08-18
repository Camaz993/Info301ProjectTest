package test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import contracts.application;
import domain.contract;
import service.ContractService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
@WebAppConfiguration
public class ContractControllerUnitTests {

	@MockBean
	private ContractService contractService;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mvc;
	private ObjectMapper objectMapper;

	private contract contract1, contract2;

	@Before
	public void setUp()
	{
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		objectMapper = new ObjectMapper();

		contract1 = new contract();
		//contract1.setUserid("abc123");
		//contract1.setStatusid("1");
		contract1.setAgreement_title("contract_1");
		contract1.setAgreement_type("contract");
		contract1.setDescription("a contract");
		contract1.setAgreement_location("new zealand");
		contract1.setLanguage("english");
		contract1.setRegion("otago");
		contract1.setRelated_agreements("different contract");

		contract2 = new contract();
		//contract2.setUserid("def456");
		//contract2.setStatusid("2");
		contract2.setAgreement_title("contract_2");
		contract2.setAgreement_type("lawsuit");
		contract2.setDescription("a lawsuit");
		contract2.setAgreement_location("australia");
		contract2.setLanguage("spanish");
		contract2.setRegion("queensland");
		contract2.setRelated_agreements("different lawsuit");
	}
	
	@Test
	public void test_add_contract() throws Exception
	{
		MvcResult result = mvc.perform(post("/api/contracts").param("agreement_title", "Test Contract 2")
				.param("agreement_type", "Test Contract").param("description", "Test Contract").param("agreement_location", "America").param("language", "american")
				.param("region", "New York").param("related_agreements", "None")).andReturn();

		int status = result.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void test_get_all_contracts() throws Exception
	{
		List<contract> contractList = new ArrayList<contract>();
		contractList.add(contract1);
		contractList.add(contract2);
		Mockito.when(contractService.getAllContracts()).thenReturn(contractList);

		MvcResult result = mvc.perform(get("/api/allcontracts").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		List<contract> resList = objectMapper.readValue(result.getResponse().getContentAsString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, contract.class));

		assertEquals(resList.size(), contractList.size());
		for (int i = 0; i < contractList.size(); i++)
		{
			assertEquals(resList.get(i), contractList.get(i));
		}
	}
	
}
