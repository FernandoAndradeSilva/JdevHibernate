package posjavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	
	public void testeHibernateUtil() {		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa("Cintia", "Alves", "cintia@gmail.com", "cintia", "123", 28);
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
	
	@Test
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1l , UsuarioPessoa.class);		
		daoGeneric.deletarPorId(pessoa);
		
	}
	
	
}
