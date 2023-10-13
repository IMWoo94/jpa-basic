package hellojpa;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {

	private String areaCode;
	private String localNumber;

	@ManyToOne
	@JoinColumn(name = "PHONESERVICDSD_ID")
	private PhoneServiceProvider provider;
}
