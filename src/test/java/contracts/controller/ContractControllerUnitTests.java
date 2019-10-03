package contracts.controller;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import contracts.domain.Contract;
import contracts.service.ContractService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ContractControllerUnitTests {
	
		@Mock
		private ContractService contractService;
		
		@InjectMocks
        private ContractController contractController;
	
        private MockMvc mockMvc;
        
        @Before
        public void setUp() {
        	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        	viewResolver.setPrefix("/templates/");
            viewResolver.setSuffix(".html");
        	MockitoAnnotations.initMocks(this);
        	mockMvc = MockMvcBuilders.standaloneSetup(contractController)
        			.setViewResolvers(viewResolver)
        			.build();			
        }
        
        @Test
        public void testFindAll() throws Exception {
        	List<Contract> contracts = new ArrayList<>();
        	contracts.add(new Contract());
        	contracts.add(new Contract());
        	when(contractService.getAllContracts()).thenReturn((List) contracts);
        	mockMvc.perform(get("/search_contracts"))
        		.andExpect(status().isOk())
        		.andExpect(view().name("search_contracts"))
        		.andExpect(model().attribute("contracts", hasSize(2)));      	
        }
        
        @Test
        public void testAddContractForm() throws Exception {
        	mockMvc.perform(get("/add_contracts"))
    		.andExpect(status().isOk())
    		.andExpect(view().name("add_contracts"));	
        }
        

}
