package contracts.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import contracts.controller.AccountController;
import contracts.domain.Contract;
import contracts.service.AccountService;
import contracts.service.ContractService;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ContractServiceUnitTests {

	@Mock
	private AccountService accountService;
	
	@Mock
	private ContractService contractService;
	
	@InjectMocks
	private AccountController accountController;

    @SuppressWarnings("unused")
	private MockMvc mockMvc;
	
	@Before
    public void setUp() {
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
    	MockitoAnnotations.initMocks(this);
    	mockMvc = MockMvcBuilders.standaloneSetup(accountController)
    			.setViewResolvers(viewResolver)
    			.build();
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void testFindAll() throws Exception {
    	List<Contract> contracts = new ArrayList<>();
    	contracts.add(new Contract());
    	contracts.add(new Contract());
    	when(contractService.getAllContracts()).thenReturn((List) contracts);     	
    }


}
