package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PhoneServiceProvider {

	@Id
	@GeneratedValue
	@Column(name = "PHONESERVICDSD_ID")
	private Long id;
}
