package com.example.laksh_000.newlib.DataFiles;

import android.util.Log;

import java.util.Date;

public class StudentIDdata {
private String id;
private String name;
private String Classs;
private String name1;
private long Phone1;
private String name2;
private long Phone2;
private String age1;
private String gender1;
public StudentIDdata(){}
public StudentIDdata(String id,String name,String age,String gender,String Classs,String name1,long phone1,String name2,long phone2){
    this.id=id;
    this.name=name;
    this.age1=age;
    this.gender1=gender;
    this.Classs=Classs;
    this.name1=name1;
    this.Phone1=phone1;
    this.name2=name2;
    this.Phone2=phone2;
}
public String getId(){return  id;}
public void setId(String id){this.id=id;}
    public String getName(){return  name;}
    public void setName(String name){this.name=name;}
    public String getAge(){return age1;}
    public void setAge(String age){this.age1=age;}
    public String getGender(){return gender1;}
    public void setGender(String gender){this.gender1=gender;}
    public String getSClass(){
    return  Classs;}
    public void setSClass(String Class1){this.Classs=Class1;}
    public String getName1(){return  name1;}
    public void setPname(String pname){this.name1=pname;}
    public long getPhone1(){return  Phone1;}
    public void setPhone1(long Phone11){this.Phone1=Phone11;}
    public String getName2(){return  name2;}
    public void setName2(String name22){this.name2=name22;}
    public long getPhone2(){return  Phone2;}
    public void setPhone2(long Phone22){this.Phone2=Phone22;}
}
