package entity;

import entity.assignment.PlanCampaign;
import java.sql.Date;

public class ScheduleCampaign {
    private int id;
    private PlanCampaign planCampaign;
    private Date date;
    private int shift;
    private int quantity;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public PlanCampaign getPlanCampaign() {
        return planCampaign;
    }

    public void setPlanCampaign(PlanCampaign planCampaign) {
        this.planCampaign = planCampaign;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
