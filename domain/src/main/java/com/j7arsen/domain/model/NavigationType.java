package com.j7arsen.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum NavigationType {

    @SerializedName("FOLDER")
    @Expose
    FOLDER,
    @SerializedName("FILE")
    @Expose
    FILE

}
