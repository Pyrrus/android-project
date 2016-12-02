package agorbahn.android_project.services;

import android.util.Log;

import agorbahn.android_project.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


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
}
