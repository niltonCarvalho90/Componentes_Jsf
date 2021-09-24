package br.com.drogaria.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDao;
import br.com.drogaria.dao.ProdutoDao;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	
	
	private Produto nomeProd;
	private ArrayList<Fabricante> comboFabricantes;
	private transient ArrayList<Produto> itens;
	private transient ArrayList<Produto> itensFiltrados;
	
	public Produto getNomeProd() {
		return nomeProd;
	}
	
	public void setNomeProd(Produto nomeProd) {
		this.nomeProd = nomeProd;
	}
	
	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}
	
	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}
	
	public ArrayList<Produto> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	@PostConstruct
	public void mostrarLista() {
		ProdutoDao produtoDao = new ProdutoDao();
		
		itens = produtoDao.Lista();
	}
	
	public void prepararIncluir() {
		nomeProd  = new Produto();
		
		FabricanteDao Dao = new FabricanteDao();
		comboFabricantes = Dao.Lista();
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
