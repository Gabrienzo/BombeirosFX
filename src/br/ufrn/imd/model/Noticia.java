package br.ufrn.imd.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.ufrn.imd.model.sqlite.NoticiaSQLiteDAO;

public class Noticia {
    
    private Integer _id;

    private String titulo;
    private String descricao;
    private String link;
    private String datacriacao;

    
    public Noticia(String titulo, String descricao, String link) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        Date datacriacaonew = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");  
        this.datacriacao = dateFormat.format(datacriacaonew);
    }
    public Noticia(int _id, String titulo, String descricao, String link) {
        this._id = _id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        Date datacriacaonew = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");  
        this.datacriacao = dateFormat.format(datacriacaonew);
    }

    
    public Integer get_id() {
        return _id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getData() {
        return datacriacao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "[" + _id + "] " + titulo + " [ " + datacriacao + " ]";
    }

    //------------- DAO

    private static NoticiaSQLiteDAO dao = new NoticiaSQLiteDAO();

    public void save(){

        if(_id != null && dao.find(_id) != null){
            dao.update(this);
        } else {
            dao.create(this);
        }
    }

    public void delete(){

        if(dao.find(_id) != null){
            dao.delete(this);
        }
    }

    public static List<Noticia> all(){
        return dao.all();
    }

    public static Noticia find(int pk){
        return dao.find(pk);
    }
    

}
