package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import hellojpa.jpql.Member;

public class Main {
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpql");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();

		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			Member member = new Member();
			member.setUsername("test");
			em.persist(member);

			// TypeQuery 반환 타입이 명확
			TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
			TypedQuery<String> query1 = em.createQuery("select m.username from Member m", String.class);

			// Query 반환 타입이 명확하지 않을 때 사용
			Query query2 = em.createQuery("select m.username, m.age from Member m");

			// getResultList -> 결과 List 형식으로 제공 단, 결과값 없으면 빈 리스트 반환
			// 파라미터 바인딩 - 이름 기준
			TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.username =:username",
				Member.class).setParameter("username", "empty");
			System.out.println("getResultList -> 빈 리스트 확인 " + query3.getResultList().isEmpty());
			// System.out.println("getSingleResult -> 결과가 무조건 1개 아니면 오류 발생 " + query.getSingleResult());
			// System.out.println("getSingleResult -> 결과가 없어도 오류 발생 " + query3.getSingleResult());

			// 파라미터 바인딩 - 위치기준
			TypedQuery<Member> query4 = em.createQuery("select m from Member m where m.username =?0",
				Member.class).setParameter(0, member.getUsername());
			query4.getSingleResult();
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