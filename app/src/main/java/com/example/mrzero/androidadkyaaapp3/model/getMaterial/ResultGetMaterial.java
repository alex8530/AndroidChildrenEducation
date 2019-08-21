package com.example.mrzero.androidadkyaaapp3.model.getMaterial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultGetMaterial {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
