package br.com.poneyerp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.poneyerp.domain.Categoria;
import br.com.poneyerp.domain.Cidade;
import br.com.poneyerp.domain.Cliente;
import br.com.poneyerp.domain.Endereco;
import br.com.poneyerp.domain.Estado;
import br.com.poneyerp.domain.ItemPedido;
import br.com.poneyerp.domain.Pagamento;
import br.com.poneyerp.domain.PagamentoComBoleto;
import br.com.poneyerp.domain.PagamentoComCartao;
import br.com.poneyerp.domain.Pedido;
import br.com.poneyerp.domain.Produto;
import br.com.poneyerp.domain.enums.EstadoPagamento;
import br.com.poneyerp.domain.enums.TipoCliente;
import br.com.poneyerp.repositories.CategoriaRepository;
import br.com.poneyerp.repositories.CidadeRepository;
import br.com.poneyerp.repositories.ClienteRepository;
import br.com.poneyerp.repositories.EnderecoRepository;
import br.com.poneyerp.repositories.EstadoRepository;
import br.com.poneyerp.repositories.ItemPedidoRepository;
import br.com.poneyerp.repositories.PagamentoRepository;
import br.com.poneyerp.repositories.PedidoRepository;
import br.com.poneyerp.repositories.ProdutoRepository;

@SpringBootApplication
public class PoneyerpApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PoneyerpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		// Instânciando os objetos produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		/*
		 * Vinculando o produto com a lista de categoria que está relacionada ao produto
		 */
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		/* Define quais as categorias que estão relacionadas aos produtos */
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		// Salvando as categorias/produtos
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");

		Cidade c1 = new Cidade(null, "Cotia", est1);
		Cidade c2 = new Cidade(null, "Caucaia", est1);
		Cidade c3 = new Cidade(null, "Uberlândia", est2);

		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3));

		// Salvando as cidade/estado
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		// Criando os clientes
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com", "33355555500", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("255555555", "5559996665"));

		// criando os endereços e fazendo vinculo com o cliente
		Endereco e1 = new Endereco(null, "Rua jose lopes", "34", "casa 1", "Jd Eropa", "00667788", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua jose alves", "388", "casa 551", "Jd pa", "667788", cli1, c2);

		// adicionando os clientes aos endereços
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("10/02/2016 10:30"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("25/02/2016 10:30"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped2, sdf.parse("10/02/2016 10:30"),
				sdf.parse("10/02/2016 10:30"));
		ped2.setPagamento(pagto2);

		/* associando os pedidos ao cliente */
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2.000);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped1, p2, 100.00, 2, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
