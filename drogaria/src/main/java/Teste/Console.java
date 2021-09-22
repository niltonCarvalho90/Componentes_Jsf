package Teste;

import br.com.drogaria.dao.ProdutoDao;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class Console {

	public static void main(String[] args) {
		
		Produto p = new Produto();
		Fabricante f = new Fabricante();
		f.setDescricao("BV");
		p.setFabricante(f);
		ProdutoDao Dao = new ProdutoDao();
		Dao.cadastrar(p);
		
		//EntityManager em = JPAUtil.getEntityManager();
		//FabricanteDao fdao = new FabricanteDao(em);
		//FabricanteBean FB = new FabricanteBean();
		
		//FB.prepararCadastro();
		

		
		/*em.getTransaction().begin();
		List<Fabricante> lista = fabricanteDao.mostrarNomes();
		System.out.println(lista);
		em.getTransaction().commit();
		em.close();*/
		
	}

}
