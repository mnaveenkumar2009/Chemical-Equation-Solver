package com.example.naveen.yourworriesargon;

import android.os.StrictMode;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> reactantList;
    private ArrayList<String> reactantQuantity;
    private ArrayList<String> productList;
    private ArrayList<String> productQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //initialization and getting all equations from the data
        reactantList = new ArrayList<>();
        reactantQuantity = new ArrayList<>();
        productList = new ArrayList<>();
        productQuantity = new ArrayList<>();
        getAllEquations();

        //Get Product Button
        Button getProduct= findViewById(R.id.getProduct);
        getProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.userInput);
                String userInput = text.getText().toString();
                String answer=getAnswer(userInput);
                text = findViewById(R.id.product);
                text.setText(answer);
            }
        });
        //Reset Button
        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.userInput);
                text.setText("");
                text = findViewById(R.id.product);
                text.setText("");
            }
        });
    }
    public void getAllEquations(){
        try {
            // Create a URL for the source reactions
            URL url = new URL("https://raw.githubusercontent.com/mnaveenkumar2009/Google-ACSA-workshop/master/Final%20Project/Sources/reactions.txt");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            int count=0;
            while ((str = in.readLine()) != null) {
                if(count%4==0)
                    reactantList.add(str.trim());
                if(count%4==1)
                    reactantQuantity.add(str.trim());
                if(count%4==2)
                    productList.add(str.trim());
                if(count%4==3)
                    productQuantity.add(str.trim());
                count=count%4+1;
            }

            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
    public Integer convertToNumber(String number){
        if(number=="")return 0;
        Integer ans=0,i=0;
        Character c=number.charAt(i++);
        while(c<'0'||c>'9')
            if(i==number.length())
                return 0;
            else
                c=number.charAt(i++);
        while(c<='9'&&c>='0')
        {
            ans=ans*10+c-'0';
            if(i==number.length())return ans;
            c=number.charAt(i++);
        }
        return ans;
    }

    public String getAnswer(String userInput){

        //initialization and declaration
        String answer="";
        ArrayList<String> products;
        ArrayList<Integer> quantity;
        products = new ArrayList<>();
        quantity = new ArrayList<>();
        HashMap<String,Integer> count = new HashMap<>();

        //separate reactants and make count of elements
        String temp="";
        for(int it=0;it<userInput.length();it++){
            if(userInput.charAt(it)==' ')continue;
            String reactantname="";
            if(userInput.charAt(it)=='('){
                it++;
                int countleftB=1,countrightB=0;
                while(countleftB!=countrightB){
                    if(it>=userInput.length())return "Enter Valid String";
                    if(userInput.charAt(it)=='(')countleftB++;
                    if(userInput.charAt(it)==')')countrightB++;
                    if(countleftB==countrightB)break;
                    Character c=userInput.charAt(it++);
                    if(c!=' ')
                    reactantname+=c;
                }
                Integer tempN=convertToNumber(temp);
                if(tempN==0)tempN=1;
                if(!count.containsKey(reactantname))
                    count.put(reactantname,tempN);
                else
                    count.put(reactantname,count.get(reactantname)+tempN);
                temp="";
            }
            else
            {
                temp+= userInput.charAt(it);
            }

        }

        //get products
        Integer reactionsSize=reactantList.size();
        for(int i=0;i<reactionsSize;i++){
            String[] reactant = reactantList.get(i).split(" ");
            String[] rQuantity = reactantQuantity.get(i).split(" ");
            String[] product = productList.get(i).split(" ");
            String[] pQuantity = productQuantity.get(i).split(" ");
            int n=reactant.length,totQuantity=Integer.MAX_VALUE;
            for(int j=0;j<n;j++){
                if(!count.containsKey(reactant[j])){
                    count.put(reactant[j],0);
                }
                if(totQuantity>count.get(reactant[j])/convertToNumber(rQuantity[j]))
                totQuantity=count.get(reactant[j])/convertToNumber(rQuantity[j]);
            }
            for(int j=0;j<n;j++){
                count.put(reactant[j],count.get(reactant[j])-(totQuantity*convertToNumber(rQuantity[j])));
            }
            n=product.length;
            for(int j=0;j<n;j++){
                if(count.containsKey(product[j]))
                    count.put(product[j],count.get(product[j])+(convertToNumber(pQuantity[j])*totQuantity));
                else
                    count.put(product[j],(convertToNumber(pQuantity[j])*totQuantity));
            }
        }
        //adding all elements from map to quantity and products
        Set set2 = count.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry mentry2 = (Map.Entry) iterator2.next();
            if( ((Integer)mentry2.getValue())!=0 ){
                products.add(mentry2.getKey().toString());
                quantity.add((Integer) mentry2.getValue());
            }
        }

        // adding products to the answer
        int n=products.size();
        for(int i=0;i<n;i++) {
            answer += quantity.get(i) + products.get(i);
            if(i!=n-1)answer+=" + ";
        }
        return answer;
    }
}
