package com.enhisecure.dynamicApprover.dto;

public class RequestedItems {
    public String id;
    public String name;
    public String description;
    public String type;
    public String operation;
    public String comment;

    public void setId(String id)
    {
        this.id=id;
    }
    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public void setDescription(String description)
    {
        this.description=description;
    }
    public String getDescription()
    {
        return description;
    }
    public void setType(String type)
    {
        this.type=type;
    }
    public String getType()
    {
        return type;
    }
    public void setOperation(String operation)
    {
        this.operation=operation;
    }
    public String getOperation()
    {
        return operation;
    }
    public void setComment(String comment)
    {
        this.comment=comment;
    }
    public String getComment()
    {
        return comment;
    }
}
