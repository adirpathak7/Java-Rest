package com.it.crudexam.bean;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
import com.it.crudexam.ejb.AbstractFacade;
import com.it.crudexam.ejb.Group1Facade;
import com.it.crudexam.entity.Group1;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Aditya Pathak R
 */
@Named(value = "crudBean")
@SessionScoped
public class CrudBean implements Serializable {

    @EJB
    private Group1Facade group1Facade;

    private Group1 group1;

    private int id;
    private String name;
    private String password;
    private String date;

    public CrudBean() {
        group1 = new Group1();
    }

    public String addData() {

//        if you are using EJB so un-comment bellow line and comment from line 50 -72
//        this.group1Facade.create(group1);
        System.out.println("    hello from addData");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/CRUDExam/app/rest/add");

        group1 = new Group1();
        group1.setName(name);
        group1.setDate(date);
        group1.setPassword(password);

        System.out.println("name is: " + name);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(group1, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 201) {
            System.out.println("Student inserted successfully!");
        } else {
            System.out.println("Error inserting student: " + response.getStatus());
        }

        response.close();
        client.close();

        this.name = "";
        this.date = "";
        this.password = "";
        return "index";
    }

    public String updateData() {
        this.group1Facade.edit(group1);
        return "index";
    }

    public AbstractFacade getAbstractFacade() {
        return group1Facade;
    }

    public void setAbstractFacade(Group1Facade group1Facade) {
        this.group1Facade = group1Facade;
    }

    public Group1 getGroup1() {
        return group1;
    }

    public void setGroup1(Group1 group1) {
        this.group1 = group1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
