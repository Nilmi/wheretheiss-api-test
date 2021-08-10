package at.wheretheiss.apitest;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ApiTestApplication.class,
        loader = SpringBootContextLoader.class
)
@CucumberContextConfiguration
public class SprintContextConfiguration {
}
