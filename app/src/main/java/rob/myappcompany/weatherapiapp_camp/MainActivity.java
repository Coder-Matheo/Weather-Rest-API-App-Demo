package rob.myappcompany.weatherapiapp_camp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btn_getweatherByID, btn_getWeatherByName;
    EditText ed_dataInput;
    ListView lv_weatherReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityID = findViewById(R.id.btn_getCityId);
        btn_getweatherByID = findViewById(R.id.btn_getWeatherByCityId);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCiutyName);

        ed_dataInput = findViewById(R.id.ed_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReporsts);

        final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        btn_getweatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForcastById(ed_dataInput.getText().toString(), new WeatherDataService.ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                       //put the entrie list into the listView control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });


            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForcastByName(ed_dataInput.getText().toString(), new WeatherDataService.GetCityForeCaseByNameCallback() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                        //put the entrie list into the listView control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });
            }
        });

        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this didn't return

                weatherDataService.getCityID(ed_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned an ID of "+cityID, Toast.LENGTH_SHORT).show();
                    }
                });



                // Instantiate the RequestQueue.
                //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                // Add a request (in this example, called stringRequest) to your RequestQueue.

                /*
                * In class WeatherDataService
                * */

                //queue.add(request);

                // Request a string response from the provided URL.
                //JSON Object request == StringRequest
                /*StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                //textView.setText();

                                Toast.makeText(MainActivity.this, "Response is: "+ response, Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                    }

                });*/

                // Add the request to the RequestQueue.
                //queue.add(stringRequest);

                //Toast.makeText(MainActivity.this, "You Clicked me", Toast.LENGTH_SHORT).show();
            }
        });

    }
}