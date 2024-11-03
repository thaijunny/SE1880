package entity.assignment;

import entity.Department;
import java.sql.Date;
import java.util.ArrayList;
import lombok.Data;

@Data
public class Plan {
    private int id;
    private String name;
    private Date start;
    private Date end;
    private Department department;
    private ArrayList<PlanCampaign> campaigns = new ArrayList<>();

   
}
