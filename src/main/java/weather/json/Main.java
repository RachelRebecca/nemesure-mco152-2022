package weather.json;

import com.google.gson.annotations.SerializedName;

public class Main
{
    double temp;
    double pressure;
    double humidity;
    @SerializedName("temp_min")
    double tempMin; // temp_min is able to be in Java naming convention because of SerializedName
    @SerializedName("temp_max")
    double tempMax;
}
