package agorbahn.android_project.services;

import android.telephony.PhoneNumberUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import agorbahn.android_project.Constants;
import agorbahn.android_project.models.Doctor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Adam on 12/2/2016.
 */

public class DoctorServices {
    public static void findDoctors(String Loction, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.DOCTOR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.LOCATION_QUERY_PARAMETER, Loction);
        urlBuilder.addQueryParameter(Constants.APIKEY_QUERY_PARAMETER, Constants.DOCTOR_APIKEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.d("URL", "JSON:        " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject DoctorJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = DoctorJSON.getJSONArray("data");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject doctorJSON = resultsJSON.getJSONObject(i);
                    String name = doctorJSON.getJSONArray("practices").getJSONObject(0).getString("name");
                    String specialty = doctorJSON.getJSONArray("specialties").getJSONObject(0).getString("actor");

                    ArrayList<String> phone = new ArrayList<>();
                    JSONArray phoneJSON = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONArray("phones");
                    for (int j = 0; j < phoneJSON.length(); j++) {
                        String number = phoneJSON.getJSONObject(j).getString("number");
                        phone.add(number);
                    }

                    double latitude = doctorJSON.getJSONArray("practices").getJSONObject(0).getDouble("lat");
                    double longitude = doctorJSON.getJSONArray("practices").getJSONObject(0).getDouble("lon");
                    String street = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street");

                    String street2;
                    try {
                        street2 = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street2");
                    } catch (JSONException e) {
                        street2 = "";
                    }

                    String city = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("city");
                    String state = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("state");
                    String zip = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("zip");
                    String bio = doctorJSON.getJSONObject("profile").getString("bio");

                    Doctor doctor = new Doctor(name, specialty, phone, latitude, longitude, street, street2, city, state, zip, bio);
                    doctors.add(doctor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
