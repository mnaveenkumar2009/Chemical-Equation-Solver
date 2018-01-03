package com.example.naveen.yourworriesargon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAllEquations();


        Button reset = (Button) findViewById(R.id.reset);
        // listen for click on reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.userInput);
                text.setText("");
            }
        });
    }
    public void getAllEquations(){
        try {
            // Create a URL for the source reactions
            URL url = new URL("http://sites.google.com/site/androidersite/text.txt");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            /*String str;
            while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
            }
            TextView text = (TextView) findViewById(R.id.product);
            text.setText(str);*/
            //in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }

}
