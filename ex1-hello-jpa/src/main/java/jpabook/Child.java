package jpabook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ChildId.class)
public class Child {

	// @Id
	// private String id;
	//
	// @ManyToOne
	// @JoinColumns({
	// 	@JoinColumn(name = "PARENT_ID1"),
	// 	@JoinColumn(name = "PARENT_ID2")
	// })
	// private Parent parent;

	@Id
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public Parent parent;

	@Id
	@Column(name = "CHILD_ID")
	private String childId;

	private String name;

}
