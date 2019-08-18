package main.testing;

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

import com.info310.medicinedelivery.backend.entity.contract;
import com.info310.medicinedelivery.backend.repository.ContractRepository;
import com.info310.medicinedelivery.backend.service.ContractService;

@RunWith(MockitoJUnitRunner.class)
public class ContractServiceUnitTests {	
	
	@Mock
	ContractRepository contractRepository;

	@InjectMocks
	ContractService contractService;

	private contract contract1;
	private contract contract2;
	
	@Before
	public void setUp()
	{
		contract1 = new contract();
		contract1.setUser("abc123");
		contract1.setStatusList("1");
		contract1.setAgreementTitle("contract_1");
		contract1.setAgreementType("contract");
		contract1.setDescription("a contract");
		contract1.setAgreementLocation("new zealand");
		contract1.setLanguage("english");
		contract1.setRegion("otago");
		contract1.setRelatedAgreements("different contract");
		

		contract2 = new contract();
		contract2.setUser("def456");
		contract2.setStatusList("2");
		contract2.setAgreementTitle("contract_2");
		contract2.setAgreementType("lawsuit");
		contract2.setDescription("a lawsuit");
		contract2.setAgreementLocation("australia");
		contract2.setLanguage("spanish");
		contract2.setRegion("queensland");
		contract2.setRelatedAgreements("different lawsuit");
	}
	
	@Test
	public void test_add_contract()
	{
		contract contract_test = new contract();
		contract_test.setUser("gef456");
		contract_test.setStatusList("3");
		contract_test.setAgreementTitle("contract_3");
		contract_test.setAgreementType("IP");
		contract_test.setDescription("intellectual property");
		contract_test.setAgreementLocation("england");
		contract_test.setLanguage("french");
		contract_test.setRegion("nottingham");
		contract_test.setRelatedAgreements("different IP's");

		contractService.addContract(contract_test);

		Mockito.verify(contractRepository, Mockito.times(1)).save(contract_test);
	}
/*
	@Test
	public void test_get_all_contracts()
	{
		List<Medication> medications = new ArrayList<Medication>();
		medications.add(med1);
		medications.add(med2);

		Mockito.when(medicationRepository.findAll()).thenReturn(medications);

		List<Medication> result = medicationService.getAllMedications();
		assertEquals(result.size(), medications.size());
		for (int i = 0; i < result.size(); i++)
		{
			assertEquals(result.get(i), medications.get(i));
		}
	}
	*/
	
	
	
	
	
	
	
	
}
