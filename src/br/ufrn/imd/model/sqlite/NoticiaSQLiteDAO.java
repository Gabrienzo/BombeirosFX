package br.ufrn.imd.model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufrn.imd.model.Noticia;

public class NoticiaSQLiteDAO extends SQLiteBase{

    public NoticiaSQLiteDAO(){

        open();

        try {
           PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Noticias (" +
           "id INTEGER PRIMARY KEY AUTOINCREMENT," +
           "titulo TEXT," +
           "descricao TEXT," +
           "link TEXT," +
           "datacriacao TEXT);");

           stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create(Noticia n){
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Noticias VALUES(?,?,?,?,?);");

            stm.setString(2, n.getTitulo());
            stm.setString(3, n.getDescricao());
            stm.setString(4, n.getLink());
 
            stm.executeUpdate();
 
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }
        
    }

    public void update(Noticia n){
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE Noticias SET " +
                                                        "titulo = ?, " +
                                                        "descricao = ?," +
                                                        "link = ?, " +
                                                        "WHERE id = ?;");

            stm.setString(1, n.getTitulo());
            stm.setString(2, n.getDescricao());
            stm.setString(3, n.getLink());
            stm.setInt(4, n.get_id());
 
            stm.executeUpdate();

         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }
        
    }

    public void delete(Noticia n){
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM Noticias WHERE id = ?;");

            stm.setInt(1, n.get_id());
 
            stm.executeUpdate();
            
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }
        
    }

    public Noticia find(int pk){
        Noticia result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Noticias WHERE id = ?;");

            stm.setInt(1, pk);
 
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                Noticia n = new Noticia(rs.getInt(1),//id
                                        rs.getString(2),//titulo
                                        rs.getString(3),//descricao
                                        rs.getString(4));//link
                
                result = n;
            }
            
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }

         return result;
        
    }

    public List<Noticia> all(){
        ArrayList<Noticia> result = new ArrayList<>();

        open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Noticias ORDER BY ID ASC;");

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Noticia n = new Noticia(rs.getInt(1),//id
                                        rs.getString(2),//titulo
                                        rs.getString(3),//descricao
                                        rs.getString(4));//link

                result.add(n);
            }

            Collections.reverse(result);
 
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }

         return result;
    }
    
}
