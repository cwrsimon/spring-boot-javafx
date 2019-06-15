package de.wesim.springbootjavafx;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class JFXMain extends Application {

	private ConfigurableApplicationContext context = null;

	@Override
	public void start(Stage primaryStage) {
		final int parameterCount = getParameters().getRaw().size();
		final String[] args = getParameters().getRaw().toArray(new String[parameterCount]);
		context = SpringApplication.run(JFXMain.class, args);

		final MainViewLoaderService mainView = context.getBean(MainViewLoaderService.class);
		mainView.init(primaryStage);
	}


	@Override
	public void stop() throws Exception {
		if (this.context != null) {
			this.context.close();
		}
	}


	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
