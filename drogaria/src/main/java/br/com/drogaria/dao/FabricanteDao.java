package br.com.drogaria.dao;


import java.util.List;
import javax.persistence.EntityManager;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JPAUtil;
import br.com.drogaria.util.JSFUtil;


public class FabricanteDao {

	private Fabricante itens;
	private EntityManager em;
	
	public FabricanteDao() {
	}
	
	public FabricanteDao(EntityManager em){
		this.em = em; 
	}
	
	public Fabricante getItens() {
		return itens;
	}

	public void setItens(Fabricante itens) {
		this.itens = itens;
	}	
	
	public List<Fabricante> mostrarNomes(){
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		String jpql = "SELECT p FROM Fabricante p"; 
		return em.createQuery(jpql, Fabricante.class)
		.getResultList();
	}
		
	public void cadastrar(Fabricante fabricante) {
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		try {
			em.getTransaction().begin();
			this.em.persist(fabricante);
			em.getTransaction().commit();
			em.close();
			
			JSFUtil.adicionarMensagemDeSucesso("Fabricante cadastrado com sucesso");
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemDeErro(ex.getMessage());
		}
	}	
		
	public void atualizar(Fabricante fabricante) {
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		try {
			em.getTransaction().begin();
			this.em.merge(fabricante);
			em.getTransaction().commit();
			em.close();
			
			JSFUtil.adicionarMensagemDeSucesso("Fabricante editado com sucesso");
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemDeErro(ex.getMessage());
		}
	}
	
	public void excluir(Fabricante fabricante) {
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		try {
			em.getTransaction().begin();
			fabricante = em.merge(fabricante);
			this.em.remove(fabricante);
			em.getTransaction().commit();
			em.close();
			
			JSFUtil.adicionarMensagemDeSucesso("Fabricante excluído com sucesso");
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemDeErro(ex.getMessage());
		}
	}
		
	public Fabricante buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		this.em = em;
		
		em.getTransaction().begin();
		return em.find(Fabricante.class, id);
	}
	
	public List<Fabricante> buscarTodos(){
		String jpql = "SELECT p FROM Fabricante p";
		return em.createQuery(jpql, Fabricante.class).getResultList();
	}
	
	public List<Fabricante> buscarPorNome(String nome){
		String jpql = "SELECT p FROM Produto p Where p.nome = : buscarNome"; 
		return em.createQuery(jpql, Fabricante.class)
		.setParameter("buscarNome", nome) 
		.getResultList();
	}	
}