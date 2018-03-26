package net.thiago.mc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.thiago.mc.domain.Categoria;
import net.thiago.mc.domain.Cidade;
import net.thiago.mc.domain.Cliente;
import net.thiago.mc.domain.Endereco;
import net.thiago.mc.domain.Estado;
import net.thiago.mc.domain.ItemPedido;
import net.thiago.mc.domain.Pagamento;
import net.thiago.mc.domain.PagamentoComBoleto;
import net.thiago.mc.domain.PagamentoComCartao;
import net.thiago.mc.domain.Pedido;
import net.thiago.mc.domain.Produto;
import net.thiago.mc.domain.enums.EstadoPagamento;
import net.thiago.mc.domain.enums.TipoCliente;
import net.thiago.mc.repositories.CategoriaRepository;
import net.thiago.mc.repositories.CidadeRepository;
import net.thiago.mc.repositories.ClienteRepository;
import net.thiago.mc.repositories.EnderecoRepository;
import net.thiago.mc.repositories.EstadoRepository;
import net.thiago.mc.repositories.ItemPedidoRepository;
import net.thiago.mc.repositories.PagamentoRepository;
import net.thiago.mc.repositories.PedidoRepository;
import net.thiago.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repository;

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
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Eletrônicos");
		Categoria categoria4 = new Categoria(null, "Móveis");
		Categoria categoria5 = new Categoria(null, "Telefonia");
		Categoria categoria6 = new Categoria(null, "Decoração");
		Categoria categoria7 = new Categoria(null, "Perfumaria");

		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));

		this.repository.saveAll(
				Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
		this.produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		this.estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		this.cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Thiago", "thiago@email.com", "003.696.201-51", TipoCliente.PESSOA_FISICA);
		cliente1.getTelefones().addAll(Arrays.asList("(67)99285-2824", "(67)3386-3543"));

		Endereco endereco1 = new Endereco(null, "Rua", "44", "Casa", "União", "79.092-420", cliente1, cidade3);
		Endereco endereco2 = new Endereco(null, "Avenida", "2344", "Apartamento", "Flores", "79.092-420", cliente1,
				cidade2);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		this.clienteRepository.saveAll(Arrays.asList(cliente1));
		this.enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido pedido1 = new Pedido(null, dateFormat.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, dateFormat.parse("10/10/2017 19:35"), cliente1, endereco2);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				dateFormat.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		this.pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		this.pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));

		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));

		this.itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));

	}
}
