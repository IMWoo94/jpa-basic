package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

import hellojpa.jpql.Member;
import hellojpa.jpql.QMember;
import hellojpa.jpql.QOrder;
import hellojpa.jpql.Team;

public class QueryDslMain {
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
			member.setAge(100);
			Member member1 = new Member();
			member1.setUsername("testB");
			member1.setAge(90);
			Member member2 = new Member();
			member2.setUsername("testBBBB");
			member2.setAge(80);

			em.persist(member);
			em.persist(member1);
			em.persist(member2);

			Team team = new Team();
			team.setName("teamA");
			Team team1 = new Team();
			team1.setName("teamB");
			em.persist(team);
			em.persist(team1);

			// em.flush();
			// em.clear();

			// 기본 사용
			// JPAQuery query = new JPAQuery(em);
			// JPAQuery query1 = new JPAQuery(em);
			//
			// // 별도의 별칭을 제공
			// QMember qMember = new QMember("m"); // JPQL 사용 시 사용될 별칭 m
			//
			// List<Member> members = query.from(qMember)
			// 	.where(qMember.username.eq("회원1"))
			// 	.orderBy(qMember.username.desc())
			// 	.list(qMember);
			//
			// // 생성된 기본 인스턴스를 사용
			// List<Member> members1 = query1.from(member)
			// 	.where(member.username.eq("회원1"))
			// 	.orderBy(member.username.desc())
			// 	.list(member);

			// 검색 조건 쿼리
			// JPAQuery query = new JPAQuery(em);
			// QTeam qTeam = new QTeam("t");
			//
			// QueryModifiers queryModifiers = new QueryModifiers(2L, 0L); // limit, offset
			//
			// // 페이징 처리를 위해서 전체 데이터의 수를 알아오는 쿼리만 일단 생성
			// SearchResults<Team> teamSearchResults = query.from(qTeam)
			// 	.orderBy(qTeam.name.desc())
			// 	.restrict(queryModifiers) // .offset(0).limit(2)
			// 	.listResults(qTeam);
			//
			// // 전체 데이터 수 및 limit, offset 정보 확인 가능
			// teamSearchResults.getTotal();
			// teamSearchResults.getLimit();
			// teamSearchResults.getOffset();
			// // 실제 조회 데이터를 가져올 때 조회 데이터에 대한 페이징 처리와 쿼리 발생
			// List<Team> results = teamSearchResults.getResults();
			// for (Team result : results) {
			// 	System.out.println("result.getName() = " + result.getName());
			// }

			// 그룹
			// JPAQuery query = new JPAQuery(em);
			// QMember qMember = new QMember("m");
			//
			// query.from(qMember)
			// 	.groupBy(qMember.age)
			// 	.having(qMember.username.eq("hello"))
			// 	.list(qMember);

			// 조인
			// JPAQuery query = new JPAQuery(em);
			// QMember qMember = new QMember("m");
			// QTeam qTeam = new QTeam("t");

			// 일반 조인
			// query.from(qMember)
			// 	.join(qMember.team, qTeam)
			// 	.list(qMember);
			//
			// // 조인 on 사용
			// query.from(qMember)
			// 	.leftJoin(qMember.team, qTeam)
			// 	.on(qTeam.members.size().gt(0))
			// 	.list(qMember);

			// 페치 조인
			// query.from(qMember)
			// 	.join(qMember.team, qTeam).fetch()
			// 	.list(qMember);

			// 세타 조인
			// query.from(qMember, qTeam)
			// 	.where(qMember.team.name.eq(qTeam.name))
			// 	.list(qMember);

			// 서브 쿼리
			// JPAQuery query = new JPAQuery(em);
			// QMember qMember = new QMember("m");
			// QMember subMember = new QMember("sub");
			//
			// query.from(qMember)
			// 	.where(qMember.username.in(
			// 		new JPASubQuery().from(subMember).list(subMember.username)
			// 	)).list(qMember);

			// 프로젝션 결과 반환
			JPAQuery query = new JPAQuery(em);
			QMember qMember = new QMember("m");
			QOrder qOrder = new QOrder("o");

			// 결과 대상이 1개 -> 엔티티타입, 단순 타입 1개, 임베디드 타입
			// List<Address> list = query.from(qOrder)
			// 	.list(qOrder.address);

			// 프로젝션 반환이 여러 타입으로 하나의 타입 처리가 불가능 한 경우
			// tuple 타입으로 Map 과 비슷한 내부 타입을 사용
			// List<Tuple> list = query.from(qMember)
			// 	.list(qMember.username, qMember.age);
			//
			// for (Tuple tuple : list) {
			// 	System.out.println("tuple.get(qMember.username) = " + tuple.get(qMember.username));
			// 	System.out.println("tuple.get(qMember.age) = " + tuple.get(qMember.age));
			// 	System.out.println("tuple.get(0, QMember.class) = " + tuple.get(0, qMember.getClass()));
			// 	System.out.println("tuple.get(1, QMember.class) = " + tuple.get(1, QMember.class));
			// }

			// 특정 DTO 혹은 신규 객체로 생성하기
			// 프로퍼티 접근
			// List<MemberDTO> dtos = query.from(qMember)
			// 	// .list(
			// 	// Projections.bean(MemberDTO.class, qMember.username.as("username"), qMember.age));    // setter 로 접근
			// 	// .list(Projections.fields(MemberDTO.class, qMember.username.as("username"),
			// 	// 	qMember.age));    // 필드 직접 접근 private 가능
			// 	.list(Projections.constructor(MemberDTO.class, qMember.username.as("username"), qMember.age)); // 생성자 사용
			//
			// for (MemberDTO dto : dtos) {
			// 	System.out.println("dto.getUsername() = " + dto.getUsername());
			// 	System.out.println("dto.getAge() = " + dto.getAge());
			//
			// }

			// 수정, 삭제 배치 쿼리
			// JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, qMember);
			// long count = jpaUpdateClause.where(qMember.age.gt(10))
			// 	.set(qMember.age, qMember.age.add(100))
			// 	.execute();

			// JPADeleteClause jpaDeleteClause = new JPADeleteClause(em, qMember);
			// long count = jpaDeleteClause.where(qMember.username.eq("test"))
			// 	.execute();
			//
			// System.out.println("count = " + count);

			// 동적 쿼리
			BooleanBuilder builder = new BooleanBuilder();
			if (1 == 1) {
				builder.and(qMember.username.contains("test"));
			}
			if (1 != 1) {
				builder.and(qMember.age.gt(10));
			}

			query.from(qMember).where(builder).list(qMember);

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
