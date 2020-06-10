package com.example.waqas.dashboardapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String mainCategory;
    private String logoImage;
    private String subCategory;
    private String name;
    private String description;
    private String image;
    private String graphDescription;
    private String factor1Description;
    private String factor1Value;
    private String factor2Description;
    private String factor2Value;
    private String factor3Description;
    private String factor3Value;
    private String factor4Description;
    private String factor4Value;



    public Item(){

    }

    public Item(Parcel in) {
        mainCategory = in.readString();
        logoImage = in.readString();
        subCategory = in.readString();
        name = in.readString();
        description = in.readString();
        image = in.readString();
        graphDescription = in.readString();
        factor1Description = in.readString();
        factor1Value = in.readString();
        factor2Description = in.readString();
        factor2Value = in.readString();
        factor3Description = in.readString();
        factor3Value = in.readString();
        factor4Description = in.readString();
        factor4Value = in.readString();
    }

    public Item(String mainCategory, String logoImage, String subCategory, String name, String description, String image, String graphDescription, String factor1Description, String factor1Value, String factor2Description, String factor2Value, String factor3Description, String factor3Value, String factor4Description, String factor4Value) {
        this.mainCategory = mainCategory;
        this.logoImage = logoImage;
        this.subCategory = subCategory;
        this.name = name;
        this.description = description;
        this.image = image;
        this.graphDescription = graphDescription;
        this.factor1Description = factor1Description;
        this.factor1Value = factor1Value;
        this.factor2Description = factor2Description;
        this.factor2Value = factor2Value;
        this.factor3Description = factor3Description;
        this.factor3Value = factor3Value;
        this.factor4Description = factor4Description;
        this.factor4Value = factor4Value;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGraphDescription() {
        return graphDescription;
    }

    public void setGraphDescription(String graphDescription) {
        this.graphDescription = graphDescription;
    }

    public String getFactor1Description() {
        return factor1Description;
    }

    public void setFactor1Description(String factor1Description) {
        this.factor1Description = factor1Description;
    }

    public String getFactor1Value() {
        return factor1Value;
    }

    public void setFactor1Value(String factor1Value) {
        this.factor1Value = factor1Value;
    }

    public String getFactor2Description() {
        return factor2Description;
    }

    public void setFactor2Description(String factor2Description) {
        this.factor2Description = factor2Description;
    }

    public String getFactor2Value() {
        return factor2Value;
    }

    public void setFactor2Value(String factor2Value) {
        this.factor2Value = factor2Value;
    }

    public String getFactor3Description() {
        return factor3Description;
    }

    public void setFactor3Description(String factor3Description) {
        this.factor3Description = factor3Description;
    }

    public String getFactor3Value() {
        return factor3Value;
    }

    public void setFactor3Value(String factor3Value) {
        this.factor3Value = factor3Value;
    }

    public String getFactor4Description() {
        return factor4Description;
    }

    public void setFactor4Description(String factor4Description) {
        this.factor4Description = factor4Description;
    }

    public String getFactor4Value() {
        return factor4Value;
    }

    public void setFactor4Value(String factor4Value) {
        this.factor4Value = factor4Value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mainCategory);
        dest.writeString(logoImage);
        dest.writeString(subCategory);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(graphDescription);
        dest.writeString(factor1Description);
        dest.writeString(factor1Value);
        dest.writeString(factor2Description);
        dest.writeString(factor2Value);
        dest.writeString(factor3Description);
        dest.writeString(factor3Value);
        dest.writeString(factor4Description);
        dest.writeString(factor4Value);
    }

    @Override
    public String toString() {
        return "Item{" +
                "mainCategory='" + mainCategory + '\'' +
                ", logoImage='" + logoImage + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", graphDescription='" + graphDescription + '\'' +
                ", factor1Description='" + factor1Description + '\'' +
                ", factor1Value='" + factor1Value + '\'' +
                ", factor2Description='" + factor2Description + '\'' +
                ", factor2Value='" + factor2Value + '\'' +
                ", factor3Description='" + factor3Description + '\'' +
                ", factor3Value='" + factor3Value + '\'' +
                ", factor4Description='" + factor4Description + '\'' +
                ", factor4Value='" + factor4Value + '\'' +
                '}';
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>()
    {
        public Item createFromParcel(Parcel in)
        {
            return new Item(in);
        }
        public Item[] newArray(int size)
        {
            return new Item[size];
        }
    };
}
