
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
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                
                resultado.add(categoria);
            }
            return resultado;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    

    //Metodo cadastrar Categoria
    public void cadastrarCategorias(Categoria obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_categoria(id,descricao) "
                    + " values (?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getDescricao());
            

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //Metodo Alterar Categoria
    public void alterarCategoria(Categoria obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_categoria  set descricao=?  where id =?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setInt(2, obj.getId());
            
            

            

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }

    //Metodo Excluir Categoria
    public void excluirCategoria(Categoria obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from tb_categoria  where id = ?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //Metodo Listar Todos Categorias
    public List<Categoria> listarCategorias() {
        try {

            //1 passo criar a lista
            List<Categoria> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_categoria";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria obj = new Categoria();

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //metodo consultaCliente  por Nome
    public Categoria consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_categoria  where descricao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Categoria obj = new Categoria();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
            return null;
        }
    }

    //Metodo listaCategoriaPorNome - retorna uma lista
    public List<Categoria> listarCategoriaPorNome(String descricao) {
        try {

            //1 passo criar a lista
            List<Categoria> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_categoria where descricao like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria obj = new Categoria();

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
}
