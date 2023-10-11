package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Member")
// @SequenceGenerator(
// 	name = "member_seq_generator",
// 	sequenceName = "member_seq",
// 	initialValue = 1, allocationSize = 2
// )
// @TableGenerator(
// 	name = "MEMBER_SEQ_GENERATOR",
// 	table = "MY_SEQUENCES",
// 	pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

	@Id
	@GeneratedValue
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE
	// 	, generator = "member_seq_generator")
	// @GeneratedValue(strategy = GenerationType.TABLE,
	// 	generator = "MEMBER_SEQ_GENERATOR")
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "TEAM_ID")
	private Long teamId;

	//
	// private Integer age;
	// @Enumerated(EnumType.STRING)
	// private RoleType roleType;
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date createdDate;
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date lastModifiedDate;
	//
	// private LocalDate testLocalDate;
	// private LocalDateTime testLocalDateTime;
	//
	// @Lob
	// private String description;

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

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
