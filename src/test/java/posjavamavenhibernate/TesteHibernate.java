package posjavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

import java.util.ArrayList;
import java.util.List;

public class TesteHibernate {
	
	
	public void testeHibernateUtil() {		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa("Fernando", "Silva", "fernando@gmail.com", "fernando", "123", 35);
		daoGeneric.salvar(pessoa);		
	}
	
		
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
				
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1l , UsuarioPessoa.class);
		System.out.println(pessoa);		
		
//		pessoa = daoGeneric.pesquisar(pessoa);		

	}
	
	
	
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
				
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1l , UsuarioPessoa.class);
		pessoa.setEmail("fernando@bol5.com.br");	
		
		pessoa = daoGeneric.updateMerge(pessoa);			
		System.out.println(pessoa);	

	}
	
	
	public void testeDelete() {		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1l , UsuarioPessoa.class);		
		daoGeneric.deletarPorId(pessoa);		
	}
	
	
	public void testeConsultar() {		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();			
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------");
		}
	}
	
	
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------");
		}
	}
	
	
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().
				createQuery("from UsuarioPessoa order by id")
				.setMaxResults(10)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------");
		}
	}
	
	
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();		
		
		List<UsuarioPessoa> list = daoGeneric.
				getEntityManager().createQuery("from UsuarioPessoa where nome = :nome")
				.setParameter("nome", "Cintia").getResultList();		
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------");
		}
	}
	
	
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager().
				createQuery("select sum (u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("Soma de todas as idades -->" + somaIdade);	
	}
	
	
	public void testeNamedQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Fernando")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	
	public void testeGravaTelefone() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L ,UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser("Casa", "99999999", pessoa);
		
		daoGeneric.salvar(telefoneUser);
	}
	
	@Test
	public void testeConsultaTelefones() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L ,UsuarioPessoa.class);
		
		for (TelefoneUser telefone : pessoa.getTelefonesUsers()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getUsuarioPessoa().getNome());
			System.out.println("--------------------------------------");
		}

	}
	
	
	
}
