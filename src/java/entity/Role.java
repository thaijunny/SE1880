package entity;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Role {
    private int id;
    private String name;
    private ArrayList<Feature> features = new ArrayList<>();

}
