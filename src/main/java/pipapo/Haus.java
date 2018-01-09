package pipapo;

import java.util.List;

public class Haus {
    
    private List<EigentuemerRolle> eigentuemerRolle;
    
    private String id;

    public List<EigentuemerRolle> getEigentuemerRolle() {
        return eigentuemerRolle;
    }

    public void setEigentuemerRolle(List<EigentuemerRolle> eigentuemerRolle) {
        this.eigentuemerRolle = eigentuemerRolle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Haus(String id) {
        this.id = id;
    }
    
    
    
}
