package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

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

			Delivery delivery = new Delivery();
			// em.persist(delivery);

			OrderItem orderItem1 = new OrderItem();
			OrderItem orderItem2 = new OrderItem();
			// em.persist(orderItem1);
			// em.persist(orderItem2);

			Order order = new Order();
			order.setDelivery(delivery);
			order.getOrderItems().add(orderItem1);
			order.getOrderItems().add(orderItem2);

			em.persist(order);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
