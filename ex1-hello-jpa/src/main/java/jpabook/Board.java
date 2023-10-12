package jpabook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Board {

	@Id
	@GeneratedValue
	@Column(name = "BOARD_ID")
	private Long id;

	private String title;

	@OneToOne(mappedBy = "board")
	private BoardDetail boardDetail;

}
