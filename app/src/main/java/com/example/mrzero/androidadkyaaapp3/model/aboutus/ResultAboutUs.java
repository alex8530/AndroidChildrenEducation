package com.example.mrzero.androidadkyaaapp3.model.aboutus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultAboutUs {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
