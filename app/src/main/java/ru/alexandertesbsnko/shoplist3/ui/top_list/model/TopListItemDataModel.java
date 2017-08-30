package ru.alexandertesbsnko.shoplist3.ui.top_list.model;

/**
 * Класс определяющий список, "поход в пятицу", "для нового года"
 * это набор продуктов и дату составления списка
 */
public class TopListItemDataModel {
//    public class TopListItemDataModel implements Parcelable {

    private long id;
    private String name;
    private String dateTitle;
    private int imageId;

    public TopListItemDataModel(long id, String dateTitle, String name, int imageId) {
        this.id = id;
        this.dateTitle = dateTitle;
        this.name = name;
        this.imageId = imageId;
    }


    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDateTitle() {
        return dateTitle;
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

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeLong(id);
//        parcel.writeString(dateTitle);
//        parcel.writeString(name);
//    }
//
//    public static final Parcelable.Creator<TopListItemDataModel> CREATOR = new Parcelable.Creator<TopListItemDataModel>() {
//        // распаковываем объект из Parcel
//        public TopListItemDataModel createFromParcel(Parcel in) {
//            return new TopListItemDataModel(in);
//        }
//
//        public TopListItemDataModel[] newArray(int size) {
//            return new TopListItemDataModel[size];
//        }
//    };
//
//    // конструктор, считывающий данные из Parcel
//    private TopListItemDataModel(Parcel parcel) {
//        id = parcel.readLong();
//        dateTitle = parcel.readLong();
//        name = parcel.readString();
//    }
}
