
package dao;

import jdbc.ConnectionFactory;
import model.Fornecedor;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;

public class DaoProduto {

    private Connection con;

    public DaoProduto() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Metodo Cadastrar Produto
    public void cadastrar(Produto obj) {
        try {

            String sql = "insert into tb_produtos (descricao, preco,qtd_estoque,for_id, cat_id) values (?,?,?,?,?)";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());

            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.setInt(5, obj.getCategoria().getId());

            stmt.execute();
            stmt.close();

            

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }
    
     public void inserir(Produto produto){
        String sql= "insert into produto(descricao, preco, qtd_estoque, fornecedor, categoria) value (?,?,?,?,?,?)";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQtd_estoque());
            stmt.setString(4, produto.getFornecedor().getNome());
            stmt.setInt(5, produto.getCategoria().getId());
            
           stmt.executeUpdate();
            
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }
        
    }

    //Metodo Alterar Produto
    public void alterar(Produto obj) {
        try {

            String sql = "update tb_produtos  set descricao=?, preco=?, qtd_estoque=?, for_id=?, cat_id=?  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());

            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.setInt(5, obj.getCategoria().getId());
            
            stmt.setInt(6, obj.getId());

            

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Alterado com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }

    public void excluir(Produto obj) {
        try {

            String sql = "delete from tb_produtos  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluido com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }

    //Metodo listar Produto
    public List<Produto> listarProdutos() {
        try {

            //1 passo criar a lista
            List<Produto> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome, c.descricao  from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)"
                    + "inner join tb_categoria as c on (p.cat_id = c.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();
                Categoria c = new Categoria();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
                
                c.setDescricao(rs.getString(("c.descricao")));
                obj.setCategoria(c);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //Metodo listar Produto por Nome
    public List<Produto> listarProdutosPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Produto> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome, c.descricao  from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) "
                    + "inner join tb_categoria as c on (p.cat_id = c.id) where p.descricao like ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();
                Categoria c = new Categoria();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
                
                c.setDescricao(rs.getString(("c.descricao")));
                obj.setCategoria(c);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //metodo consultaProduto por Nome
    public Produto consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.

            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome, c.descricao from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)"
                    + "inner join tb_categoria as c on (p.cat_id = c.id)"
                    + " where p.descricao =  ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();
            Categoria c = new Categoria();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
                
                c.setDescricao(rs.getString(("c.descricao")));
                obj.setCategoria(c);

                
                
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }

    //metodo buscaProduto  por Codigo
    public Produto buscaPorCodigo(int id) {
        try {
            //1 passo - criar o sql , organizar e executar.

            String sql = "select * from tb_produtos where id =  ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Produto obj = new Produto();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));

            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }

    
    
    
    //Metodo  que da baixa no estoque
    public void baixaEstoque(int id, int qtd_nova) {
        try {

            String sql = "update tb_produtos  set qtd_estoque= ?  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

           // JOptionPane.showMessageDialog(null, "Produto Alterardo com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }
    
       //Metodo  que da baixa no estoque
    public void adicionarEstoque(int id, int qtd_nova) {
        try {

            String sql = "update tb_produtos  set qtd_estoque= ?  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

           // JOptionPane.showMessageDialog(null, "Produto Alterardo com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }
    
    //Metodo que retorna o estoque atual de um produto
    public int retornaEstoqueAtual(int id) {
        try {
            int qtd_estoque = 0;

            String sql = "SELECT qtd_estoque from tb_produtos where id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {           
                qtd_estoque = (rs.getInt("qtd_estoque"));    
            }

            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
