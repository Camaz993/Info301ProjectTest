package contracts.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import contracts.domain.Contract;
import contracts.repository.ContractRepository;
import contracts.service.ContractService;



@RunWith(MockitoJUnitRunner.class)
public class ContractServiceUnitTests {	
	
	@Mock
	ContractRepository contractRepository;

	@InjectMocks
	ContractService contractService;

	private Contract contract1;
	private Contract contract2;
	
	@Before
	public void setUp()
	{
		contract1 = new Contract();
		//contract1.setUserid("abc123");
		//contract1.setStatusid("1");
		contract1.setAgreement_title("contract_1");
		contract1.setAgreement_type("contract");
		contract1.setDescription("a contract");
		contract1.setAgreement_location("new zealand");
		contract1.setLanguage("english");
		contract1.setRegion("otago");
		contract1.setRelated_agreements("different contract");
		

		contract2 = new Contract();
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
	public void test_add_contract()
	{
		Contract contract_test = new Contract();
		//contract_test.setUserid("gef456");
		//contract_test.setStatusid("3");
		contract_test.setAgreement_title("contract_3");
		contract_test.setAgreement_type("IP");
		contract_test.setDescription("intellectual property");
		contract_test.setAgreement_location("england");
		contract_test.setLanguage("french");
		contract_test.setRegion("nottingham");
		contract_test.setRelated_agreements("different IP's");

		contractService.addContract(contract_test);

		Mockito.verify(contractRepository, Mockito.times(1)).save(contract_test);
	}

	@Test
	public void test_get_all_contracts()
	{
		List<Contract> contracts = new ArrayList<Contract>();
		contracts.add(contract1);
		contracts.add(contract2);

		Mockito.when(contractRepository.findAll()).thenReturn(contracts);

		List<Contract> result = contractService.getAllContracts();
		assertEquals(result.size(), contracts.size());
		for (int i = 0; i < result.size(); i++)
		{
			assertEquals(result.get(i), contracts.get(i));
		}
	}
	
}
