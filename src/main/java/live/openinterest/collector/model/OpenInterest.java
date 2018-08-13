package live.openinterest.collector.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OpenInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String amount;

    public String getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
