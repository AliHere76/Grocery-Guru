/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

/**
 *
 * @author ASUS TUFF
 */

import java.io.Serializable;
public class DateOfBirth implements Serializable{
    private String date;
    private String month;
    private String year;

    public DateOfBirth(){}
    
    public DateOfBirth(String date, String month, String year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getMonth() {return month;}
    public void setMonth(String month) {this.month = month;}

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    @Override
    public String toString() {
        return "{" +
                "date='" + date + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +'}';
    }
}
