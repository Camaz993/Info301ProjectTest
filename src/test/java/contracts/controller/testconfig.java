package contracts.controller;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

public class testconfig {

	@TestConfiguration
	public static class TestConfig {    
	  @Bean 
	  BuildProperties buildProperties() {
	    return new BuildProperties(new Properties());
	  }
	}

}
