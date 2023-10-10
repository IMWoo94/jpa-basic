package hellojpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Member")
@SequenceGenerator(
	name = "member_seq_generator",
	sequenceName = "member_seq",
	initialValue = 1, allocationSize = 2
)
// @TableGenerator(
// 	name = "MEMBER_SEQ_GENERATOR",
// 	table = "MY_SEQUENCES",
// 	pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE
		, generator = "member_seq_generator")
	// @GeneratedValue(strategy = GenerationType.TABLE,
	// 	generator = "MEMBER_SEQ_GENERATOR")
	private Long id;

	@Column(name = "name")
	private String username;

	private Integer age;
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	private LocalDate testLocalDate;
	private LocalDateTime testLocalDateTime;

	@Lob
	private String description;

	public Member(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public Member() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
