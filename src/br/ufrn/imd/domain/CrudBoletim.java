package br.ufrn.imd.domain;

import br.ufrn.imd.main.VisaoSistema;
import br.ufrn.imd.model.Noticia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CrudBoletim {

    private Noticia noticiaAtual;

    @FXML
    private TextField tfDesc;

    @FXML
    private TextField tfLink;

    @FXML
    private TextField tfTitulo;

    @FXML
    protected void initialize(){
       VisaoSistema.addOnChangeScreenListener(new VisaoSistema.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData){
                if(newScreen.equals("crudNoticia")){

                    if(userData != null){

                        noticiaAtual = (Noticia)userData;
                        tfTitulo.setText(noticiaAtual.getTitulo());
                        tfDesc.setText(noticiaAtual.getDescricao());
                        tfLink.setText(noticiaAtual.getLink());
                    } else {

                        noticiaAtual = null;
                        tfTitulo.setText("");
                        tfDesc.setText("");
                        tfLink.setText("");
                    }
                }
            }
       }); 
    }
    
    @FXML
    protected void btCancel(ActionEvent e){

        VisaoSistema.changeScreen("boletim");
    }

    @FXML
    protected void btSave(ActionEvent e){
        try {

            if(tfDesc.getText().isEmpty() || tfTitulo.getText().isEmpty() || tfLink.getText().isEmpty()){
                throw new RuntimeException();
            }

            if(noticiaAtual != null){

                noticiaAtual.setTitulo(tfTitulo.getText());
                noticiaAtual.setDescricao(tfDesc.getText());
                noticiaAtual.setLink(tfLink.getText());
                noticiaAtual.save();
            } else {

                Noticia n = new Noticia(
                    tfTitulo.getText(),
                    tfDesc.getText(),
                    tfLink.getText());
        
                n.save();
            }
        
            VisaoSistema.changeScreen("boletim");

        } catch (RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao criar noticia");
            alert.setContentText("Algum dos campos estava vazio ao tentar criar a noticia...");
            alert.showAndWait();
        }
    }
    
}
