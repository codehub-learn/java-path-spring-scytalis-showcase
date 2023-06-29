package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(0)
@RequiredArgsConstructor
public class EnvironmentRunner extends BaseComponent implements CommandLineRunner {
	private final Environment env;

	@Override
	public void run(String... args) throws Exception {
		logger.info("These profiles ({}) are currently active.", env.getActiveProfiles());
		logger.info("Current 'database URL' value is {} (environment)", env.getProperty("spring.datasource.url"));
	}
}
