package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();

		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			// insert
			// Member member = new Member();
			// member.setId(1L);
			// member.setName("helloA");
			// em.persist(member);

			// select
			// Member findMember = em.find(Member.class, 1L);

			// update
			// Member findMember = em.find(Member.class, 1L);
			// findMember.setName("HelloJpaA");

			// delete
			// Member findMember = em.find(Member.class, 1L);
			// em.remove(findMember);

			// create query -> JPQL
			// List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
			// for (Member member : result) {
			// 	System.out.println("member.getName() = " + member.getName());
			// }

			// 비영속 상태
			// Member member = new Member();
			// member.setId(1L);
			// member.setName("helloJPA");
			//
			// // 영속 상태
			// em.persist(member);
			//
			// // 준 영속 상태
			// em.detach(member);
			//
			// // 삭제
			// em.remove(member);

			// 1 차 캐시 확인
			// Member member = new Member();
			// member.setId(1L);
			// member.setName("helloJPA");
			//
			// System.out.println("=== BEFORE ===");
			// em.persist(member);
			// System.out.println("=== AFTER ===");

			// Member findMember1 = em.find(Member.class, 1L);
			// Member findMember2 = em.find(Member.class, 1L);
			// System.out.println("findMember.getName() = " + findMember.getName());
			// System.out.println("findMember.getId() = " + findMember.getId());

			// 동일성 보장
			// System.out.println("(findMember2 == findMember1) = " + (findMember2 == findMember1));

			// 쓰기 지연
			// Member member = new Member(103L, "sprinnA");
			// Member member1 = new Member(104L, "springB");
			//
			// em.persist(member);
			// System.out.println("*********************");
			// em.persist(member1);
			// System.out.println("=====================");

			// 변경 감지
			Member findMember1 = em.find(Member.class, 1L);
			findMember1.setName("test");

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}