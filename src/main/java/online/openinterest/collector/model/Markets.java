package online.openinterest.collector.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Markets {

    @Id
    private String marketId;

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

}
