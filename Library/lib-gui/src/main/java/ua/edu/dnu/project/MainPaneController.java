package ua.edu.dnu.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.util.Objects;

public class MainPaneController {
    private BorderPane pane;
    private String contentFileName;
    private Object contentController;
    private static MainPaneController instance;

    private MainPaneController(){}

    public void setPane(BorderPane pane){
        this.pane = pane;
    }

    public void setMenu(String fxmlFileName){
        pane.setLeft(getNode(fxmlFileName));
    }

    public void setTop(String fxmlFileName){
        pane.setTop(getNode(fxmlFileName));
    }

    public void setContent(String fxmlFileName){
        contentFileName = fxmlFileName;
        pane.setCenter(getNode(fxmlFileName));
    }

    public void update(){
        setContent(contentFileName);
    }

    public static MainPaneController getInstance() {
        if(instance == null){
            instance = new MainPaneController();
        }
        return instance;
    }

    private Parent getNode(String name){
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource(name));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            contentController = fxmlLoader.getController();
            root.setFocusTraversable(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;
    }

    public Object getContentController() {
        return contentController;
    }
}
