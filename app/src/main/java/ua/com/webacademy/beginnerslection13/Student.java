package ua.com.webacademy.beginnerslection13;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    public static final String TABLE_NAME = "Students";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "FirstName";
    public static final String COLUMN_LAST_NAME = "LastName";
    public static final String COLUMN_AGE = "Age";

    public long id;

    public String FirstName;
    public String LastName;
    public long Age;

    public Student() {
    }

    public Student(String firstName, String lastName, int age) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeLong(this.Age);
    }

    protected Student(Parcel in) {
        this.id = in.readLong();
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.Age = in.readLong();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return String.format("id:%s, %s %s, age:%s", id, FirstName, LastName, Age);
    }
}
