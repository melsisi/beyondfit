package beyondfit.bfpopulator;

import java.util.ArrayList;

/**
 * Created by melsisi on 4/19/2016.
 */
public class Globals {
    private static Globals instance = new Globals();

    private BusinessMenu businessMenu;

    private String businessName;

    private String businessID;

    private ArrayList<Integer> dietaryRequirements;

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

    public String getBusinessName() { return businessName; }

    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessID() { return businessID; }

    public void setBusinessID(String businessID) { this.businessID = businessID; }

    public ArrayList<Integer> getDietaryRequirements() {
        return dietaryRequirements;
    }

    public void setDietaryRequirements(ArrayList<Integer> dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }
}
