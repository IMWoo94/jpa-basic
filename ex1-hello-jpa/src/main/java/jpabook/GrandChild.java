package jpabook;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
// @IdClass(GrandChildId.class)
public class GrandChild {

	// @Id
	// @ManyToOne
	// @JoinColumns({
	// 	@JoinColumn(name = "PARENT_ID"),
	// 	@JoinColumn(name = "CHILD_ID"),
	// })
	// private Child childId;
	//
	// @Id
	// @Column(name = "GRANDCHILD_ID")
	// private String id;

	@EmbeddedId
	private GrandChildId id;

	@MapsId("childId")
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "PARENT_ID"),
		@JoinColumn(name = "CHILD_ID"),
	})
	private Child child;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
