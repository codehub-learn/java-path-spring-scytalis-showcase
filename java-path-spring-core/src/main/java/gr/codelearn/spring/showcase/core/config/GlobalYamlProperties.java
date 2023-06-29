package gr.codelearn.spring.showcase.core.config;

import gr.codelearn.spring.showcase.core.base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "myproperties")
@Getter
@Setter
@ToString
public class GlobalYamlProperties extends BaseComponent {
	private int threadPool;
	private String email;
	private List<String> servers;
}
