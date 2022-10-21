package com.data.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class UserData {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String name;    
    
    @NotNull
    @Email
    private String email;  
    
    @NotNull
    @Size(min=8, max=15)
    private String password;    
    
    @Positive
    @Pattern(regexp = "[0-9]{8}")
    private String cellphone;    
    
    @NotNull
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date birthday;
    
    @NotNull
    private String profession;
    
    @Min(value = 20000)
    @Max(value = 200000)
    private int income;
    


    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Date getBirthday() {
        return birthday;
    }
    
    public String getProfession() {
        return profession;
    }
    
    public int getIncome() {
        return income;
    }
    
    

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }    
    
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }    
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }   
    
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String toString()
    {
        return "User(Name: "+ this.name + this.email + this.password + this.cellphone + this.birthday + this.profession + this.income + ")";
    }
	
}
