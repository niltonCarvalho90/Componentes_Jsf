package br.com.drogaria.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDao;
import br.com.drogaria.domain.Fabricante;



@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	
	private Fabricante nomeFab;
	private transient ArrayList<Fabricante> itens;
	private transient ArrayList<Fabricante> itensFiltrados;
	
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public Fabricante getFabricante() {
		return nomeFab;
	}

	public void setFabricante(Fabricante nomeFab) {
		this.nomeFab = nomeFab;
	}

	@PostConstruct
	public void mostrarLista() {
		FabricanteDao Dao = new FabricanteDao();
				
		itens = Dao.Lista();
	}
	
	public void prepararIncluir(){
		nomeFab  = new Fabricante();
	}
	
	public void incluir() {
		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.cadastrar(nomeFab);
		
		mostrarLista();
	}
	
	public void remover() {
		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.excluir(nomeFab);
		
		mostrarLista();
	}
	
	public void atualizar() {
		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.atualizar(nomeFab);
		
		mostrarLista();
	}
}
