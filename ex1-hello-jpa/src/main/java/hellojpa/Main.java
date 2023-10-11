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
			// Member findMember1 = em.find(Member.class, 1L);
			// System.out.println("=====================");
			// findMember1.setName("test");
			// System.out.println("=====================");

			// 플러시
			// Member member201 = new Member(201L, "member200");
			// em.persist(member201);
			// System.out.println("===================");
			// em.flush();
			// System.out.println("===================");

			// 준영속 상태로 만들기
			// Member findMember1 = em.find(Member.class, 1L);
			// findMember1.setName("aaaa");

			// em.detach(findMember1);
			// em.clear();
			// Member findMember2 = em.find(Member.class, 1L);
			// System.out.println("(findMember2 == findMember1) = " + (findMember2 == findMember1));

			// 병합 하기
			// Member findMember1 = em.find(Member.class, 1L);
			//
			// em.clear();
			// System.out.println("==========");
			// Member findMember2 = em.merge(findMember1);
			//
			// System.out.println("(findMember2 == findMember1) = " + (findMember2 == findMember1));

			// enum 타입 ORDINAL 쓰면 일어나느 참사
			// Member member = new Member();
			// member.setId(2L);
			// member.setUsername("test");
			// member.setRoleType(RoleType.USER);
			//
			// Member member1 = new Member();
			// member1.setId(3L);
			// member1.setUsername("test");
			// member1.setRoleType(RoleType.ADMIN);
			//
			// em.persist(member);
			// em.persist(member1);

			// @Id, @GeneratedValue 사용
			// Member member = new Member();
			// member.setUsername("A");
			// Member member1 = new Member();
			// member1.setUsername("B");
			// Member member2 = new Member();
			// member2.setUsername("C");
			// Member member3 = new Member();
			// member3.setUsername("D");
			//
			// em.persist(member);
			// System.out.println("11111111");
			// em.persist(member1);
			// System.out.println("2222222");
			// em.persist(member2);
			// System.out.println("3333333");
			// em.persist(member3);

			// 연관관계 RDB 관점 처리
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setTeamId(team.getId());
			em.persist(member);

			Member findMember = em.find(Member.class, member.getId());

			Long teamId = findMember.getTeamId();
			Team findTeam = em.find(Team.class, teamId);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}