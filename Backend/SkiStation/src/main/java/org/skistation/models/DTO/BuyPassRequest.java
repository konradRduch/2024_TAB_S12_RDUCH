package org.skistation.models.DTO;

import org.skistation.models.Client;
import org.skistation.models.PriceList;

public class BuyPassRequest {
    private Client client;
    private PassDTO passDTO;
    private Float total;
    private PriceList priceList;

    public BuyPassRequest() {
    }

    public BuyPassRequest(Client client, PassDTO passDTO, Float total, PriceList priceList) {
        this.client = client;
        this.passDTO = passDTO;
        this.total = total;
        this.priceList = priceList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PassDTO getPassDTO() {
        return passDTO;
    }

    public void setPassDTO(PassDTO passDTO) {
        this.passDTO = passDTO;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }
}
