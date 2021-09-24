package br.com.drogaria.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.JPAUtil;
import br.com.drogaria.util.JSFUtil;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao() {
	}
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public ArrayList<Produto> Lista() {
		EntityManager em = JPAUtil.getEntityManager();
        this.em = em;

        String jpql = "SELECT p FROM Produto p";
        return (ArrayList<Produto>) em.createQuery(jpql, Produto.class).getResultList();
	}	

	public void cadastrar(Produto produto) {
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		try {
			em.getTransaction().begin();
			this.em.persist(produto);
			em.getTransaction().commit();
			em.close();
			
			JSFUtil.adicionarMensagemDeSucesso("Produto cadastrado com sucesso");
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemDeErro(ex.getMessage());
		}
		
	}	
	
	public void atualizar(Produto produto) {
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		try {
			em.getTransaction().begin();
			this.em.merge(produto);
			em.getTransaction().commit();
			em.close();
			
			JSFUtil.adicionarMensagemDeSucesso("Produto editado com sucesso");
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemDeErro(ex.getMessage());
		}
	}
	
	public void excluir(Produto produto) {
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		try {
			em.getTransaction().begin();
			produto = em.merge(produto);
			this.em.remove(produto);
			em.getTransaction().commit();
			em.close();
			
			JSFUtil.adicionarMensagemDeSucesso("Produto excluído com sucesso");
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemDeErro(ex.getMessage());
		}
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		String jpql = "SELECT p FROM Produto p Where p.nome = : buscarNome"; 
		return em.createQuery(jpql, Produto.class)
		.setParameter("buscarNome", nome) 
		.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome(String nome){
		String jpql = "SELECT p.preco FROM Produto p Where p.nome = :buscarPrecoNome"; 
		return em.createQuery(jpql, BigDecimal.class)
		.setParameter("buscarPrecoNome", nome) 
		.getSingleResult();
	}
	
}
