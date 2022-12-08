package br.ufrn.imd.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuControl {

    @FXML
    protected void btBoletim(ActionEvent e){

        VisaoSistema.changeScreen("boletim");
    }

    @FXML
    protected void btAlertas(ActionEvent e){

        VisaoSistema.changeScreen("alerta");
    }

}
