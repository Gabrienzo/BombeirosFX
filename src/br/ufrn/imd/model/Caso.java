package br.ufrn.imd.model;

import java.util.List;

import br.ufrn.imd.model.sqlite.CasoSQLiteDAO;

public class Caso {

    private Integer _id;

    private String titulo;
    private String descricao;
    private String data;
    private Boolean andamento;

    
    
    public Caso(String titulo, String descricao, String data, Boolean andamento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.andamento = andamento;
    }
    public Caso(Integer _id, String titulo, String descricao, String data, Boolean andamento) {
        this._id = _id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.andamento = andamento;
    }


    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Boolean getAndamento() {
        return andamento;
    }
    public void setAndamento(Boolean andamento) {
        this.andamento = andamento;
    }
    @Override
    public String toString() {
        return "[" + _id + "] " + titulo + " [ " + data + " ]";
    }
    public int get_id() {
        return 0;
    }

    //------------- DAO

    private static CasoSQLiteDAO daoCaso = new CasoSQLiteDAO();

    public void save(){

        if(_id != null && daoCaso.find(_id) != null){
            daoCaso.update(this);
        } else {
            daoCaso.create(this);
        }
    }

    public void delete(){

        if(daoCaso.find(_id) != null){
            daoCaso.delete(this);
        }
    }

    public static List<Caso> all(){
        return daoCaso.all();
    }

    public static Caso find(int pk){
        return daoCaso.find(pk);
    }

    
    

}
