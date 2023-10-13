package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpqlMain {
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();

		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			// jpql 직접 사용
			// List<Member> resultList = em.createQuery("select m from Member m where m.username like '%kim%'",
			// 	Member.class).getResultList();

			// Criteria 사용
			// CriteriaBuilder cb = em.getCriteriaBuilder();
			// CriteriaQuery<Member> query = cb.createQuery(Member.class);
			//
			// // 루트 클래스 ( 조회를 시작할 클래스 )
			// Root<Member> m = query.from(Member.class);
			//
			// // 쿼리 생성
			// CriteriaQuery<Member> cq = query.select(m);
			//
			// String username = "asd";
			// if (username != null) {
			// 	cq = cq.where(cb.equal(m.get("username"), "kim"));
			// }
			// List<Member> resultList = em.createQuery(cq).getResultList();

			// 네이티브 SQL
			// String sql = "Select * FROM MEMBER WHERE USERNAME = 'kim'";
			// List resultList = em.createNativeQuery(sql, Member.class).getResultList();

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