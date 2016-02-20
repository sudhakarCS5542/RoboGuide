package com.example.sudhakareddy.robo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    Button captureButton;
    ImageView iv;
    static final int CAM_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getData = (Button) findViewById(R.id.GetDirections);
        EditText editBox = (EditText) findViewById(R.id.jsondata);
        captureButton = (Button) findViewById(R.id.Explore);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://jsonplaceholder.typicode.com/posts/1";
                new RestOperations().execute(URL);
            }
        });
    }

    private File getFile(){
        File imageFolder = new File("sdcard/image_storage");

        if(imageFolder.exists()){
        imageFolder.mkdir();
        }

        File image_file = new File(imageFolder, "image.jpg");
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class RestOperations extends AsyncTask<String, Void, Void>{

        String context;
        String error;
        TextView parseJson = (TextView) findViewById(R.id.jsondata);
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Fetching Data, Please wait..");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            BufferedReader br = null;

            URL url = null;
            try {
                url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);

                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                while((line = br.readLine())!=null){
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                }
                context = sb.toString();
            }
            catch (IOException e) {
                error = e.getMessage();
            }
            finally {
                try {
                    br.close();
                } catch (IOException e) {
                    error = e.getMessage();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            if (error != null) {

                String output = null;
                try {
                    JSONObject jsonObject = new JSONObject(context);
                    JSONArray jArray = jsonObject.optJSONArray("ANDROID");

                    for (int i=0; i<jArray.length(); i++){
                        JSONObject list = jArray.getJSONObject(i);

                        String userId = list.getString("userId");
                        String title = list.getString("title");

                        output = "userID " + userId + System.getProperty("line.separator") + "title" + title;
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

