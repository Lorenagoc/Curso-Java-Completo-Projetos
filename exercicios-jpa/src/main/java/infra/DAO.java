package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		} catch (Exception e) {
			// logar -> log4j
		}
	}

	public DAO() {
		this(null);
	}

	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}

	public DAO<E> abrirTransacao() {
		em.getTransaction().begin();
		return this;
	}

	public DAO<E> commitTransacao() {
		em.getTransaction().commit();
		return this;
	}

	public DAO<E> incluirTransacao(E entidade) {
		em.persist(entidade);
		return this;
	}

	public DAO<E> incluirAtomicamente(E entidade) {
		return this.abrirTransacao().incluirTransacao(entidade).commitTransacao();
	}

	public List<E> obterTodos() {
		return obterTodos(10, 0); // default
	}

	public List<E> obterTodos(int limit, int offset) {
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		} else {
			String jpql = "SELECT e FROM " + classe.getName() + " e";
			TypedQuery<E> query = em.createQuery(jpql, classe);
			query.setMaxResults(limit).setFirstResult(offset);
			return query.getResultList();
		}
	}

	public E obterPorId(Object id) {
		return em.find(classe, id);
	}

	public void fecharDAO() {
		em.close();
		// emf não será fechado pq ele vai servir para todos os DAOs gerados
	}
}
