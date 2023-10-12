package jpabook;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class BoardDetail {

	@Id
	private Long boardId;

	@MapsId
	@OneToOne
	@JoinColumn(name = "BOARD_ID")
	private Board board;

	private String content;
}
