package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Member")
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
	private Team team;

	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;

	// @ManyToMany
	// @JoinTable(name = "MEMBER_PRODUCT",
	// 	joinColumns = @JoinColumn(name = "MEMBER_ID"),
	// 	inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	// private List<Product> products = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<MemberProduct> memberProducts = new ArrayList<>();

	public Member(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public Member() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// public Team getTeam() {
	// 	return team;
	// }
	//
	// public void setTeam(Team team) {
	// 	this.team = team;
	// }
	//
	// public void changeTeam(Team team) {
	// 	this.team = team;
	// 	team.getMembers().add(this);
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Member{" +
			"id=" + id +
			", username='" + username + '\'' +
			// ", team=" + team +
			'}';
	}
}
