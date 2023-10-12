package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class JpaMain {
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();

		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			Member member = new Member();
			member.setUsername("hello");

			em.persist(member);

			em.flush();
			em.clear();

			// Member findMember = em.find(Member.class, member.getId());
			Member referenceMember = em.getReference(Member.class, member.getId());

			// em.detach(referenceMember);
			// org.hibernate.LazyInitializationException
			// System.out.println("============" + referenceMember.getUsername());

			// 프록시 인스턴스 초기화 여부 확인
			// PersistenceUnitUtil.isLoaded
			System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(referenceMember));

			// JPA 에서는 프록시 객체를 초기화하는 표준은 없다.
			// 강제 초기화는 구현체에서 제공합니다 .따로
			Hibernate.initialize(referenceMember);

			// em.detach(referenceMember);
			//
			// referenceMember.setUsername("test");

			// System.out.println("referenceMember = " + referenceMember.getClass());
			// System.out.println("referenceMember.getId() = " + referenceMember.getId());
			// System.out.println("referenceMember.getUsername() = " + referenceMember.getUsername());
			//
			// Member reMember = em.find(Member.class, 1L);
			// System.out.println("reMember.getClass() = " + reMember.getClass());
			// System.out.println("reMember.getId() = " + reMember.getId());
			// System.out.println("reMember.getUsername() = " + reMember.getUsername());
			//
			// System.out.println("proxy == origin = " + (member == referenceMember));
			// System.out.println("proxy instance of oriing = " + (reMember instanceof Member));
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}
}