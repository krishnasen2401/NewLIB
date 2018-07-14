package com.example.laksh_000.newlib.DataFiles;

public class BookHistory {
private String bookid;
private String studentid;
private String Sentdate;
private String recevieddate;
public BookHistory(){
}
    public BookHistory(String bookid, String studentid, String Sentdate,String recevieddate) {
        this.bookid = bookid;
        this.studentid = studentid;
        this.Sentdate = Sentdate;
        this.recevieddate = recevieddate;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }
    public String getBookid() {
        return bookid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getRecevieddate() {
        return recevieddate;
    }

    public String getSentdate() {
        return Sentdate;
    }

    public void setRecevieddate(String recevieddate) {
        this.recevieddate = recevieddate;
    }

    public void setSentdate(String sentdate) {
        Sentdate = sentdate;
    }
}
