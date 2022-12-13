package model;

public class Produto {
    
    //Atributos
    private int id;
    private String descricao;
    private double preco;
    private int qtd_estoque;
    
    private Fornecedor fornecedor; 
    private Categoria categoria; //isso aqui faz a diferença na hora do relacionamento entre tabelas 

    public Produto() {
    }
    
    //Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    
    public Produto(int id, String descricao, double preco, int qtd_estoque, Fornecedor fornecedor, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.qtd_estoque = qtd_estoque;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

   
    
}
