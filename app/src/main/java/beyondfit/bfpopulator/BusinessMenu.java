package beyondfit.bfpopulator;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by melsisi on 4/19/2016.
 */
public class BusinessMenu implements Serializable {
    private HashMap<String, Plate> plates;

    public HashMap<String, Plate> getPlates() {
        return plates;
    }

    public void setPlates(HashMap<String, Plate> plates) {
        this.plates = plates;
    }

    public BusinessMenu() {
        plates = new HashMap<>();
    }

}
