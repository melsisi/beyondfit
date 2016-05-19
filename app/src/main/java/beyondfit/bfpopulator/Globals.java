package beyondfit.bfpopulator;

import android.view.Menu;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapperConfig;

/**
 * Created by melsisi on 4/19/2016.
 */
public class Globals {
    private static Globals instance = new Globals();

    private BusinessMenu businessMenu;

    private String businessName;

    private String businessID;

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
}
