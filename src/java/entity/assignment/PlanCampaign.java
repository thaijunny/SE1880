package entity.assignment;

import lombok.Data;

@Data
public class PlanCampaign {
    private int id;
    private Plan plan;
    private Product product;
    private int quantity;
    private float cost;

  
}
