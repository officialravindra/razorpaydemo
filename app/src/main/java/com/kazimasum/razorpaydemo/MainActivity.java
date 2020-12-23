package com.kazimasum.razorpaydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener
{
   Button paybtn;
   TextView paytext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());

        paytext=(TextView)findViewById(R.id.paytext);
        paybtn=(Button)findViewById(R.id.paybtn);

            paybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   makepayment();
                }
            });

    }

    private void makepayment()
    {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_exIJ43aNyVjo9T");

        checkout.setImage(R.drawable.officialravindra);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "RAVINDRA RAJPUT");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://www.facebook.com/photo?fbid=1544408115714581&set=a.100252613463479");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "30000");//300 X 100
            options.put("prefill.email", "ravindra@example.com");
            options.put("prefill.contact","9649868677");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s)
    {
        paytext.setText("Successful payment ID :"+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        paytext.setText("Failed and cause is :"+s);
    }
}