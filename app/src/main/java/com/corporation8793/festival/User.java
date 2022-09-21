package com.corporation8793.festival;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "userId")
    public String userId;

    @ColumnInfo(name = "userName")
    public String userName;

    @ColumnInfo(name = "userPw")
    public String userPw;

    @ColumnInfo(name = "userPwQuestion")
    public String userPwQuestion;

    @ColumnInfo(name = "userPwAnswer")
    public String userPwAnswer;

    @ColumnInfo(name = "userEmail")
    public String userEmail;

    @ColumnInfo(name = "userPhoneNumber")
    public String userPhoneNumber;

    @ColumnInfo(name = "userArea")
    public String userArea;

}
