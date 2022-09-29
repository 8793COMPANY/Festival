package com.corporation8793.festival;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    //사용자 아이디
    @ColumnInfo(name = "userId")
    public String userId;
    //사용자 이름
    @ColumnInfo(name = "userName")
    public String userName;
    //사용자 비밀번호
    @ColumnInfo(name = "userPw")
    public String userPw;
    //사용자 비밀번호 확인 질문
    @ColumnInfo(name = "userPwQuestion")
    public String userPwQuestion;
    //사용자 비밀번호 확인 답
    @ColumnInfo(name = "userPwAnswer")
    public String userPwAnswer;
    //사용자 이메일
    @ColumnInfo(name = "userEmail")
    public String userEmail;
    //사용자 연락처
    @ColumnInfo(name = "userPhoneNumber")
    public String userPhoneNumber;
    //사용자 지역
    @ColumnInfo(name = "userArea")
    public String userArea;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserPwQuestion() {
        return userPwQuestion;
    }

    public void setUserPwQuestion(String userPwQuestion) {
        this.userPwQuestion = userPwQuestion;
    }

    public String getUserPwAnswer() {
        return userPwAnswer;
    }

    public void setUserPwAnswer(String userPwAnswer) {
        this.userPwAnswer = userPwAnswer;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }
}
