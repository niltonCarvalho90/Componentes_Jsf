package br.com.drogaria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDao;
import br.com.drogaria.domain.Produto;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	
	private Produto nomeProd;
	private transient ArrayList<Produto> itens;
	
	public ArrayList<Produto> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	
	public Produto getNomeProd() {
		return nomeProd;
	}
	
	public void setNomeProd(Produto nomeProd) {
		this.nomeProd = nomeProd;
	}
	
	@PostConstruct
	public void mostrarLista() {
		ProdutoDao produtoDao = new ProdutoDao();
		
		List<Produto> lista = produtoDao.mostrarNomes();
		itens = new ArrayList<Produto>(lista);
	}
	
	public void prepararIncluir() {
		nomeProd  = new Produto();
	}
	
	public void incluir() {
		ProdutoDao produtoDao = new ProdutoDao();
		produtoDao.cadastrar(nomeProd);
		
		mostrarLista();
	}
	
	public void remover() {
		ProdutoDao produtoDao = new ProdutoDao();
		produtoDao.excluir(nomeProd);
		
		mostrarLista();
	}
	
	public void atualizar() {
		ProdutoDao produtoDao = new ProdutoDao();
		produtoDao.atualizar(nomeProd);
		
		mostrarLista();
	}
}
