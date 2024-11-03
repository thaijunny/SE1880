package entity;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Feature {
    private int id;
    private String name;
    private String url;
    private ArrayList<Role> roles = new ArrayList<>();

}
