package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

			// Team team = new Team();
			// team.setName("te");
			// em.persist(team);
			//
			// Member member = new Member();
			// member.setUsername("hello");
			// team.getMembers().add(member);
			//
			// em.persist(member);
			//
			// em.flush();
			// em.clear();
			//
			// Member findMember = em.find(Member.class, member.getId());
			//
			// System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
			// findMember.getTeam().getName();
			//
			// List<Member> members = em.createQuery("select m from Member m", Member.class)
			// 	.getResultList();

			// Member referenceMember = em.getReference(Member.class, member.getId());

			// em.detach(referenceMember);
			// org.hibernate.LazyInitializationException
			// System.out.println("============" + referenceMember.getUsername());

			// 프록시 인스턴스 초기화 여부 확인
			// PersistenceUnitUtil.isLoaded
			// System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(referenceMember));

			// JPA 에서는 프록시 객체를 초기화하는 표준은 없다.
			// 강제 초기화는 구현체에서 제공합니다 .따로
			// Hibernate.initialize(referenceMember);

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

			// Child child1 = new Child();
			// Child child2 = new Child();
			//
			// Parent parent = new Parent();
			// parent.addChild(child1);
			// parent.addChild(child2);
			//
			// em.persist(parent);
			// em.persist(child1);
			// em.persist(child2);
			//
			// em.flush();
			// em.clear();
			//
			// Parent parent1 = em.find(Parent.class, parent.getId());
			// em.remove(parent1);

			Address address = new Address("city", "street", "10000");
			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setHomeAddress(address);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setHomeAddress(address);
			em.persist(member2);

			// Address 값 타입을 setter 을 제거함으로서 생성 이후에 값이 변경되지 않도록 불변 객체 처리 하여서
			// 공유 참조가 되는 것을 막음 부작용이 생성되는 것을 막음
			member2.getHomeAddress().setCity("newCity");

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