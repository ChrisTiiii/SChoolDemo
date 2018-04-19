package com.example.juicekaaa.schooldemo;

/**
 * Created by Juicekaaa on 17/3/21.
 */

public class ResourceModel {

    private int resourcetype;
    private String resource;

    public ResourceModel() {

    }

    public ResourceModel(int resourcetype, String resource) {


        this.resourcetype = resourcetype;
        this.resource = resource;
    }

    public int getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(int resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


}
