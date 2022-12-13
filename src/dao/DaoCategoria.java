
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
import model.Funcionario;
import model.Produto;
import view.FrmLogin;
import view.FrmMenu;


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
                categoria.setId(rs.getInt("codigo"));
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

    //Metodo Alterar Funcionario
    public void alterarCategoria(Categoria obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_categoria  set descricao=?,   where id =?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getDescricao());
            

            stmt.setInt(17, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }

    //Metodo Excluir Funcionario
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

    //Metodo Listar Todos Funcionarios
    public List<Funcionario> listarCategorias() {
        try {

            //1 passo criar a lista
            List<Funcionario> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

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

                obj.setCodigo(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
            return null;
        }
    }

    //Metodo listaFuncionarioPorNome - retorna uma lista
    public List<Funcionario> listarFuncionariosPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Funcionario> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

    //Metodo efetuaLogin
    public void efetuaLogin(String email, String senha) {
        try {

            //1 passo - SQL
            String sql = "select * from tb_funcionarios where email = ? and senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //Usuario logou

                //Caso o usuario seja do tipo admin
                if (rs.getString("nivel_acesso").equals("Administrador")) {

                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    FrmMenu tela = new FrmMenu();
                    tela.setUsuarioLogado(rs.getString("nome"));

                    tela.setVisible(true);
                } //Caso o usuario seja do tipo limitado 
                else if (rs.getString("nivel_acesso").equals("Usuário")) {

                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    FrmMenu tela = new FrmMenu();
                    tela.setUsuarioLogado(rs.getString("nome"));

                    //Desabilitar os menus
                    tela.menu_posicao.setEnabled(false);
                    tela.menu_controlevendas.setVisible(false);

                    tela.setVisible(true);

                }

            } else {
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new FrmLogin().setVisible(true);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }

    }

}

    
    
}
