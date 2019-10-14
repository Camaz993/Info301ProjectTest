package contracts.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import contracts.controller.AccountController;
import contracts.domain.Contract;
import contracts.domain.User;
import contracts.service.AccountService;
import contracts.service.ContractService;
import contracts.service.IContractService;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
	
	@Test
    public void testFindAll() throws Exception {
    	List<Contract> contracts = new ArrayList<>();
    	contracts.add(new Contract());
    	contracts.add(new Contract());
    	when(contractService.getAllContracts()).thenReturn((List) contracts);     	
    }


}
