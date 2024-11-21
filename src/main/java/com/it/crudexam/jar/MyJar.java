///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
// */
//package com.it.crudexam.jar;
//
//import javax.ws.rs.ClientErrorException;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Response;
//
///**
// * Jersey REST client generated for REST resource:JakartaEE8Resource [rest]<br>
// * USAGE:
// * <pre>
// *        MyJar client = new MyJar();
// *        Object response = client.XXX(...);
// *        // do whatever with response
// *        client.close();
// * </pre>
// *
// * @author Aditya Pathak R
// */
//public class MyJar {
//
//    private WebTarget webTarget;
//    private Client client;
//    private static final String BASE_URI = "http://localhost:8080/CRUDExam/app";
//
//    public MyJar() {
//        client = javax.ws.rs.client.ClientBuilder.newClient();
//        webTarget = client.target(BASE_URI).path("rest");
//    }
//
//    public Response deleteData(String id) throws ClientErrorException {
//        return webTarget.path(java.text.MessageFormat.format("deleteData/{0}", new Object[]{id})).request().delete(Response.class);
//    }
//
//    public Response updateData(String id) throws ClientErrorException {
//        return webTarget.path(java.text.MessageFormat.format("updateData/{0}", new Object[]{id})).request().put(null, Response.class);
//    }
//
//    public Response addData() throws ClientErrorException {
//        return webTarget.path("add").request().post(null, Response.class);
//    }
//
//    public String hello() throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path("hello");
//        return resource.get(String.class);
//    }
//
//    public <T> T getData(Class<T> responseType) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path("getAllData");
//        return resource.get(responseType);
//    }
//
//    public void close() {
//        client.close();
//    }
//    
//}
