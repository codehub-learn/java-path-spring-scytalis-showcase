package gr.codelearn.spring.showcase.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
	@Bean("asyncExecutor")
	public Executor getAsyncExecutor() {
		var asyncExecutor = new ThreadPoolTaskExecutor();
		asyncExecutor.setCorePoolSize(3);
		asyncExecutor.setMaxPoolSize(3);
		asyncExecutor.setQueueCapacity(100);
		asyncExecutor.setThreadNamePrefix("async-");
		asyncExecutor.setWaitForTasksToCompleteOnShutdown(true);
		asyncExecutor.initialize();
		return asyncExecutor;
	}
}
