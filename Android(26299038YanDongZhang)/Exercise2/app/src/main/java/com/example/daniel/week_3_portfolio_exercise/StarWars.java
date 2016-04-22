package com.example.daniel.week_3_portfolio_exercise;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by daniel on 20/03/2016.
 */
public class StarWars implements Parcelable {
    //  Properties
    private String firstName;
    private String surname;
    private String motherMaidenName;
    private String birthplace;
    private String favouriteBrand;

    // Standard constructors
    public StarWars() {
        this.firstName = "mystery";
        this.surname = "mystery";
        this.motherMaidenName = "mystery";
        this.birthplace = "mystery";
        this.favouriteBrand = "mystery";
    }

    public StarWars(String firstName,String surname,String motherMaidenName,String birthplace,String favouriteBrand) {
        this.firstName = firstName;
        this.surname = surname;
        this.motherMaidenName = motherMaidenName;
        this.birthplace = birthplace;
        this.favouriteBrand = favouriteBrand;
    }



    // Accessors and mutators
    public String getFirstName() { return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getFavouriteBrand() {
        return favouriteBrand;
    }

    public void setFavouriteBrand(String favouriteBrand) {
        this.favouriteBrand = favouriteBrand;
    }

    // Constructor inits values based on implemented Parcelable interface
    public StarWars(Parcel in) {
        // Please be attentioned order
        firstName = in.readString();
        surname = in.readString();
        motherMaidenName = in.readString();
        birthplace = in.readString();
        favouriteBrand = in.readString();
    }

    // Generates a Parcelable instance of this class from a Parcel
    public static final Creator<StarWars> CREATOR = new Creator<StarWars>() {
        @Override
        public StarWars createFromParcel(Parcel in) {
            return new StarWars(in);
        }

        @Override
        public StarWars[] newArray(int size) {
            return new StarWars[size];
        }
    };


    // Used to describe special objects, not modified very often.
    @Override
    public int describeContents() {
        return 0;
    }

    // Outputs the format the parcel writes values
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(surname);
        dest.writeString(motherMaidenName);
        dest.writeString(birthplace);
        dest.writeString(favouriteBrand);
    }
}
