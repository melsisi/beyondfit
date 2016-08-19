package beyondfit.bfpopulator;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.nio.ByteBuffer;

/**
 * Created by melsisi on 4/26/2016.
 */
@DynamoDBTable(tableName = "RawMenuItems")
public class RawMenuItem {
    private String name;
    private ByteBuffer menuItems;

    @DynamoDBHashKey(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "menuitems")
    public ByteBuffer getMenu() {
        return menuItems;
    }

    public void setMenu(ByteBuffer menuItems) {
        this.menuItems = menuItems;
    }
}
