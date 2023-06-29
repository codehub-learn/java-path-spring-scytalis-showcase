package gr.codelearn.spring.showcase.core.bootstrap;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import gr.codelearn.spring.showcase.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleRunner extends BaseComponent implements CommandLineRunner {
	private final UserService userService;

	@Override
	public void run(final String... args) throws Exception {
		logger.debug("Loading user database, {}.", userService.getUsers());
	}
}
