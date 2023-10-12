package jpabook;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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

	@EmbeddedId
	private ParentId id;

	private String name;

	public ParentId getId() {
		return id;
	}

	public void setId(ParentId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
