package com.j7arsen.data.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j7arsen.domain.model.NavigationType;

import java.util.List;

public class NavigationDataEntity {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private NavigationType type;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("items")
    @Expose
    private List<NavigationDataEntity> navigationDataEntityList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NavigationType getType() {
        return type;
    }

    public void setType(NavigationType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<NavigationDataEntity> getNavigationDataEntityList() {
        return navigationDataEntityList;
    }

    public void setNavigationDataEntityList(List<NavigationDataEntity> navigationDataEntityList) {
        this.navigationDataEntityList = navigationDataEntityList;
    }
}
