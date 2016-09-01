package com.goodthinking.younglod.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by elit on 17/07/16.
 */
@IgnoreExtraProperties
public class Course implements Parcelable {

    private String CourseName;
    private String CourseStartdate;
    private String Coursetime;
    private String CourseGide;
    private String CourseSynopsys;
    private String CourseInfo;
    private String CourseEndDate;
    private String MaxNoOfParticipetors;
    private int numberPrticipate;
    private String CourseCost;
    private String CourseAduience;
    private String CourseLang;
    private String StatusIsValidDate;
    @Exclude
    private String key;

    public Course() {
    }

    public Course(String courseAduience, String courseCost, String courseEndDate, String courseGide,
                  String CourseName, String courseInfo, String courseLang, String courseStartdate,
                  String courseSynopsys, String coursetime, String maxNoOfParticipetors,
                  int numberPrticipate, String statusIsValidDate) {
        this.CourseAduience = courseAduience;
        this.CourseCost = courseCost;
        this.CourseEndDate = courseEndDate;
        this.CourseGide = courseGide;
        this.CourseName = CourseName;
        this.CourseInfo = courseInfo;
        this.CourseLang = courseLang;
        this.CourseStartdate = courseStartdate;
        this.CourseSynopsys = courseSynopsys;
        this.Coursetime = coursetime;
        this.MaxNoOfParticipetors = maxNoOfParticipetors;
        this.numberPrticipate = numberPrticipate;
        this.StatusIsValidDate = statusIsValidDate;
    }


    public String getCourseAduience() {
        return CourseAduience;
    }

    public void setCourseAduience(String courseAduience) {
        CourseAduience = courseAduience;
    }

    public String getCourseCost() {
        return CourseCost;
    }

    public void setCourseCost(String courseCost) {
        CourseCost = courseCost;
    }

    public String getCourseEndDate() {
        return CourseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        CourseEndDate = courseEndDate;
    }

    public String getCourseGide() {
        return CourseGide;
    }

    public void setCourseGide(String courseGide) {
        CourseGide = courseGide;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getCourseInfo() {
        return CourseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        CourseInfo = courseInfo;
    }

    public String getCourseLang() {
        return CourseLang;
    }

    public void setCourseLang(String courseLang) {
        CourseLang = courseLang;
    }

    public String getCourseStartdate() {
        return CourseStartdate;
    }

    public void setCourseStartdate(String courseStartdate) {
        CourseStartdate = courseStartdate;
    }

    public String getCourseSynopsys() {
        return CourseSynopsys;
    }

    public void setCourseSynopsys(String courseSynopsys) {
        CourseSynopsys = courseSynopsys;
    }

    public String getCoursetime() {
        return Coursetime;
    }

    public void setCoursetime(String coursetime) {
        Coursetime = coursetime;
    }

    public String getMaxNoOfParticipetors() {
        return MaxNoOfParticipetors;
    }

    public void setMaxNoOfParticipetors(String maxNoOfParticipetors) {
        MaxNoOfParticipetors = maxNoOfParticipetors;
    }

    public int getNumberPrticipate() {
        return numberPrticipate;
    }

    public void setNumberPrticipate(int numberPrticipate) {
        this.numberPrticipate = numberPrticipate;
    }

    public String getStatusIsValidDate() {
        return StatusIsValidDate;
    }

    public void setStatusIsValidDate(String statusIsValidDate) {
        StatusIsValidDate = statusIsValidDate;
    }

    @Exclude
    String Key;

    @Exclude
    public String getKey() {
        return Key;
    }

    @Exclude
    public void setKey(String key) {
        Key = key;
    }




    @Exclude
    public HashMap<String,Object> Objecttofirebase(){
        HashMap<String,Object> course = new HashMap<>();

        course.put("CourseName", CourseName);
        course.put("CourseStartdate",CourseStartdate);
        course.put("Coursetime",Coursetime);
        course.put("CourseSynopsys",CourseSynopsys);
        course.put("CourseInfod",CourseInfo);
        course.put("CourseEndDate",CourseEndDate);
        course.put("MaxNoOfParticipetors",MaxNoOfParticipetors);
        course.put("numberPrticipate",numberPrticipate);
        course.put("CourseCost",CourseCost);
        course.put("CourseAduience",CourseAduience);
        course.put("CourseLang",CourseLang);
        course.put("StatusIsValidDate", StatusIsValidDate);

        return course;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CourseName);
        dest.writeString(this.CourseStartdate);
        dest.writeString(this.Coursetime);
        dest.writeString(this.CourseGide);
        dest.writeString(this.CourseSynopsys);
        dest.writeString(this.CourseInfo);
        dest.writeString(this.CourseEndDate);
        dest.writeString(this.MaxNoOfParticipetors);
        dest.writeInt(this.numberPrticipate);
        dest.writeString(this.CourseCost);
        dest.writeString(this.CourseAduience);
        dest.writeString(this.CourseLang);
        dest.writeString(this.StatusIsValidDate);
        dest.writeString(this.key);
        dest.writeString(this.Key);
    }

    protected Course(Parcel in) {
        this.CourseName = in.readString();
        this.CourseStartdate = in.readString();
        this.Coursetime = in.readString();
        this.CourseGide = in.readString();
        this.CourseSynopsys = in.readString();
        this.CourseInfo = in.readString();
        this.CourseEndDate = in.readString();
        this.MaxNoOfParticipetors = in.readString();
        this.numberPrticipate = in.readInt();
        this.CourseCost = in.readString();
        this.CourseAduience = in.readString();
        this.CourseLang = in.readString();
        this.StatusIsValidDate = in.readString();
        this.key = in.readString();
        this.Key = in.readString();
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
}
