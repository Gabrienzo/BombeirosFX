package br.ufrn.imd.model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufrn.imd.model.Caso;

public class CasoSQLiteDAO extends SQLiteBase{

    public CasoSQLiteDAO(){

        open();

        try {
           PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Casos (" +
           "id INTEGER PRIMARY KEY AUTOINCREMENT," +
           "titulo TEXT," +
           "descricao TEXT," +
           "data TEXT," +
           "andamento BOOLEAN);");

           stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create(Caso n){
        open();

        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Casos VALUES(?,?,?,?,?);");

            stm.setString(2, n.getTitulo());
            stm.setString(3, n.getDescricao());
            stm.setString(4, n.getData());
            stm.setBoolean(5, n.getAndamento());
 
            stm.executeUpdate();
 
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }
        
    }

    public void update(Caso n){
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE Casos SET " +
                                                        "titulo = ?, " +
                                                        "descricao = ?," +
                                                        "data = ?, " +
                                                        "andamento = ?, " +
                                                        "WHERE id = ?;");

            stm.setString(1, n.getTitulo());
            stm.setString(2, n.getDescricao());
            stm.setString(3, n.getData());
            stm.setBoolean(4, n.getAndamento());
            stm.setInt(5, n.get_id());
 
            stm.executeUpdate();

         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }
        
    }

    public void delete(Caso n){
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM Casos WHERE id = ?;");

            stm.setInt(1, n.get_id());
 
            stm.executeUpdate();
            
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }
        
    }

    public Caso find(int pk){
        Caso result = null;
        conn = open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Casos WHERE id = ?;");

            stm.setInt(1, pk);
 
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                Caso n = new Caso(rs.getInt(1),//id
                                        rs.getString(2),//titulo
                                        rs.getString(3),//descricao
                                        rs.getString(4),//data
                                        rs.getBoolean(5));//andamento
                
                result = n;
            }
            
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close();
         }

         return result;
        
    }

    public List<Caso> all(){
        ArrayList<Caso> result = new ArrayList<>();

        open();

        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Casos ORDER BY ID ASC;");

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Caso n = new Caso(rs.getInt(1),//id
                                        rs.getString(2),//titulo
                                        rs.getString(3),//descricao
                                        rs.getString(4),//data
                                        rs.getBoolean(5));//andamento

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
