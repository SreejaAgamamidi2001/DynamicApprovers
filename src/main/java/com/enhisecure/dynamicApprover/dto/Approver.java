package com.enhisecure.dynamicApprover.dto;

public class Approver
{
    private String id;
    private String name;
    private String type;

    public Approver(String id, String name,String type)
    {
        this.id=id;
        this.name=name;
        this.type=type;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return type;
    }

}