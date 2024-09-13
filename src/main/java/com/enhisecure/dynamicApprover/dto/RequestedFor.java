package com.enhisecure.dynamicApprover.dto;

public class RequestedFor {
    public String id;
    public String name;
    public String type;
    public void setId(String id)
    {
        this.id=id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void  setType(String type)
    {
        this.type=type;
    }
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getType()
    {
        return type;
    }
}
