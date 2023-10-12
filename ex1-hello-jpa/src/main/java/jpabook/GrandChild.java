package jpabook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@IdClass(GrandChildId.class)
public class GrandChild {

	@Id
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "PARENT_ID"),
		@JoinColumn(name = "CHILD_ID"),
	})
	private Child childId;

	@Id
	@Column(name = "GRANDCHILD_ID")
	private String id;

	private String name;

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
