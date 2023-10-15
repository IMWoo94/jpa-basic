package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.jpql.Member;
import hellojpa.jpql.Team;

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

			// Member member = new Member();
			// member.setUsername("test");
			// em.persist(member);
			//
			// // TypeQuery 반환 타입이 명확
			// TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
			// TypedQuery<String> query1 = em.createQuery("select m.username from Member m", String.class);
			//
			// // Query 반환 타입이 명확하지 않을 때 사용
			// Query query2 = em.createQuery("select m.username, m.age from Member m");
			//
			// // getResultList -> 결과 List 형식으로 제공 단, 결과값 없으면 빈 리스트 반환
			// // 파라미터 바인딩 - 이름 기준
			// TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.username =:username",
			// 	Member.class).setParameter("username", "empty");
			// System.out.println("getResultList -> 빈 리스트 확인 " + query3.getResultList().isEmpty());
			// // System.out.println("getSingleResult -> 결과가 무조건 1개 아니면 오류 발생 " + query.getSingleResult());
			// // System.out.println("getSingleResult -> 결과가 없어도 오류 발생 " + query3.getSingleResult());
			//
			// // 파라미터 바인딩 - 위치기준
			// TypedQuery<Member> query4 = em.createQuery("select m from Member m where m.username =?0",
			// 	Member.class).setParameter(0, member.getUsername());
			// query4.getSingleResult();

			// 프로젝션

			// Member member = new Member();
			// member.setUsername("test");
			//
			// Team team = new Team();
			// team.setName("teamA");
			// em.persist(team);
			//
			// member.setTeam(team);
			//
			// em.persist(member);
			//
			// em.flush();
			// em.clear();

			// 엔티티 프로젝션
			// TypedQuery<Team> query = em.createQuery("select m.team from Member m", Team.class);

			// 임베디드 타입 프로젝션
			// TypedQuery<Address> query = em.createQuery("select o.address from Order o", Address.class);

			// 스칼라 타입 프로젝션
			// Query query = em.createQuery("select m.username, m.age from Member m");

			// 여러 타입으 조회 하는 경우 반환 경우
			// List resultList = query.getResultList();
			//
			// // 방안 1 일반
			// Iterator iterator = resultList.iterator();
			// while (iterator.hasNext()) {
			// 	Object[] row = (Object[])iterator.next();
			// 	String username = (String)row[0];
			// 	Integer age = (Integer)row[1];
			// }

			// 방안 2 제네릭
			// List<Object[]> resultList = query.getResultList();
			//
			// for (Object[] objects : resultList) {
			// 	String username = (String)objects[0];
			// 	Integer age = (Integer)objects[1];
			// }

			// 방안 3 new 예약어
			// TypedQuery<MemberDTO> query = em.createQuery(
			// 	"select new hellojpa.jpql.MemberDTO(m.username, m.age) from Member m",
			// 	MemberDTO.class);
			//
			// List<MemberDTO> resultList = query.getResultList();

			// 페이징 API
			// Member member1 = new Member();
			// member1.setUsername("1");
			// Member member2 = new Member();
			// member2.setUsername("2");
			// Member member3 = new Member();
			// member3.setUsername("3");
			// Member member4 = new Member();
			// member4.setUsername("4");
			// Member member5 = new Member();
			// member5.setUsername("5");
			//
			// em.persist(member1);
			// em.persist(member2);
			// em.persist(member3);
			// em.persist(member4);
			// em.persist(member5);
			//
			// List<Member> resultList = em.createQuery("select m from Member m order by m.username desc", Member.class)
			// 	.setFirstResult(0).setMaxResults(3).getResultList();
			// int firstResult = em.createQuery("select m from Member m order by m.username desc", Member.class)
			// 	.getFirstResult();
			//
			// for (Member findMember : resultList) {
			// 	System.out.println("findMember.getUsername() = " + findMember.getUsername());
			// }
			// System.out.println("firstResult = " + firstResult);
			// System.out.println("resultList.size() = " + resultList.size());

			// 조인
			Member member = new Member();
			member.setUsername("test");
			member.setAge(100);
			Member member1 = new Member();
			member1.setUsername("testB");
			member1.setAge(90);
			Member member2 = new Member();
			member2.setUsername("testBBBB");
			member2.setAge(80);

			Team team = new Team();
			team.setName("teamA");
			Team team1 = new Team();
			team1.setName("teamB");
			em.persist(team);
			em.persist(team1);

			member.setTeam(team);
			member1.setTeam(team1);
			member2.setTeam(team1);
			em.persist(member);
			em.persist(member1);
			em.persist(member2);

			em.flush();
			em.clear();

			// 내부 조인
			// Query query = em.createQuery("select m from Member m inner join m.team t");
			// List resultList = query.getResultList();
			// for (Object o : resultList) {
			// 	System.out.println("objcet class " + o.getClass());
			// }
			//
			// System.out.println("==================");

			// 잘못된 조인 사용 예 Order 엔티티는 Member 엔티티와 아무런 연관관계가 없다.
			// em.createQuery("select m from Member m inner join Order o").getResultList();

			// 외부 조인
			// em.createQuery("select m from Member m right outer join m.team t").getResultList();

			// 컬렉션 조인
			// List resultList = em.createQuery("select m from Team t left outer join t.members m").getResultList();
			// for (Object o : resultList) {
			// 	System.out.println("objcet class " + o.getClass());
			// }

			// 세타 조인 Order 엔티티는 Member 엔티티와 아무런 연관관계가 없다.
			// 내부 조인과 외부 조인 방식을 사용하면 오류가 나는데 세타 조인을 처리하면 연관 관계가 없어도 처리가 가능하다.
			// 단 세타 조인 내부 조인만 지원 된다.
			// em.createQuery("select m from Member m, Order o where m.username = o.address.city").getResultList();

			// join on 절
			// on 절 사용 시 연관관계 없는 필드 조인도 가능하게 해준다.
			// List resultList = em.createQuery("select m from Member m inner join Order o on o.address.city='teamB'")
			// 	.getResultList();
			// System.out.println("resultList = " + resultList.size());

			// 페치 조인
			// 앤티티 페치 조인
			// List resultList = em.createQuery("select m from Member m join fetch m.team").getResultList();
			// for (Object o : resultList) {
			// 	System.out.println("objcet class " + o.getClass());
			// 	Member findMember = (Member)o;
			// 	System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());
			// }

			// 서브 쿼리
			// List resultList = em.createQuery(
			// 	"select m from Member m where m.age > (select avg (m2.age) from Member m2)").getResultList();
			// System.out.println("resultList.size() = " + resultList.size());

			// 타입 표현 식
			// em.createQuery("select m.username, 'hello', true from Member m "
			// 	+ "where m.memberType = hellojpa.jpql.MemberType.ADMIN ").getResultList();
			// em.createQuery("select i from Item i where type(i) = Book ");

			// case 식
			// 일반 케이스
			// List<String> resultList = em.createQuery(
			// 		"select case when m.age <= 90 then '학생요금' "
			// 			+ "when m.age >= 60 then '경로요금' "
			// 			+ "else '일반요금' end "
			// 			+ "from Member m", String.class)
			// 	.getResultList();
			//
			// for (String s : resultList) {
			// 	System.out.println("s = " + s);
			// }
			// // COALESCE
			// em.createQuery("select coalesce(m.username, '이름 없는 회원') from Member m");
			//
			// // NULLIF
			// em.createQuery("select NULLIF(m.username, '관리자') from Member m");

			// JPQL 기본 함수
			// em.createQuery("select 'a' || 'b', concat(m.username, 'bbbb') from Member m");
			// em.createQuery("select substring(m.username, 2, 3), trim(m.username) from Member m");
			// em.createQuery("select locate('de','abcdefg') from Member m");
			// em.createQuery("select CURRENT_TIMESTAMP, year(CURRENT_TIMESTAMP), day(CURRENT_TIME) from Member m");
			//
			// // SIZE, INDEX
			// em.createQuery("select size(t.members) from Team t");
			// // @OrderColumn 와 전제조건
			// em.createQuery("select index(t.members) from Team t").getResultList();
			//
			// // 사용자 정의 함수
			// // dialect 를 직접 생성 해야한다. 함수를 만드는 방법은 각각 dialect 등을 확인해보자
			// // 이후 persistence.xml 에 해당 dialect 를 지정해주면 된다.
			// em.createQuery("select function('group_concat', m.username) from Member m");

			// 경로 표현식

			// 상태 필드 -> 경로 탐색의 끝
			// em.createQuery("select m.username from Member m");

			// 단일 값 연관 경로 -> 묵시적 내부 조인 발생 추가 탐색 가능
			// em.createQuery("select m.team from Member m");

			// 컬렉션 값 연관 경로 -> 묵시적 내부 조인 발생, 탐색 x
			// em.createQuery("select t.members from Team t");
			// em.createQuery("select m.username from Team t join t.members m").getResultList();

			// fetch 조인
			// 일반 조인 시 연관관계 SQL
			// List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
			// for (Member findMember : resultList) {
			// 	System.out.println("findMember.getUsername() = " + findMember.getUsername());
			// 	System.out.println("===================");
			// 	// 일반적으로 연관관계 조회 시 한번 더 SQL 이 날아간다.
			// 	// 즉시로딩은 로딩 시 조회 쿼리 한번더, 지연 로딩은 실제 연관관계 객체 사용 시 날아간다.
			// 	System.out.println("findMember.getUsername() = " + findMember.getTeam().getClass());
			// }

			// fetch 조인을 사용해서 조인 방식으로 한번의 SQL 로 조회
			// List<Member> resultList = em.createQuery(
			// 		"select m from Member m inner join fetch m.team", Member.class)
			// 	.getResultList();
			// for (Member findMember : resultList) {
			// 	System.out.println("findMember.getUsername() = " + findMember.getUsername());
			// 	System.out.println("===================");
			// 	// 페치 조인을 사용하게 되면 글로벌 페치 조인 전략은 반영되지 않고 JPQL 만을 해석해서 진행한다.
			// 	// 지연로딩을 섫정해두어도 fetch 조인을 사용하면 즉시로딩 되며, join 으로 하나의 SQL 로 날라 간다.
			// 	System.out.println("findMember.getUsername() = " + findMember.getTeam().getClass());
			// }

			// 컬렉션 페치 조인
			// List<Team> resultList = em.createQuery("select t from Team t join fetch t.members", Team.class)
			// 	.getResultList();
			// // 일대다 관계는 결과가 증가 될 수 있다.
			// for (Team findTeam : resultList) {
			// 	System.out.println("findTeam.getName() = " + findTeam.getName());
			// 	System.out.println("findTeam.getMembers().getClass() = " + findTeam.getMembers().getClass());
			// }

			// 컬렉션 페치 조인 distinct 사용 하면 결과 값이 DB 상에서 중복은 제거 해주는 기능도 있지만,
			// 애플리케이션 단에서 중복 되는 객체도 제거해준다.
			// List<Team> resultList = em.createQuery("select distinct t from Team t join fetch t.members", Team.class)
			// 	.getResultList();
			// for (Team findTeam : resultList) {
			// 	System.out.println("findTeam.getName() = " + findTeam.getName());
			// 	System.out.println("findTeam.getMembers().getClass() = " + findTeam.getMembers().getClass());
			// }

			// 컬렉션 래퍼 프록시 확인
			// List<Team> findTeam = em.createQuery("select t from Team t", Team.class).getResultList();
			// for (Team ooo : findTeam) {
			// 	System.out.println(
			// 		"ooo.getMembers().getClass() = " + ooo.getMembers().getClass());
			// }
			// List resultList = em.createQuery("select t.members from Team t").getResultList();
			// System.out.println("resultList.getClass() = " + resultList.getClass());
			// for (Object o : resultList) {
			// 	System.out.println("o.getClass() = " + o.getClass());
			// }

			// 페치 조인 시 페이징 처리 일대다
			// em.createQuery("select t from Team t join fetch t.members m")
			// 	.setFirstResult(0)
			// 	.setMaxResults(2)
			// 	.getResultList();

			// 일대다를 다대일로 페이징 처리
			// em.createQuery("select m from Member m join fetch m.team t")
			// 	.setFirstResult(0)
			// 	.setMaxResults(2)
			// 	.getResultList();

			// batch size
			// List<Team> resultList = em.createQuery("select t from Team t", Team.class)
			// 	.setFirstResult(0)
			// 	.setMaxResults(2)
			// 	.getResultList();
			//
			// for (Team team2 : resultList) {
			// 	System.out.println("team2.getMembers() = " + team2.getMembers());
			// }

			// JPQL 엔티티 직접 사용
			// 엔티티의 아이디 직접 사용
			// em.createQuery("select count(m.id) from Member m").getResultList();
			// // 엔티티를 직접 사용
			// em.createQuery("select count(m) from Member m").getResultList();
			//
			// // 결과는 동일한 SQL 을 사용하고 엔티티의 기본 키 값을 사용한다.
			//
			// // 파라미터 전달
			// em.createQuery("select m.username from Member m where m = :member")
			// 	.setParameter("member", member)
			// 	.getResultList();
			//
			// // 식별자 직접 전달
			// em.createQuery("select m.username from Member m where m.id = :memberId")
			// 	.setParameter("memberId", member.getId())
			// 	.getResultList();

			// 외래 키 값 직접 사용
			// Team findTeam = em.find(Team.class, team.getId());
			//
			// em.createQuery("select m.username from Member m where m.team = :team")
			// 	.setParameter("team", team)
			// 	.getResultList();

			// Named 쿼리
			// Member.findByUsername 생성
			em.createNamedQuery("Member.findByUsername", Member.class).setParameter("username", "test").getResultList();

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