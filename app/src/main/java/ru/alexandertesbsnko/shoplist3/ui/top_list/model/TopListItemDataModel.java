package ru.alexandertesbsnko.shoplist3.ui.top_list.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс определяющий список, "поход в пятицу", "для нового года"
 * это набор продуктов и дату составления списка
 */
public class TopListItemDataModel implements Parcelable {

    private long id;
    private String name;
    private long dateMilis;
//    List<ProductInstance> prodList = new ArrayList<>();

    public TopListItemDataModel(long id, long date, String name) {
        this.id = id;
        this.dateMilis = date;
        this.name = name;
    }

//    public void addProductInstance(ProductInstance prodInstance) {
//        prodList.add(prodInstance);
//    }

    public String getName() {
        return name;
    }

    public long getDateMilis() {
        return dateMilis;
    }

    public void setDateMilis(long dateMilis) {
        this.dateMilis = dateMilis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setProdList(List<ProductInstance> prodList) {
//        this.prodList = prodList;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(dateMilis);
        parcel.writeString(name);
    }

    public static final Parcelable.Creator<TopListItemDataModel> CREATOR = new Parcelable.Creator<TopListItemDataModel>() {
        // распаковываем объект из Parcel
        public TopListItemDataModel createFromParcel(Parcel in) {
            return new TopListItemDataModel(in);
        }

        public TopListItemDataModel[] newArray(int size) {
            return new TopListItemDataModel[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private TopListItemDataModel(Parcel parcel) {
        id = parcel.readLong();
        dateMilis = parcel.readLong();
        name = parcel.readString();
    }
}
