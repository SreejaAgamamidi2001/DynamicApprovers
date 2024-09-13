package com.enhisecure.dynamicApprover.dto;
import java.util.*;
public class Request {
    private String accessRequestId;
    private RequestedFor requestedFor;
    private List<RequestedItems> requestedItems;
    private RequestedBy requestedBy;
    public void setRequestedItems(List<RequestedItems> requestedItems)
    {
        this.requestedItems=requestedItems;
    }
    public List<RequestedItems> getRequestedItems()
    {
        return requestedItems;
    }
    public void setRequestedBy(RequestedBy requestedBy)
    {
        this.requestedBy=requestedBy;
    }
    public RequestedBy getRequestedBy()
    {
        return requestedBy;
    }
    public void setAccessRequestId(String accessRequestId)
    {
        this.accessRequestId=accessRequestId;
    }
    public void setRequestedFor(RequestedFor requestedFor)
    {
        this.requestedFor=requestedFor;
    }
    public String getAccessRequestId()
    {
        return accessRequestId;
    }
    public RequestedFor getRequestedFor()
    {
        return requestedFor;
    }

}
