package one.digitalinovation.laboojava.basedados;

import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por simular um banco de dados. Esta faz as inserções e exclusões da
 * aplicação. Atualizações não são permitidas para facilitar o funcionamento da aplicação.
 * 
 */
public class Banco {

    private static final String url = "jdbc:mysql://localhost:3306/db_ajuda_ai";
	private static final String user = "root";
	private static final String password = "";
	
	private static Connection conn;

    public static Connection getConexao() {
		// TODO Auto-generated method stub
		try {
			
			if(conn == null) {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("CONEXÃO COM O BANCO DE DADOS REALIZADA\n");
			return conn;
			
			}else {
				return conn;
			}
			
			
			
		} catch (SQLException e) {

			e.printStackTrace();
			
			System.out.println("FALHA NA CONEXÃO COM O BANCO DE DADOS\n");
			return null;
		}
		
	}


    /**
     * Lista que armazena os produtos(livros e cadernos) cadastrados.
     */
    private List<Produto> produtos;

    /**
     * Lista que armazena os pedidos cadastrados.
     */
    private List<Pedido> pedidos;

    /**
     * Lista que armazena os cupons disponíveis.
     */
    private List<Cupom> cupons;

    /**
     * Cliente cadastrado.
     */
    private Cliente cliente;

    public Banco() {

        this.produtos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.cliente = new Cliente();

        this.cupons = new ArrayList<>();
        cupons.add(new Cupom("CUPOM2", 2));
        cupons.add(new Cupom("CUPOM5", 5));
        cupons.add(new Cupom("CUPOM7", 7));
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cupom[] getCupons() {
        return cupons.toArray(new Cupom[cupons.size()]);
    }

    public Pedido[] getPedidos() {
        return pedidos.toArray(new Pedido[pedidos.size()]);
    }

    public Produto[] getProdutos() {
        return produtos.toArray(new Produto[produtos.size()]);
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(int posicao) {
        produtos.remove(posicao);
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removerPedido(int posicao) {
        pedidos.remove(posicao);
    }
}
