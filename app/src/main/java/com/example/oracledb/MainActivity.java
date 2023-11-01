package com.example.oracledb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     TextView txt;
     Button button;
     Button insertButton;
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@20.120.11.141:1521/oratest1";
    //private static final String URL = "jdbc:oracle:thin:@10.64.171.207:1521:orcl";
    private static final String USERNAME = "TSTUSER";
    private static final String PASSWORD = "tstuser";
    private Connection connection;

    Product product;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        productList = new ArrayList<Product>();

       button = findViewById(R.id.button);
       insertButton = findViewById(R.id.insertButton);
       txt = findViewById(R.id.textView);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        button.setOnClickListener(view -> {

            try {
                Class.forName(DRIVER);
                this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                Toast.makeText(this,"Connected",Toast.LENGTH_LONG).show();

                Statement statement = connection.createStatement();

                StringBuilder stringBuilder = new StringBuilder();

                ResultSet resultSet = statement.executeQuery("select productid,productcategory,productname,productprice from online_products");


                while (resultSet.next()){
                    stringBuilder.append(resultSet.getInt(1) + resultSet.getString(2) +resultSet.getString(3) + resultSet.getDouble(4) + "\n");

                    int productId = resultSet.getInt(1);
                    String productCategory = resultSet.getString(2);
                    String productName = resultSet.getString(3);
                    Double productPrice = resultSet.getDouble(4);

                 product   = new Product(productId,productCategory,productName,productPrice, Calendar.getInstance().getTime());

                    productList.add(product);


                }

                for(Product product1:productList)
                {
                    Log.d("Product", "onCreate: " + product1.getProductName());
                }

                txt.setText(productList.get(0).getProductName());

                connection.close();

            }catch (Exception e){
                txt.setText(e.toString());
            }

        });

        insertButton.setOnClickListener(view -> {
            try {
                int id = 113;
                String name = "Minas";
                Class.forName(DRIVER);
                this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                Toast.makeText(this,"Connection is open",Toast.LENGTH_LONG).show();
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate("insert into online_products values(5,'PIZZA','Tikka',1000,sysdate)");
                if(count>0)
                {
                    txt.setText("Record Inserted Successfully");
                }else{
                    txt.setText("faild to Insert Data");
                }
                connection.close();

            }catch (Exception e)
            {
                txt.setText(e.toString());
            }

        });
    }




}