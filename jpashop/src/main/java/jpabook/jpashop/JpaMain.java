package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {

		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();

		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			// Order order = em.find(Order.class, 1L);
			// Long memberId = order.getMemberId();
			// Member member = em.find(Member.class, memberId);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}