package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Aluno;
import util.JPAUtil;
import util.MessageUtil;

public class AlunoDAO {

	public static void salvar(Aluno aluno) {
		EntityManager em = JPAUtil.criarEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(aluno);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public static void editar(Aluno aluno) {
	    EntityManager em = JPAUtil.criarEntityManager();
	    try {
	    	if (aluno == null) {
	            // Se o aluno for null, exibe uma mensagem de erro
	            MessageUtil.addErrorMsg("Erro", "Aluno não encontrado para edição.");
	            return; // Interrompe a execução caso o aluno seja null
	        }
	    	//System.out.println(aluno.getNome());
	        em.getTransaction().begin();
	        em.merge(aluno); // Atualiza no banco
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        em.getTransaction().rollback();
	        throw e; // Lança a exceção para tratamento superior
	    } finally {
	        em.close();
	    }
	}



	
	public static void excluir(Aluno aluno) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		aluno = em.find(Aluno.class, aluno.getId());
		em.remove(aluno);
		em.getTransaction().commit();
		em.close();
	}


	public static List<Aluno> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		try {
			TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}


	


	
}
