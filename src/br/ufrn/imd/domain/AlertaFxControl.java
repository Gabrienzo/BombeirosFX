package br.ufrn.imd.domain;

import java.util.Optional;

import br.ufrn.imd.main.VisaoSistema;
import br.ufrn.imd.model.Caso;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class AlertaFxControl {

    @FXML
    private ListView<Caso> lvCasos;

    @FXML
    private TextArea tfCasosDesc;

    @FXML
    protected void btMenu(ActionEvent e){

        VisaoSistema.changeScreen("menu");
    }

    @FXML
    protected void btBoletim(ActionEvent event) {

        VisaoSistema.changeScreen("boletim");

    }

    @FXML
    protected void btNovo(ActionEvent event) {

        VisaoSistema.changeScreen("crudCaso");

    }

    @FXML
    protected void btDelete(ActionEvent e){
        ObservableList<Caso> ol = lvCasos.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Caso n = ol.get(0);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Deseja realmente excluir a Caso?");
            alert.setContentText("Titulo: " + n.getTitulo());

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                n.delete();
                updateList();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhuma Caso selecionada!");
            alert.setContentText("Selecione uma Caso antes de apertar em 'Delete'!");
            alert.showAndWait();
        }
        
    }

    @FXML
    protected void btEditar(ActionEvent e){

        ObservableList<Caso> ol = lvCasos.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Caso n = ol.get(0);

            VisaoSistema.changeScreen("crudCaso",n);
        }
    }

    @FXML
    protected void ntSelected(MouseEvent event) {

        ObservableList<Caso> ol = lvCasos.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Caso c = ol.get(0);
            if(c.getAndamento() == true){
                tfCasosDesc.setText(c.getDescricao() +"\n\n" +"[Caso em Andamento]");
            }else {
                tfCasosDesc.setText(c.getDescricao() +"\n\n" +"[Caso Encerrado]");
            }
        }

    }

    @FXML
    protected void initialize(){
        VisaoSistema.addOnChangeScreenListener(new VisaoSistema.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData){
                if(newScreen.equals("alerta")){
                    updateList();
                }
            }
       });

       updateList();
    }

    private void updateList(){

        lvCasos.getItems().clear();
        for(Caso n : Caso.all()){
            lvCasos.getItems().add(n);
        }
    }

}
