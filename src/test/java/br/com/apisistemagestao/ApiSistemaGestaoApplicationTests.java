package br.com.apisistemagestao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import br.com.apisistemagestao.domain.entity.Categoria;
import br.com.apisistemagestao.domain.entity.Cidade;
import br.com.apisistemagestao.domain.entity.Cliente;
import br.com.apisistemagestao.domain.entity.Compra;


import br.com.apisistemagestao.domain.entity.Fornecedor;
import br.com.apisistemagestao.domain.entity.Funcionario;
import br.com.apisistemagestao.domain.entity.ItemCompra;
import br.com.apisistemagestao.domain.entity.ItemVenda;

import br.com.apisistemagestao.domain.entity.Permissao;
import br.com.apisistemagestao.domain.entity.Produto;

import br.com.apisistemagestao.domain.entity.StatusCompra;
import br.com.apisistemagestao.domain.entity.StatusVenda;
import br.com.apisistemagestao.domain.entity.TipoPessoa;
import br.com.apisistemagestao.domain.entity.TipoProduto;
import br.com.apisistemagestao.domain.entity.TipoVenda;
import br.com.apisistemagestao.domain.entity.Usuario;
import br.com.apisistemagestao.domain.entity.Venda;
import br.com.apisistemagestao.domain.repository.ContaaPagarRepositoty;
import br.com.apisistemagestao.domain.repository.FuncionarioRepository;
import br.com.apisistemagestao.domain.repository.PermissoesRpository;
import br.com.apisistemagestao.domain.repository.ProdutoReposistory;
import br.com.apisistemagestao.domain.repository.UsuarioRepository;

import br.com.apisistemagestao.infra.ContasFilter;
import br.com.apisistemagestao.serivces.CategoriaService;
import br.com.apisistemagestao.serivces.ClienteService;
import br.com.apisistemagestao.serivces.CompraService;
import br.com.apisistemagestao.serivces.FornecedorService;
import br.com.apisistemagestao.serivces.ProdutoService;
import br.com.apisistemagestao.serivces.VendaService;

@SpringBootTest
class ApiSistemaGestaoApplicationTests {
	@Autowired
    private  UsuarioRepository usuarorepo ;
	@Autowired
	private  FuncionarioRepository funcrepo;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private PermissoesRpository permi;
	@Autowired
	private ProdutoService produtoservice;
	@Autowired
	private CategoriaService categoriaservice;
	@Autowired
	private VendaService vendaservice;
	@Autowired
	ClienteService clieservice;
	@Autowired
	private ProdutoReposistory produtorepo;
	@Autowired
	private FornecedorService fornecedorservice;
	@Autowired
	private CompraService compraservic;
	@Autowired
	private ContaaPagarRepositoty contarepositrory;
	//@Test
	void pesquisar () {
		java.util.List<Produto>produtos= produtorepo.findByNomeprodutoContains("JAVA ");
		System.out.println(produtos.size());
	}
	void salvarvenda() {
		Optional<Produto> proOptional = produtoservice.buscarporcodigo(4l);
		Optional<Usuario> uOptional = usuarorepo.findById(1l);
		Venda venda = new Venda();
		ItemVenda itemVenda = new ItemVenda();
		Optional<Cliente>cliOptional= clieservice.buscarporcodigo(3l);
		venda.setUsario(uOptional.get());
		venda.setCliente(cliOptional.get());
		itemVenda.setQtdeItem(new BigDecimal(3));
		itemVenda.setSubTotal(itemVenda.getQtdeItem().multiply(proOptional.get().getPrecodevenda()));
		itemVenda.setProduto(proOptional.get());
		itemVenda.setDesconto(new BigDecimal(20));
		venda.setTipovenda(TipoVenda.Venda);
		venda.setDatahoravenda(LocalDateTime.now());
		venda.setDatavenda(LocalDate.now());
		
		venda.setStatusVenda(StatusVenda.Finalizada);
		venda.getItens().add(itemVenda);
		
		System.out.println(venda.getCliente().getNomePessoa());
		Optional<Produto> proOptional1 = produtoservice.buscarporcodigo(2l);
		ItemVenda itemVenda2 = new ItemVenda();
		itemVenda2.setProduto(proOptional1.get());
		itemVenda2.setQtdeItem(new BigDecimal(5));
		itemVenda2.setSubTotal(itemVenda2.getQtdeItem().multiply(proOptional1.get().getPrecodevenda()));
		itemVenda2.setProduto(proOptional1.get());
		itemVenda2.setDesconto(new BigDecimal(40));
		venda.getItens().add(itemVenda2);
		venda.setToTalVenda(itemVenda.getSubTotal().add(itemVenda2.getSubTotal()));
		vendaservice.salvar(venda);
		System.out.println(venda.getToTalVenda());
		System.out.println("salvo com sucesso ");
		
	}
	
	void salvarproduto() {
		Optional<Categoria> categoria = categoriaservice.buscarporcodigo(3l);
		Produto produto = new Produto();
		produto.setCategoria(categoria.get());
		produto.setAtivo(true);
		produto.setNomeproduto("HD EXETERNOr ");
		produto.setMarca("SEAGATE");
		produto.setTipoproduto(TipoProduto.FISÍCO);
		produto.setPrecocusto(new BigDecimal(350));
	//	produto.setPrecodevenda(new BigDecimal(40));
		produtoservice.salvar(produto);
	}
	///@Test
	void contextLoads() {
		Funcionario func = new Funcionario();
		;
		Cidade cidade  = new Cidade();
		cidade.setCodigo_cidade(4071l);
        func.setBairro("Centro");
        func.setCelular("4789847878");
        func.setCep("8797878");
        func.setComplemto("casa");
        func.setCidade(cidade);
        func.setCpfouCnpj("87827352634");
        func.setTipessoa(TipoPessoa.FÍSICA);
        func.setRgouInscricao("457875478878");
        func.setDataNiverouFundacao(LocalDate.of(1977, 10, 15));
        func.setEmail("func2@suaempresa.com");
        func.setNomePessoa("Messias da silva");
        func.setLogradouro("rua parati");
        func.setSalario(new BigDecimal(1750));
       funcrepo.save(func);
       System.out.println("salvo com suceso");
}
  ///  @Test
	void salvaruser() {
		  String senha="123";
		String  senhaendodadad= pe.encode(senha);
		   System.out.println(senhaendodadad);
		   Optional	  <  Funcionario> func = funcrepo.findById(1l);
          Optional	  <  Funcionario> func1 = funcrepo.findById(2l);
	
		Usuario usuario = new Usuario();
		Usuario user1= new Usuario();
	usuario.setFuncionario(func.get());
 ///  usuario.setEmail("bethmaria_07@hotamil.com");
	usuario.setSenha( pe.encode(senha) );
	user1.setFuncionario(func1.get());
	user1.setSenha(pe.encode("12345"));
	///	usuario.addPerfil(Perfil.CLIENTE);
	///	usuario.setNome("ELIBETH FERNANDES");
	
		Permissao p1 =new Permissao();
		          p1.setDescricao("ROLE_CADASTRAR_CATEGORIA");
		          
		Permissao p2 = new Permissao();
		p2.setDescricao("ROLE_PESQUISAR_CATEGORIA");
		Permissao p3 = new Permissao();
		p3.setDescricao("ROLE_REMOVER_CATEGORIA");
		Permissao p4 = new Permissao();
		p4.setDescricao("ROLE_CADASTRAR_BANCO");
		Permissao p5 = new Permissao();
		p5.setDescricao("ROLE_PESQUISAR_BANCO");
		
	
		Permissao p6 = new Permissao();
		p6.setDescricao("ROLE_REMOVER_BANCO");
		Permissao p7 = new Permissao();
		p7.setDescricao("ROLE_CADASTRAR_CLIENTE");
		Permissao p8 = new Permissao();
		p8.setDescricao("ROLE_PESQUISAR_CLIENTE");
		Permissao p9 = new Permissao();
		p9.setDescricao("ROLE_REMOVER_CLIENTE");
	
		Permissao p10 = new Permissao();
		p10.setDescricao("ROLE_CADASTRAR_PRODUTO");
		Permissao p11 = new Permissao();
		p11.setDescricao("ROLE_PESQUISAR_PRODUTO");
		Permissao p12 = new Permissao();
		p12.setDescricao("ROLE_REMOVER_PRODUTO");
		
		
        usuario.getPermissoes().addAll((Arrays.asList(p1,p2,p3,p5)));
        user1.getPermissoes().addAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12));
         usuarorepo.saveAll(Arrays.asList(user1,usuario));
//		usuario.getPermissoes().add(p1);
//		usuario.getPermissoes().add(p2)
//		          for (int i=0;i<usuario.getPermissoes().size();i++) {
//		        	permi.save(usuario.getPermissoes().get(i)); 
//		          }
  /// 
       System.out.println("salvo com suceso");
	  }
	
	  void alterar() {
    Usuario user = usuarorepo.buscarEmail("func1@suaempresa.com");
//	Permissao p4 = new Permissao();
//	p4.setDescricao("ROLE_CADASTRAR_CONDICAO");
	Permissao p5 = new Permissao();
	p5.setDescricao("ROLE_PESQUISAR_CONDICAO");
 //	Permissao p6 = new Permissao();
//	p6.setDescricao("ROLE_EXCLUIR_CONDICAO");
//	Permissao p7 = new Permissao();
//	p7.setDescricao("ROLE_CADASTRAR_FORNECEDOR");
	Permissao p8 = new Permissao();
	p8.setDescricao("ROLE_PESQUISAR_FORNECEDOR");
//	Permissao p9 = new Permissao();
//	p9.setDescricao("ROLE_EXCLUIR_FORNECEDOR");

//	Permissao p10 = new Permissao();
//	p10.setDescricao("ROLE_CADASTRAR_CONTAVIMENTO");
//	Permissao p11 = new Permissao();
//	p11.setDescricao("ROLE_PESQUISAR_CONTAVIMENTO");
//	Permissao p12 = new Permissao();
//	p12.setDescricao("ROLE_CANCELAR_CONTAVIMENTO");
	  user.getPermissoes().addAll(Arrays.asList(p5,p8));
		usuarorepo.save(user);
	  }
	
  @Test
		void salvarcompra() {
			Compra compra = new Compra();
			Optional<Produto> produto = produtoservice.buscarporcodigo(1l);
          ///produto.get().setCategoria(null);
 ///         produto.get().setTipoproduto(null);
		produto.get().setUltiMocusto_pago(produto.get().getPrecocusto());
			ItemCompra itenCompras = new ItemCompra();
			Optional<Fornecedor> forneOptional = fornecedorservice.buscarporcodigo(3l);
	produto.get().setDataultimacmpra(LocalDate.now());
	produto.get().setUltiMocusto_pago(produto.get().getPrecocusto());
          compra.setIdfonecedor(forneOptional.get().getCodigo());
			itenCompras.setQtde(new BigDecimal(10));
compra.setMarkqup(new BigDecimal(1.7));
		//compra.setNumeronota(1345688l);

			itenCompras.setCompra(compra);
		
			compra.setFornecedor(forneOptional.get());

		///	produto.get().setQtde((produto.get().getQtde().add(itenCompras.getQtde())));
			itenCompras.setSubtotal(itenCompras.getQtde().multiply(produto.get().getPrecocusto()));

			compra.setOutrasdepesza(new BigDecimal("20"));
			compra.setValorfrete(new BigDecimal("50"));
			compra.setValorseguro(new BigDecimal("15"));
			compra.setDesconto(new BigDecimal(30));
			compra.setDataCompra(LocalDate.now());
			compra.setFornecedor(forneOptional.get());
			compra.setTotalproduto(itenCompras.getSubtotal());
			System.out.println("Subtotal "+itenCompras.getSubtotal());
		
			compra.setStatusCompra(StatusCompra.Entregue);

			compra.setDatadeentrada(LocalDateTime.now());

			compra.setDataCompra(LocalDate.now().minusDays(5));
			itenCompras.setProduto(produto.get());
			
			

			compra.setNumeronota(135l);

			itenCompras.setCompra(compra);

		

		
//			itenCompras.setSubtotal(itenCompras.getQtde().multiply(produto.get().getPrecocusto()));

			compra.getItemcompras().add(itenCompras);
			// compra.setDatadeentrada(LocalDate.now());
			System.out.println(compra.getTotalCompra());
			System.out.println("Qte atual estoque :" + produto.get().getQteestoque());
			System.out.println("subtotal 1 :"  + itenCompras.getSubtotal());
			System.out.println(produto.get().getNomeproduto());
			System.out.println(produto.get().getPrecodevenda());
			Optional<Produto> produto1 = produtoservice.buscarporcodigo(2l);
			produto1.get().setDataultimacmpra(LocalDate.now());
			produto1.get().setUltiMocusto_pago(produto1.get().getPrecocusto());

			// Produto produto =produto1.get();
		ItemCompra itenCompras1 = new ItemCompra();
	//	
//			produto1.get().setPrecoCusto(new BigDecimal(455.55D));
//	       
		itenCompras1.setQtde(new BigDecimal(15));
			itenCompras1.setCompra(compra);
			itenCompras1.setProduto(produto1.get());
			produto1.get().setUltiMocusto_pago(produto1.get().getPrecodevenda());
			itenCompras1.setSubtotal(itenCompras1.getQtde().multiply(produto1.get().getPrecocusto()));
			compra.getItemcompras().add(itenCompras1);
			
			System.out.println("Subto total final "+itenCompras1.getSubtotal());
//			produto1.get().setQtde((produto1.get().getQtde().add(itenCompras1.getQtde())));
//			itenCompras1.setSubtotal(itenCompras.getQtde().multiply(produto1.get().getPrecoCusto()));
//			produto1.get().setPrecovenda(compra.getTotalnota().divide(itenCompras1.getQtde(), 4, RoundingMode.HALF_DOWN)
//					.multiply(new BigDecimal(1.7d)));
//			produto.get().setCustomedio(compra.getTotalnota().divide(itenCompras1.getQtde(), 4, RoundingMode.HALF_DOWN));
//			itenCompras1.setCustototal(compra.getTotalnota());
//			compra.setDataCompra(LocalDate.now().minusDays(30));
//			itenCompras.setProduto(produto.get());
	//	System.out.println("ToTal "+compra.getTotalproduto());//			itenCompras.setQtde(new BigDecimal(10d));
			compra.setTotalproduto(compra.getTotalproduto().add(itenCompras.getSubtotal().add(itenCompras1.getSubtotal())));
      compra.setTotalCompra(compra.getTotalCompra().add(itenCompras.getSubtotal().
    		  add(itenCompras1.getSubtotal()).
    		  add(compra.getValorfrete().add(compra.getValorseguro().
    				  add(compra.getOutrasdepesza().subtract(compra.getDesconto()))))));
      System.out.println(" total final "+compra.getTotalCompra());
//      ContasPagar contaaPagar = new ContasPagar();
//      contaaPagar.setFornecedor(forneOptional.get());
//      contaaPagar.setDatalancamento(LocalDate.now());
//			Integer numparcela = 0;
//			for (int pos = 0; pos < contaaPagar.getFornecedor().getCondicaoPamento().getQtdeparcelas(); pos++) {
//			     ContasPagarDetalhe contadetalhe = new ContasPagarDetalhe();
//				numparcela = pos + 1;
////				contaaPagar.setCompra(compra);
//        	contadetalhe.setFormadePagmamento(contaaPagar.getFornecedor().getCondicaoPamento().getColecaoparcelas().get(pos).getFormadePagamento());;
////				contaaPagar.setNumtitulo(compra.getNumeronota());
//        	  contadetalhe.setNumtitulo(compra.getNumeronota());
//        	  contadetalhe.setStatuspagmaento(StatuPagamento.PENDENTE);
//        	  contadetalhe.setNumparcela(numparcela);
//        	  contadetalhe.setDatavencimento(compra.getDataCompra().plusDays(contaaPagar.getFornecedor().getCondicaoPamento().getColecaoparcelas().get(pos).getDia()));
//        	  contadetalhe.setValoparcela(compra.getTotalCompra().multiply(contaaPagar.getFornecedor().getCondicaoPamento().getColecaoparcelas()
//        			  .get(pos).getPercentual().divide(new BigDecimal(100))));
//        	  contadetalhe.setValorapagar(contadetalhe.getValoparcela());
//        	///  contaaPagar.getContasdetalhe().add(contadetalhe);
//        	  contadetalhe.setContasPagar(contaaPagar);
//				contaaPagar.setFormadepagamento(
//						forneOptional.get().getCondicaoPamento().getColecaoparcelas().get(pos).getFormadePagamento());
//				contaaPagar.setCondicaoPagamento(forneOptional.get().getCondicaoPamento());
//				contaaPagar.setFornecedor(forneOptional.get());
//
//				contaaPagar.setStatuspagmaento(EstadoPagamento.PENDENTE);
//
//				/// contaaPagar.setNumerodaparcela(numparcela);
//				System.out.println(contaaPagar.getStatuspagmaento());
//
//				contaaPagar.gerardatavencimeto(compra.getDataCompra(),
//						forneOptional.get().getCondicaoPamento().getColecaoparcelas().get(pos).getDia());
//
//				contaaPagar.setDatalancamento(LocalDate.now());
//				System.out.println("DAta " + contaaPagar.getDatavencimento());
//				System.out.println(forneOptional.get().getCondicaoPamento().getColecaoparcelas().get(pos).getDia());
//
//				/// System.out.println(contaaPagar.getNumerodaparcela());
//				System.out.println(contaaPagar.getDatalancamento());
//				System.out.println("VAlor da nota : " + compra.getTotalnota());
//
//				contaaPagar.setValoparcela(compra.getTotalnota().multiply(forneOptional.get().getCondicaoPamento()
//						.getColecaoparcelas().get(pos).getPercentual().divide(new BigDecimal(100d))));
//				contaaPagar.setValorapagar(contaaPagar.getValoparcela());
//				System.out.println("VAlor Parcela : " + contaaPagar.getValoparcela());
//				compra.getContas().add(contaaPagar);
//
	//	/}
			///compra.setConta(contaaPagar);
			compraservic.salvar(compra);
			/// produtoservice.salvar(produto.get());
			/// daocompra.save(compra);
			System.out.println("salvo com sucesso ");
		}
    
		void buscarItens() {
		Funcionario func = new Funcionario();
			func.setEmail("func2@suaempresa.com");
			
			
			func = funcrepo.findByEmail(func.getEmail());
			
			System.out.println(func.getNomePessoa());
	
		}
		
		void darbaixaconta() {
	  ContasFilter		contasfilter = new ContasFilter();
	  contasfilter.setCnpj("24856574000124");
///	  ContasPagarDetalhe contdetalhe = contarepositrory.localizar(contasfilter);
	
	  

	  

//		  System.out.println(	  contdetalhe.getContasPagar().getFornecedor().getNomePessoa());
//		  System.out.println(contdetalhe.getValoparcela());
	  

		}	
}
