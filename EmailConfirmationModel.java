package com.example.worldskills;

import com.google.gson.annotations.SerializedName;

public class EmailConfirmationModel {
    @SerializedName("message")
    public String message;

    @SerializedName("errors")
    public String errors;
}
