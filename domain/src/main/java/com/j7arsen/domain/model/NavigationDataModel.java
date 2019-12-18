package com.j7arsen.domain.model;

import java.io.Serializable;
import java.util.List;

public class NavigationDataModel implements Serializable {

    private String name;
    private NavigationType type;
    private String content;
    private List<NavigationDataModel> navigationDataModelList;
    private NavigationImageType image;

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

    public List<NavigationDataModel> getNavigationDataModelList() {
        return navigationDataModelList;
    }

    public void setNavigationDataModelList(List<NavigationDataModel> navigationDataModelList) {
        this.navigationDataModelList = navigationDataModelList;
    }

    public NavigationImageType getImage() {
        return image;
    }

    public void setImage(NavigationImageType image) {
        this.image = image;
    }
}
