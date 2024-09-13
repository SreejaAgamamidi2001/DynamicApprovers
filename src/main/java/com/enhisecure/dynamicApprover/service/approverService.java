package com.enhisecure.dynamicApprover.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enhisecure.dynamicApprover.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.stereotype.Service;
import com.enhisecure.dynamicApprover.dto.Approver;
import org.springframework.web.client.RestTemplate;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

@Service
public class approverService
{
    public Approver getApprover(com.enhisecure.dynamicApprover.dto.Request request) throws IOException
    {
        String userId=request.getRequestedFor().getId();
        String managerName = getManagerName(userId);
		if(managerName==null)
		{
			return new Approver("","","");
		}
        String managerId=getManagerId(managerName);
        System.out.println(managerName);
        if(managerId==null || managerId.isBlank())
        {
            return new Approver("","","");
        }
        return new Approver(managerId,managerName,"IDENTITY");
    }
    public String getManagerId(String name) throws IOException {
        String access_token=getAccessToken();
        final OkHttpClient client = new OkHttpClient();
        String apiUrl = "https://partner9058.api.identitynow-demo.com/beta/identities";
        Request request = new Request.Builder().url(apiUrl).addHeader("Authorization", access_token)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        TypeReference<List<Manager>> mapType = new TypeReference<List<Manager>>() {};

        String managerId="";
        List<Manager> accounts = mapper.readValue(response.string(), mapType);
        for(int i=0;i<accounts.size();i++)
        {
            Manager manager = accounts.get(i);
            if(name.equals(manager.getName()))
            {
                managerId=manager.getId();
                System.out.println(managerId);
            }
        }
        return managerId;

    }
    public String getManagerName(String id) throws IOException {
        String URI = "https://partner9058.api.identitynow-demo.com/beta/identities/"+id;
        String access_token=getAccessToken();
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URI).addHeader("Authorization", access_token)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode jsonNode = mapper.readTree(response.string());
        System.out.println(jsonNode);
        String managerName = "";
        if(jsonNode.get("attributes")==null)
        {
            return null;
        }
        else {
            JsonNode managerNode = mapper.readTree(jsonNode.get("attributes").toString());

            if (managerNode.get("mgr") == null)
                return null;
            else
                managerName = managerNode.get("mgr").asText();
        }
        return managerName;

    }
   /* public String getManagerId(String id) throws IOException {
        String URI = "https://partner9058.api.identitynow-demo.com/beta/identities/"+id;
        String access_token=getAccessToken();
        final OkHttpClient client = new OkHttpClient();
       Request request = new Request.Builder().url(URI).addHeader("Authorization", access_token)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode jsonNode = mapper.readTree(response.string());
        System.out.println(jsonNode);
        String managerId = "";
        if(jsonNode.get("attributes")==null)
        {
            return null;
        }
        else {
            JsonNode managerNode = mapper.readTree(jsonNode.get("attributes").toString());

            if (managerNode.get("mgr").asText() == null)
                return null;
            else
                managerId = managerNode.get("mgr").asText();
        }
        return managerId;

    }
    public String getManagerName(String id) throws IOException {
        String URI = "https://partner9058.api.identitynow-demo.com/beta/identities/"+id;
        String access_token=getAccessToken();
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URI).addHeader("Authorization", access_token)
                .build();
        ResponseBody response = client.newCall(request).execute().body();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode jsonNode = mapper.readTree(response.string());
        String name="";
        if(jsonNode.get("name")==null)
        {
            return null;
        }
        else {
            name = jsonNode.get("name").asText();
        }
        return name;

    }*/

    public String getAccessToken() throws IOException {
        String URI = "https://partner9058.api.identitynow-demo.com/oauth/token";
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .add("client_id", "277ac88be6b44baa86143c4646bb41fc")
                .add("client_secret","89093af3fdbc69cd011ad11f76390cc298547f5dd1cea9f7bea0767fb1b03452")
                .build();

        Request request = new Request.Builder()
                .url(URI)
                .post(formBody)
                .build();

        ResponseBody response = client.newCall(request).execute().body();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode jsonNode = mapper.readTree(response.string());
        String access_token = jsonNode.get("access_token").asText();
        if(access_token!=null)
            return "Bearer "+access_token;
        else
            return "Cannot retrieve Access Token";    }
}
