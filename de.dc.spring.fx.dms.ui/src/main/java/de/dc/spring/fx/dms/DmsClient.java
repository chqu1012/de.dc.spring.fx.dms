package de.dc.spring.fx.dms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import de.dc.spring.fx.dms.shared.model.Ticket;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DmsClient extends Application {

	private static final Logger log = LoggerFactory.getLogger(DmsClient.class);

    private ConfigurableApplicationContext springContext;
    private BorderPane root;
	private FXMLLoader fxmlLoader;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Ticket[] ticket = restTemplate.getForObject(
					"http://localhost:8090/tickets", Ticket[].class);
			for (Ticket t : ticket) {
				System.out.println(t);
			}
			log.info(ticket.toString());
		};
	}

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(DmsClient.class);
        fxmlLoader = new FXMLLoader(getClass().getResource("DMSMain.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("DBS Application");
        Scene scene = new Scene(root, 1400, 820);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    @Bean
    public HostServices getHostService() {
    	return getHostServices();
    }
    

    public static void main(String[] args) {
        launch(DmsClient.class);
    }
}