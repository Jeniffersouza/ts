package one.digitalinovation.laboojava.utilidade;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Servico;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.constantes.Genero;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe utilitária para auxiliar na leitura de entradas de dados via teclado.
 * @author thiago leite
 */
public final class LeitoraDados {

	/**
	 * Classe do Java para manipular entradas via teclado.
	 */
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

	/**
	 * Ler um dado específico
	 * @return Dado lido
	 */
	public static String lerDado() {
		
		String texto = scanner.nextLine();
		
		return texto;
	}

	/**
	 * Ler os dados do livro a ser cadastrado.
	 * @return Um livro a partir dos dados de entrada
	 */
	public static Servico lerServico() {

		System.out.println("Cadastrando serviço...");
		Servico servico = new Servico();

		System.out.println("Digite o nome do seu serviço");
		String nome = lerDado();
		servico.setNome(nome);

		System.out.println("Digite uma categoria: CASA, CONSTRUCAO, DESIGN");
		String genero = lerDado();
		servico.setGenero(Genero.valueOf(genero.toUpperCase()));

		System.out.println("Digite o valor que você quer cobrar pelo serviço(padrão 0.0)");
		String preco = lerDado();
		servico.setPreco(Double.parseDouble(preco));

		return servico;
	}
	


	/**
	 * Ler os dados do pedido e retorna um objeto a partir destes.
	 * @return Um pedido a partir dos dados de entrada
	 */
	public static Pedido lerPedido(Banco banco) {

		ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

		System.out.println("Cadastrando pedido...");
		Pedido pedido = new Pedido();

		String opcao = "s";
		do {

			System.out.println("Digite o código do serviço");
			String codigo = lerDado();

			Optional<Produto> resultado = produtoNegocio.consultar(codigo);
			if (resultado.isPresent()) {

				Produto produto = resultado.get();

				System.out.println("Digite a quantidade");
				String quantidade = lerDado();
				produto.setQuantidade(Integer.parseInt(quantidade));

				pedido.getProdutos().add(produto);
			} else {
				System.out.println("Produto inexistente. Escolha um produto válido");
			}

			System.out.println("Deseja selecionar mais um serviço? s/n");
			opcao = lerDado();
		} while("s".equals(opcao));

		return pedido;
	}

	/**
	 * Ler os dados do cupom e retorna um objeto a partir destes.
	 * @return O cupom a partir dos dados de entrada
	 */
	public static Optional<Cupom> lerCupom(Banco banco) {

		System.out.println("Caso queira utilizar algum cupom escolha entre: CUPOM2, CUPOM5, CUPOM7. Se não desejar, deixe em branco.");

		String desconto = lerDado();

		for (Cupom cupom: banco.getCupons()) {
			if (cupom.getCodigo().equalsIgnoreCase(desconto)) {
				return Optional.of(cupom);
			}
		}

		return Optional.empty();
	}

}
