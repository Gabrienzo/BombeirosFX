package br.ufrn.imd.domain;

import java.util.Optional;

import br.ufrn.imd.main.VisaoSistema;
import br.ufrn.imd.model.Noticia;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class BoletimFxControl {

    @FXML
    protected ListView<Noticia> lvNoticias;

    @FXML
    private TextArea tfNoticiaDesc;
    
    @FXML
    private TextArea tfUltimoCaso;

    @FXML
    protected void btMenu(ActionEvent e){

        VisaoSistema.changeScreen("menu");
    }

    @FXML
    protected void btAlertas(ActionEvent event) {
        VisaoSistema.changeScreen("alerta");
    }

    @FXML
    protected void ntSelected(MouseEvent event) {
        ObservableList<Noticia> ol = lvNoticias.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Noticia n = ol.get(0);
            tfNoticiaDesc.setText(n.getDescricao() + "\n\n" + n.getLink());
        }

    }

    @FXML
    protected void btNovo(ActionEvent e){

        VisaoSistema.changeScreen("crudNoticia");
    }

    @FXML
    protected void btEditar(ActionEvent e){

        ObservableList<Noticia> ol = lvNoticias.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Noticia n = ol.get(0);

            VisaoSistema.changeScreen("crudNoticia",n);
        }
    }

    @FXML
    protected void btDelete(ActionEvent e){
        ObservableList<Noticia> ol = lvNoticias.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Noticia n = ol.get(0);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Deseja realmente excluir a noticia?");
            alert.setContentText("Titulo: " + n.getTitulo());

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                n.delete();
                updateList();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhuma Noticia selecionada!");
            alert.setContentText("Selecione uma noticia antes de apertar em 'Delete'!");
            alert.showAndWait();
        }
        
    }

    @FXML
    protected void initialize(){
        VisaoSistema.addOnChangeScreenListener(new VisaoSistema.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData){
                if(newScreen.equals("boletim")){
                    updateList();
                }
            }
       });

       updateList();
    }

    private void updateList(){

        lvNoticias.getItems().clear();
        for(Noticia n : Noticia.all()){
            lvNoticias.getItems().add(n);
        }
    }

}
