package beyondfit.bfpopulator;

import java.io.Serializable;

/**
 * Created by melsisi on 4/19/2016.
 */
public class PlateItem implements Serializable{
    private String mainCategory;
    private String subCategory;
    private String shape;
    private String kind;
    private int meatPercent;
    private String part;
    private int quantity;
    private double weight;
    private String cookingType;
    private String Comments;
    private double protein;
    private double carbs;
    private double carbsStarch;
    private double carbsFiber;
    private double carbsSugar;
    private double fat;
    private double fatUnsat;
    private double fatSat;
    private double fatTrans;
    private int Calories;
    private int GL;
    private double salt;

    public PlateItem() {
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getMeatPercent() {
        return meatPercent;
    }

    public void setMeatPercent(int meatPercent) {
        this.meatPercent = meatPercent;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCookingType() {
        return cookingType;
    }

    public void setCookingType(String cookingType) {
        this.cookingType = cookingType;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getCarbsStarch() {
        return carbsStarch;
    }

    public void setCarbsStarch(double carbsStarch) {
        this.carbsStarch = carbsStarch;
    }

    public double getCarbsFiber() {
        return carbsFiber;
    }

    public void setCarbsFiber(double carbsFiber) {
        this.carbsFiber = carbsFiber;
    }

    public double getCarbsSugar() {
        return carbsSugar;
    }

    public void setCarbsSugar(double carbsSugar) {
        this.carbsSugar = carbsSugar;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFatUnsat() {
        return fatUnsat;
    }

    public void setFatUnsat(double fatUnsat) {
        this.fatUnsat = fatUnsat;
    }

    public double getFatSat() {
        return fatSat;
    }

    public void setFatSat(double fatSat) {
        this.fatSat = fatSat;
    }

    public double getFatTrans() {
        return fatTrans;
    }

    public void setFatTrans(double fatTrans) {
        this.fatTrans = fatTrans;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public int getGL() {
        return GL;
    }

    public void setGL(int GL) {
        this.GL = GL;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }
}
