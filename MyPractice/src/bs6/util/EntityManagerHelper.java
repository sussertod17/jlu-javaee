package bs6.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

public class EntityManagerHelper {
	@PersistenceUnit(unitName = "mypractice")
	private static EntityManagerFactory emf;
	
	
	private static final ThreadLocal<EntityManager> threadLocal;
	static {
		//emf = Persistence.createEntityManagerFactory("mypractice");
		threadLocal = new ThreadLocal<EntityManager>();
	}

	public static EntityManager getEntityManager() {
		EntityManager manager = threadLocal.get();
		if (manager == null || !manager.isOpen()) {
			manager = emf.createEntityManager();

			threadLocal.set(manager);
		}
		return manager;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		threadLocal.set(null);
		if (em != null)
			em.close();
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();

	}

	public void commit() {
		getEntityManager().getTransaction().commit();
	}

	public static void createQuery(String query) {
		getEntityManager().createQuery(query);
	}

}
