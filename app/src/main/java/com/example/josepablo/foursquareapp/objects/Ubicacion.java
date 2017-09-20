package com.example.josepablo.foursquareapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jose Pablo on 9/18/2017.
 */

public class Ubicacion implements Parcelable {

    private String name;
    private String description;
    private String url;
    private String imgUrl;

    // Remove after FourSquare Implementation
    private int img;

    public Ubicacion(String n, String d, String u, String i){
        this.name = n;
        this.description = d;
        this.url = u;
        this.imgUrl = i;
        this.img = 0;
    }

    //Remove after FourSquare Implementation
    public Ubicacion(String n, String d, int i){
        this.name = n;
        this.description = d;
        this.url = "";
        this.imgUrl = "";
        this.img = i;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getImg(){
        return img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return hashCode();
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(description);
        out.writeString(url);
        out.writeString(imgUrl);
        out.writeInt(img);
    }

    // example constructor that takes a Parcel and gives you an object populated with it's values
    public Ubicacion(Parcel in) {
        name = in.readString();
        description = in.readString();
        url = in.readString();
        imgUrl = in.readString();
        img = in.readInt();
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Ubicacion> CREATOR = new Parcelable.Creator<Ubicacion>() {
        @Override
        public Ubicacion createFromParcel(Parcel in) {
            return new Ubicacion(in);
        }

        public Ubicacion[] newArray(int size) {
            return new Ubicacion[0];
        }
    };
}
