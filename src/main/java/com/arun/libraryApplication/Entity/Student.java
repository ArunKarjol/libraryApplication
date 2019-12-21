package com.arun.libraryApplication.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    public String studRollNo;
    public String fname;
    public String lname;
    public String email;
    public Long phoneNo;

    public String getStudRollNo() {
        return studRollNo;
    }

    public void setStudRollNo(String studRollNo) {
        this.studRollNo = studRollNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
}
