package de.dc.spring.fx.dms; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SpringBootApplication
public class DocumentManagementApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private BorderPane root;
	private FXMLLoader fxmlLoader;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(DocumentManagementApplication.class);
        fxmlLoader = new FXMLLoader(getClass().getResource("DMSMain.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("DBS Application");
        Scene scene = new Scene(root, 1400, 800);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }


    public static void main(String[] args) {
        launch(DocumentManagementApplication.class);
    }
}
