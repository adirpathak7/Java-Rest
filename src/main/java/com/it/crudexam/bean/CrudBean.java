package com.it.crudexam.bean;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
import com.it.crudexam.ejb.AbstractFacade;
import com.it.crudexam.ejb.Group1Facade;
import com.it.crudexam.entity.Group1;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
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
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/CRUDExam/app/rest/add");

        group1 = new Group1();
        group1.setName(name);
        group1.setDate(date);
        group1.setPassword(password);

//        System.out.println("name is: " + name);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(group1, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 201) {
            System.out.println("Data inserted successfully!");
        } else {
            System.out.println("Error inserting data: " + response.getStatus());
        }

        response.close();
        client.close();

        this.name = "";
        this.date = "";
        this.password = "";
        return "index";
    }

    public String updateData() {
//        this.group1Facade.edit(group1);
        System.out.println("in the bean ");
        System.out.println("the id in bean " + group1.getId());
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/CRUDExam/app/rest/updateData/" + group1.getId());

        System.out.println("in the bean " + group1.getName());

        Response response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(group1, MediaType.APPLICATION_JSON));
        System.out.println("in the bean " + group1.getName());
        if (response.getStatus() == 200) {
            System.out.println("Data updated successfully!");
        } else {
            System.out.println("Error updating Data: " + response.getStatus());
        }

        response.close();
        client.close();

        this.name = "";
        this.date = "";
        this.password = "";
        return "index";
    }

    public String deleteData(int id) {
        System.out.println("in the bean the id is: " + id);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/CRUDExam/app/rest/deleteData/" + id);

        Response response = target.request()
                .delete();

        if (response.getStatus() == 200) {
            System.out.println("Data deletde successfully!");
        } else {
            System.out.println("Error deleteing data: " + response.getStatus());
        }

        response.close();
        client.close();

        return "index";
    }

    public List<Group1> getData() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/CRUDExam/app/rest/getAllData");

        Response response = target.request().get();

        List<Group1> listData = null;
        if (response.getStatus() == 200) {
            listData = response.readEntity(new GenericType<List<Group1>>() {
            });
            System.out.println("Get the all data.");
        } else {
            System.out.println("Error geting all data " + response.getStatus());
        }

        return listData;
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
