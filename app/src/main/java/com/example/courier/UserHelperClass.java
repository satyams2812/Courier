package com.example.courier;

public class UserHelperClass {
    String menu, pickname,pickphone,deliverusername,deliverphoneno,pickupaddress,deliveradddress,mapdeliveradddress,mappickupaddress,name,userphoneno,Date;

    public UserHelperClass() {
    }

    public UserHelperClass(String menu, String pickname, String pickphone, String deliverusername, String deliverphoneno, String pickupaddress, String deliveradddress, String mapdeliveradddress, String mappickupaddress, String name, String userphoneno, String date) {
        this.menu = menu;
        this.pickname = pickname;
        this.pickphone = pickphone;
        this.deliverusername = deliverusername;
        this.deliverphoneno = deliverphoneno;
        this.pickupaddress = pickupaddress;
        this.deliveradddress = deliveradddress;
        this.mapdeliveradddress = mapdeliveradddress;
        this.mappickupaddress = mappickupaddress;
        this.name = name;
        this.userphoneno = userphoneno;
        Date = date;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getPickname() {
        return pickname;
    }

    public void setPickname(String pickname) {
        this.pickname = pickname;
    }

    public String getPickphone() {
        return pickphone;
    }

    public void setPickphone(String pickphone) {
        this.pickphone = pickphone;
    }

    public String getDeliverusername() {
        return deliverusername;
    }

    public void setDeliverusername(String deliverusername) {
        this.deliverusername = deliverusername;
    }

    public String getDeliverphoneno() {
        return deliverphoneno;
    }

    public void setDeliverphoneno(String deliverphoneno) {
        this.deliverphoneno = deliverphoneno;
    }

    public String getPickupaddress() {
        return pickupaddress;
    }

    public void setPickupaddress(String pickupaddress) {
        this.pickupaddress = pickupaddress;
    }

    public String getDeliveradddress() {
        return deliveradddress;
    }

    public void setDeliveradddress(String deliveradddress) {
        this.deliveradddress = deliveradddress;
    }

    public String getMapdeliveradddress() {
        return mapdeliveradddress;
    }

    public void setMapdeliveradddress(String mapdeliveradddress) {
        this.mapdeliveradddress = mapdeliveradddress;
    }

    public String getMappickupaddress() {
        return mappickupaddress;
    }

    public void setMappickupaddress(String mappickupaddress) {
        this.mappickupaddress = mappickupaddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserphoneno() {
        return userphoneno;
    }

    public void setUserphoneno(String userphoneno) {
        this.userphoneno = userphoneno;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
