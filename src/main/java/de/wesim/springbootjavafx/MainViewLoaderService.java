package de.wesim.springbootjavafx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.scene.Scene;
import javafx.stage.Stage;

@Service
public class MainViewLoaderService {

	@Autowired
	private MainView mainView;
	
//    @Autowired
//    private MainViewController mainViewController;

    public void init(Stage stage) {
//        Scene scene = new Scene(grid, 800, 500);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        primaryStage.setOnCloseRequest(e -> {
//            System.err.println("Quitting application.");
//        });
        
        final Scene myScene = new Scene(mainView, 800, 800);
        stage.setScene(myScene);
        stage.setTitle("Blub");
        stage.setWidth(1024);
        stage.setHeight(550);
        stage.setResizable(true);
      //  stage.getIcons().add(new Image(MainViewLoaderService.class.getResource("/icon.png").toExternalForm()));
        //stage.setTitle("ImapNotesFX");
        stage.show();
        stage.setOnCloseRequest(e -> {
        //    getLogger().info("Quitting application.");
        });
     //   this.mainViewController.startup();
    }
}
