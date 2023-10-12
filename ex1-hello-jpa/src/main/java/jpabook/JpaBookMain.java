package jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaBookMain {
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();

		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			// @IdClass 사용 시 비식별
			// Parent parent = new Parent();
			// parent.setId1("myId1");
			// parent.setId2("myId2");
			// parent.setName("name");
			//
			// em.persist(parent);
			//
			// ParentId parentId = new ParentId("myId1", "myId2");
			// Parent findParent = em.find(Parent.class, parentId);

			// @EmbeddedId 사용 시 비 식별
			// Parent parent = new Parent();
			// ParentId parentId = new ParentId("myId1", "myId2");
			// parent.setId(parentId);
			// parent.setName("name");
			//
			// em.persist(parent);
			//
			// em.flush();
			// em.clear();
			//
			// Parent findParent = em.find(Parent.class, parentId);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
