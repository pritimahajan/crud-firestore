package com.pritimahajan.crudfirestore.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class HistoryData
{

    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("url")
    private String url;

    @NotNull
    @JsonProperty("timestamp")
    private String timestamp;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }
}