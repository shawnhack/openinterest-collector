package live.openinterest.collector.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "openinterest")
public class OpenInterest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "openinterest_id_seq")
	@SequenceGenerator(name = "openinterest_id_seq", sequenceName = "openinterest_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "amount")
	private float amount;

	@Column(name = "record_time")
	private LocalDateTime timestamp;

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
