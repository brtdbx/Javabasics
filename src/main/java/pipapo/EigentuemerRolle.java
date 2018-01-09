package pipapo;

import java.util.List;

public class EigentuemerRolle {
    
    private List<Haus> haeuser;
    
    private String id;

    public List<Haus> getHaeuser() {
        return haeuser;
    }

    public void setHaeuser(List<Haus> haeuser) {
        this.haeuser = haeuser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EigentuemerRolle(String id) {
        this.id = id;
    }
    
    
    
    
}
