package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Aluno;
import util.JPAUtil;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
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
		em.getTransaction().begin();
		em.merge(aluno);
		em.getTransaction().commit();
		em.close();
	}

    public void excluir(Integer id) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            Aluno aluno = em.find(Aluno.class, id);
            if (aluno != null) {
                em.remove(aluno);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Aluno> listar() {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}

