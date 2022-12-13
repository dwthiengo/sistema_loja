
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Categoria;
import model.Fornecedor;
import model.Produto;


public class DaoCategoria {
    
     private Connection con;
     
     public DaoCategoria() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public List<Categoria> buscar(){
        String sql="select * from tb_categoria";
        Connection con = new ConnectionFactory().getConnection();
        try {
          PreparedStatement stmt = con.prepareStatement(sql);
            List<Categoria> resultado = new ArrayList<>();
            
            ResultSet rs = stmt.executeQuery();
            
            Categoria obj = new Categoria();
            
           while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("codigo"));
                categoria.setDescricao(rs.getString("descricao"));
                
                resultado.add(categoria);
            }
            return resultado;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    
    
    
    
    
}
