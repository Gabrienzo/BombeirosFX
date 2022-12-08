package br.ufrn.imd.domain;

import br.ufrn.imd.main.VisaoSistema;
import br.ufrn.imd.model.Caso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class CrudAlerta {

    private Caso casoAtual;

    @FXML
    private CheckBox cbAndamento;

    @FXML
    private CheckBox cbEncerrado;

    @FXML
    private TextField tfDesc;

    @FXML
    private TextField tfData;

    @FXML
    private TextField tfTitulo;

    @FXML
    protected void initialize(){
       VisaoSistema.addOnChangeScreenListener(new VisaoSistema.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData){
                if(newScreen.equals("crudCaso")){

                    if(userData != null){

                        casoAtual = (Caso)userData;
                        tfTitulo.setText(casoAtual.getTitulo());
                        tfDesc.setText(casoAtual.getDescricao());
                        tfData.setText(casoAtual.getData());
                        if(casoAtual.getAndamento() == true){
                            cbAndamento.setSelected(true);
                            cbEncerrado.setSelected(false);
                        } else {
                            cbAndamento.setSelected(false);
                            cbEncerrado.setSelected(true);
                        }
                    } else {

                        casoAtual = null;
                        tfTitulo.setText("");
                        tfDesc.setText("");
                        tfData.setText("");
                        cbAndamento.setSelected(false);
                        cbEncerrado.setSelected(false);
                    }
                }
            }
       }); 
    }

    @FXML
    protected void btCancel(ActionEvent event) {

        VisaoSistema.changeScreen("alerta");

    }

    @FXML
    protected void btSave(ActionEvent e){
        try {

            if(tfDesc.getText().isEmpty() || tfTitulo.getText().isEmpty() || tfData.getText().isEmpty()){
                throw new RuntimeException("Algum dos campos estava vazio ao tentar criar a caso...");
            }
            if(cbAndamento.isSelected() && cbEncerrado.isSelected()){
                throw new RuntimeException("Apenas uma das caixas pode estar selecionada por vez!");
            }

            if(casoAtual != null){

                if(cbAndamento.isSelected()==true){

                    casoAtual.setTitulo(tfTitulo.getText());
                    casoAtual.setDescricao(tfDesc.getText());
                    casoAtual.setData(tfData.getText());
                    casoAtual.setAndamento(true);
                    casoAtual.save();
                } else if(cbEncerrado.isSelected()==true){

                    casoAtual.setTitulo(tfTitulo.getText());
                    casoAtual.setDescricao(tfDesc.getText());
                    casoAtual.setData(tfData.getText());
                    casoAtual.setAndamento(false);
                    casoAtual.save();
                } else {
                    throw new RuntimeException("É necessario especificar se o caso está em andamento ou não!");
                }

            } else {

                if(cbAndamento.isSelected()==true){
                    Caso n = new Caso(
                    tfTitulo.getText(),
                    tfDesc.getText(),
                    tfData.getText(),
                    true);
                    n.save();
                } else if(cbEncerrado.isSelected()==true){
                    Caso n = new Caso(
                    tfTitulo.getText(),
                    tfDesc.getText(),
                    tfData.getText(),
                    false);
                    n.save();
                } else {
                    throw new RuntimeException("É necessario especificar se o caso está em andamento ou não!");
                }
        
            }
        
            VisaoSistema.changeScreen("alerta");

        } catch (RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao criar caso");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

}