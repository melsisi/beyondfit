package beyondfit.bfpopulator;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by melsisi on 4/26/2016.
 */
@DynamoDBTable(tableName = "RestaurantsID2")
public class RestaurantsIDs {
    private String id;
    private String name;

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    @DynamoDBHashKey(attributeName = "ID")
    public String getID() {
        return id;
    }

    public void setID(String id) { this.id = id; }
}
