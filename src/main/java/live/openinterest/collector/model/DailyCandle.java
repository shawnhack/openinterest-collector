package live.openinterest.collector.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dailycandle")
public class DailyCandle {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dailycandle_id_seq")
	@SequenceGenerator(name = "dailycandle_id_seq", sequenceName = "dailycandle_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	private float close;

	@Column(name = "candle_day")
	private LocalDate date;

	private float high;

	private float low;

	private float open;

	/**
	 * @return the close
	 */
	public float getClose() {
		return close;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the high
	 */
	public float getHigh() {
		return high;
	}

	/**
	 * @return the low
	 */
	public float getLow() {
		return low;
	}

	/**
	 * @return the open
	 */
	public float getOpen() {
		return open;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(float close) {
		this.close = close;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(float high) {
		this.high = high;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(float low) {
		this.low = low;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(float open) {
		this.open = open;
	}

	/**
	 * @param amount
	 */
	public void update(float amount) {
		if (open == 0) {
			open = amount;
		}
		if (low == 0) {
			low = amount;
		}
		if (amount > high) {
			high = amount;
		}
		if (amount < low) {
			low = amount;
		}
		close = amount;
	}

}
