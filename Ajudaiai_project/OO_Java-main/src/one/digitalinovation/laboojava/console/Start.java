package one.digitalinovation.laboojava.console;
import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.negocio.ClienteNegocio;
import one.digitalinovation.laboojava.negocio.PedidoNegocio;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;


import java.util.Arrays;
import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * 
 */

public class Start {
	
	private static Banco banco = new Banco();
	
	

	private static Cliente clienteLogado = null;
	
	private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
	private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
	private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);
	

	/**
	 * Método utilitário para inicializar a aplicação.
	 * 
	 */
	public static void main(String[] args) {

		logar();

	}
	
	private static void identificarUsuario(String cpf) {
		Cliente cliente = new Cliente();

		if (cliente.getCpfs().contains(cpf)) {
		    // O CPF digitado está na lista
		    System.out.println("CPF encontrado");
			menu();
		} else {
			System.out.println("CPF inválido. Acesso negado.\n");
			logar();
		}
	}
  

	private static void logar() {
		
		String opcaoi = "";
		String cpfNovo;
		String cpf = "";
		System.out.println("Bem vindo ao AjudaAi!");
///     MENU PRINCIPAL
		System.out.println("Selecione uma opcao:\n");
		System.out.println("1 - CADASTRAR CPF");
		System.out.println("2 - ENTRAR");
		
		
		opcaoi = LeitoraDados.lerDado();
		switch (opcaoi) {
		//////
		case "1":
			System.out.println("DIGITE O CPF SEM PONTOS");
			cpfNovo = LeitoraDados.lerDado();
			Cliente cliente = new Cliente();
			cliente.addCpf(cpfNovo);
			
			logar();
			 
			break;
			
		case "2":
			
			while (true) {

				if (clienteLogado == null) {

					System.out.println("Digite o cpf:");

					
					cpf = LeitoraDados.lerDado();
					
					
					identificarUsuario(cpf);
				}
			}
			
		default:
			System.out.println("Opção inválida.");
			break;
		}
		

		
	}

	private static void menu() {

		String opcao = "";
		System.out.println("Selecione uma opcao:\n");
		System.out.println("1 - Cadastrar servico ");
		System.out.println("2 - Excluir servico");
		// TODO Desafio: Consultar Livro(nome)
		//System.out.println("3 - Cadastrar Caderno");
		//System.out.println("4 - Excluir Caderno");
		// TODO Desafio: Consultar Caderno(matéria)
		System.out.println("3 - Fazer pedido");
		System.out.println("4 - Excluir pedido");
		// TODO Desafio: Consultar Pedido(código)
		System.out.println("5 - Listar servicos");
		System.out.println("6 - Listar pedidos");
		System.out.println("7 - Deslogar");
		System.out.println("8 - Sair");

		opcao = LeitoraDados.lerDado();

		switch (opcao) {
		case "1":
			Servico servico = LeitoraDados.lerServico();
			produtoNegocio.salvar(servico);
			menu();
			break;
		case "2":
			System.out.println("Digite o código do servico");
			String codigoLivro = LeitoraDados.lerDado();
			produtoNegocio.excluir(codigoLivro);
			menu();
			break;
		
		case "3":
			Pedido pedido = LeitoraDados.lerPedido(banco);
			Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

			if (cupom.isPresent()) {
				pedidoNegocio.salvar(pedido, cupom.get());
			} else {
				pedidoNegocio.salvar(pedido);
			}
			menu();
			break;
		case "4":
			System.out.println("Digite o código do pedido");
			String codigoPedido = LeitoraDados.lerDado();
			pedidoNegocio.excluir(codigoPedido);
			menu();
			break;
		case "5":
			produtoNegocio.listarTodos();
			menu();
			break;
		case "6":
			pedidoNegocio.listarPedidos();
			menu();
			break;
		case "7":
			System.out.println("  Volte sempre! \n//////////////////////////////////\n");
			clienteLogado = null;
			logar();
			menu();
			break;
		case "8":
			System.out.println("Aplicação encerrada.");
			System.exit(0);
			break;
		default:
			System.out.println("Opção inválida.");
			break;
		}
	}
}
