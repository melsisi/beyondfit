package beyondfit.bfpopulator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melsisi on 4/25/2016.
 */
public class Plate implements Serializable {

    private ArrayList<PlateItem> plateItems;

    public Plate() {
        plateItems = new ArrayList<>();
    }

    public ArrayList<PlateItem> getPlateItems() {
        return plateItems;
    }

    public void setPlateItems(ArrayList<PlateItem> plateItems) {
        this.plateItems = plateItems;
    }

}
