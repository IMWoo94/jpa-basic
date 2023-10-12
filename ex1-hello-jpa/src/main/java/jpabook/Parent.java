package jpabook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// @IdClass(ParentId.class)
public class Parent {

	// @Id
	// @Column(name = "PARENT_ID1")
	// private String id1; // ParentId.id1 과 연결
	//
	// @Id
	// @Column(name = "PARENT_ID2")
	// private String id2; // ParentId.id2 과 연결

	// @EmbeddedId
	// private ParentId id;

	@Id
	@Column(name = "PARENT_ID")
	private String id; // 식별 관계

	private String name;

	// public ParentId getId() {
	// 	return id;
	// }
	//
	// public void setId(ParentId id) {
	// 	this.id = id;
	// }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
