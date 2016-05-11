package beyondfit.bfpopulator;

import android.view.Menu;

/**
 * Created by melsisi on 4/19/2016.
 */
public class Globals {
    private static Globals instance = new Globals();

    private BusinessMenu businessMenu;

    public static Globals getInstance() {
        if(instance==null)
            instance=new Globals();
        return instance;
    }

    public BusinessMenu getBusinessMenu() {
        return businessMenu;
    }

    public void setBusinessMenu(BusinessMenu businessMenu) {
        this.businessMenu = businessMenu;
    }

    private Globals() {
        businessMenu = new BusinessMenu();

    }
}
