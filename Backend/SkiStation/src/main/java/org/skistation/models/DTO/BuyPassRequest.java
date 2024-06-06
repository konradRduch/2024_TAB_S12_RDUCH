package org.skistation.models.DTO;

import org.skistation.models.Client;
import org.skistation.models.PriceList;

/**
 * Represents a request to buy a pass.
 * It contains the client, pass details, total price, number of normal and discount passes, and the price list.
 */
public class BuyPassRequest
{
    /**
     * The client who wants to buy the pass.
     */
    private Client client;

    /**
     * The details of the pass to buy.
     */
    private PassDTO passDTO;

    /**
     * The total price of the pass.
     */
    private Float total;

    /**
     * The number of normal passes to buy.
     */
    private Integer numberOfNormalPasses;

    /**
     * The number of discount passes to buy.
     */
    private Integer numberOfDiscountPasses;

    /**
     * The price list for the pass.
     */
    private PriceList priceList;

    /**
     * Constructs a new BuyPassRequest with no details.
     */
    public BuyPassRequest() {
    }

    /**
     * Constructs a new BuyPassRequest with the specified client, pass details, total price, number of normal and discount passes, and price list.
     *
     * @param client                 the client who wants to buy the pass
     * @param passDTO                the details of the pass to buy
     * @param total                  the total price of the pass
     * @param numberOfNormalPasses   the number of normal passes to buy
     * @param numberOfDiscountPasses the number of discount passes to buy
     * @param priceList              the price list for the pass
     */
    public BuyPassRequest(Client client, PassDTO passDTO, Float total, Integer numberOfNormalPasses, Integer numberOfDiscountPasses, PriceList priceList) {
        this.client = client;
        this.passDTO = passDTO;
        this.total = total;
        this.numberOfNormalPasses = numberOfNormalPasses;
        this.numberOfDiscountPasses = numberOfDiscountPasses;
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

    public Integer getNumberOfNormalPasses() {
        return numberOfNormalPasses;
    }

    public void setNumberOfNormalPasses(Integer numberOfNormalPasses) {
        this.numberOfNormalPasses = numberOfNormalPasses;
    }

    public Integer getNumberOfDiscountPasses() {
        return numberOfDiscountPasses;
    }

    public void setNumberOfDiscountPasses(Integer numberOfDiscountPasses) {
        this.numberOfDiscountPasses = numberOfDiscountPasses;
    }
}