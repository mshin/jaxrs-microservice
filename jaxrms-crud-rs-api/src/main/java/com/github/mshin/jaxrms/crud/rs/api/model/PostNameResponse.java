package com.github.mshin.jaxrms.crud.rs.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author MunChul Shin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class PostNameResponse {

    @JsonProperty("id")
    @JsonPropertyDescription("The created record's id.")
    @XmlElement
    @ApiModelProperty(value = "The created record's id.")
    private String id;
    
    @JsonProperty("responseCode")
    @JsonPropertyDescription("0 is success, otherwise failure.")
    @XmlElement
    @ApiModelProperty(value = "0 is success, otherwise failure.")
    private Integer responseCode;

    public PostNameResponse() {
    }

    public PostNameResponse(String id, Integer responseCode) {
        super();
        this.id = id;
        this.responseCode = responseCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((responseCode == null) ? 0 : responseCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PostNameResponse other = (PostNameResponse) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (responseCode == null) {
            if (other.responseCode != null)
                return false;
        } else if (!responseCode.equals(other.responseCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PostNameResponse [id=" + id + ", responseCode=" + responseCode + "]";
    }
    
    
}
