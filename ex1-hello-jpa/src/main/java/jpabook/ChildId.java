package jpabook;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChildId implements Serializable {

	private String parent;
	@Column(name = "CHILD_ID")
	private String childId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ChildId childId1 = (ChildId)o;
		return Objects.equals(parent, childId1.parent) && Objects.equals(childId, childId1.childId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parent, childId);
	}
}
