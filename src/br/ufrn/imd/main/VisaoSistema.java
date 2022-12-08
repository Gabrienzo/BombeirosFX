package br.ufrn.imd.main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VisaoSistema extends Application{

    private static Stage stage;

    private static Scene menu;
    private static Scene boletim;
    private static Scene crudNoticia;
    private static Scene alerta;
    private static Scene crudCaso;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        Parent fxmlMenu = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/menu.fxml"));
        menu = new Scene(fxmlMenu, 600, 400);

        Parent fxmlBoletim = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/boletim.fxml"));
        boletim = new Scene(fxmlBoletim, 1100, 660); 

        Parent fxmlCrudNoticia = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/crudNoticia.fxml"));
        crudNoticia = new Scene(fxmlCrudNoticia, 900, 577);

        Parent fxmlAlerta = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/alerta.fxml"));
        alerta = new Scene(fxmlAlerta, 1100, 599);
        
        Parent fxmlCrudCaso = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/crudCaso.fxml"));
        crudCaso = new Scene(fxmlCrudCaso, 900, 576);
        
        primaryStage.setScene(menu);
        primaryStage.show();
        
    }

    public static void changeScreen(String scr, Object userData){
        switch(scr){
            case "menu":
                stage.setScene(menu);
                notifyAllListeners("menu", userData);
                break;
            case "boletim":
                stage.setScene(boletim);
                notifyAllListeners("boletim", userData);
                break;
            case "crudNoticia":
                stage.setScene(crudNoticia);
                notifyAllListeners("crudNoticia", userData);
                break;
            case "alerta":
                stage.setScene(alerta);
                notifyAllListeners("alerta", userData);
                break;
            case "crudCaso":
                stage.setScene(crudCaso);
                notifyAllListeners("crudCaso", userData);
                break;
        }
    }

    public static void changeScreen(String scr){
        changeScreen(scr, null);
    }

    public static void main(String[] args) {
        launch(args);
        
    }

    //----------comunicação entre telas----------------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen, userData);
        }
    }


}
    