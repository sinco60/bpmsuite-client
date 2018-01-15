package bpmclient;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.levvel.sample.config.MainConfig;
import io.levvel.sample.service.BpmSuiteRestClient;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MainConfig.class)
public class BpmSuiteRestClientTest {
	
	@Autowired
	private BpmSuiteRestClient bpmSuiteRestClient;

	@Test
	public void testStartWorkFlow() {
		String result = bpmSuiteRestClient.startWorkFlow();
		assertNotNull(result);
	}

	
	@Test
	public void testSignalWorkFlow() {
		fail("Not yet implemented");
	}
}
