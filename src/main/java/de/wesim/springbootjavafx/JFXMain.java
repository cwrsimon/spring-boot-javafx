package de.wesim.springbootjavafx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.application.Application;
import javafx.stage.Stage;

@Component
public class JFXMain extends Application implements CommandLineRunner {

	@Autowired
	MainViewLoaderService loaderService;

	@Override
	public void start(Stage primaryStage) {
		final int parameterCount = getParameters().getRaw().size();
		final String[] args = getParameters().getRaw().toArray(new String[parameterCount]);
		//context = SpringApplication.run(JFXMain.class, args);

		loaderService.init(primaryStage);
	}


	// @Override
	// public void stop() throws Exception {
	// 	if (this.context != null) {
	// 		this.context.close();
	// 	}
	// }

	@Override
	public void run(String... args) throws Exception {
		launch(args);
	}
}
