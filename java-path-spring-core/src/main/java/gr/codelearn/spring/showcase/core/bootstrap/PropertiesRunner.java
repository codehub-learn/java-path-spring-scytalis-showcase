package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.config.GlobalProperties;
import gr.codelearn.spring.showcase.core.config.GlobalYamlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
@RequiredArgsConstructor
public class PropertiesRunner extends BaseComponent implements CommandLineRunner {
	private final GlobalProperties globalProperties;
	private final GlobalYamlProperties globalYamlProperties;

	@Override
	public void run(String... args) throws Exception {
		// Retrieves information from .properties file
		// .properties file
		logger.info("Using properties, current threadPool value is {}.", globalProperties.getThreadPool());
		logger.info("Using properties, current email value is {}.", globalProperties.getEmail());
		logger.info("Using properties, current errorCode values are {}.", globalProperties.getErrorCodes());

		// Retrieves information from YAML file
		logger.info("Using yaml, current threadPool value is {}.", globalYamlProperties.getThreadPool());
		logger.info("Using yaml, current email value is {}.", globalYamlProperties.getEmail());
		logger.info("Using yaml, current server values are {}.", globalYamlProperties.getServers());
	}
}
