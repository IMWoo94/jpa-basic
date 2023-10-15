package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import hellojpa.jpql.Member;

public class CriteriaMain {
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpql");

		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();

		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			// JPQL : select m from Member m
			String jpqlSql = "select m from Member m";

			// Criteria

			// 1. 쿼리 빌더 엔티티 매니저 또는 엔티티 매니저 팩토리에서 얻어오자
			// emf.getCriteriaBuilder();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

			// 2. criteriaBuilder 에서 criteria 쿼리를 생성하며, 이때 반환 타입을 지정할 수 있다.
			// CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
			CriteriaQuery<Object> query = criteriaBuilder.createQuery();

			// tuple 사용 시 tuple 지정 혹은 tuple 메소드 사용
			// CriteriaQuery<Tuple> tupleQuery1 = criteriaBuilder.createTupleQuery();
			// CriteriaQuery<Tuple> tupleQuery2 = criteriaBuilder.createQuery(Tuple.class);

			// 3. from 절을 생성한다. 반환 된 값 m 은 criteria 에서 사용하는 특별한 별칭 입니다.
			// m 을 조회의 시작점 이라는 의미로 쿼리 root 라 합니다.
			// Root<Member> m = query.from(Member.class);

			// 조건과 정렬 추가
			// 검색 조건
			// Predicate equal = criteriaBuilder.equal(m.get("username"), "회원1");
			// 정렬 desc(), asc()
			// Order desc = criteriaBuilder.desc(m.get("age"));

			// Predicate ageGt = criteriaBuilder.greaterThan(m.<Integer>get("age"), 10);

			// 단순 한건조회
			// query.select(m); // 단순 select 절

			// 조회 대상 여러건 지정
			// query.multiselect(m.get("username"), m.get("age")).distinct(true);

			// new -> construct()
			// query.select(criteriaBuilder.construct(MemberDTO.class, m.get("username"), m.get("age")));

			// 조건 정렬 select 문
			// query.select(m).where(equal).orderBy(desc);

			// 10살 초과 회원 조회 및 나이 역순 정렬
			// query.select(m).where(ageGt).orderBy(criteriaBuilder.desc(m.get("age")));

			// TypedQuery<Member> typedQuery = em.createQuery(query);
			// List<Member> resultList = typedQuery.getResultList();

			// em.createQuery(query).getResultList();

			// 조인
			Root<Member> m = query.from(Member.class);
			// 일반 조인
			// Join<Member, Team> t = m.join("team", JoinType.INNER);
			// query.multiselect(m, t);
			// fetch 조인
			m.fetch("team", JoinType.INNER);
			query.select(m);

			em.createQuery(query).getResultList();

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
