package jpabook.jpashop.domain;

import static javax.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Long id;

	@ManyToMany
	@JoinTable(name = "CATEGORY_ITEM",
		joinColumns = @JoinColumn(name = "CATEGORY_ID"),
		inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
	private List<Item> items = new ArrayList<>();

	// @OneToMany(mappedBy = "category")
	// private List<CategoryItem> categoryItems = new ArrayList<>();

	private String name;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
