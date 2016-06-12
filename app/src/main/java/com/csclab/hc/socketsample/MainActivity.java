package com.csclab.hc.socketsample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.OutputStream;
import java.net.Socket;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity implements View.OnClickListener {
    /**
     * Init Variable for IP page
     **/
    EditText inputIP;
    Button ipSend;
    String ipAdd = "";

    /**
     * Init Variable for Page 1
     **/
    EditText inputNumTxt1;
    EditText inputNumTxt2;

    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;

    /**
     * Init Variable for Page 2
     **/
    TextView textResult;

    Button return_button;

    /**
     * Init Variable
     **/
    String oper = "";

    /**
     * Init Variable for Page 1
     **/

    //thread t;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_page);
        inputIP = (EditText) findViewById(R.id.edIP);
        ipSend = (Button) findViewById(R.id.ipButton);

        ipSend.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Func() for setup page 1 **/
                ipAdd = inputIP.getText().toString();
                jumpToMainLayout();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Function for page 1 setup
     */
    public void jumpToMainLayout() {
        //TODO: Change layout to activity_main
        setContentView(R.layout.activity_main);

        //btn = (Button) findViewById(R.id.button);
        //btn.setOnClickListener(this);
        //TODO: Find and bind all elements(4 buttons 2 EditTexts)
        // inputNumTxt1, inputNumTxt2
        //TODO: Find all elements(4 buttons 2 EditTexts)
        inputNumTxt1 = (EditText) findViewById(R.id.etNum1);
        inputNumTxt2 = (EditText) findViewById(R.id.etNum2);

        // btnAdd, btnSub, btnMult, btnDiv
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        //TODO: Set 4 buttons' listener
        // HINT: myButton.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

    }

    /**
     * Function for onclick() implement
     */
    @Override
    public void onClick(View v) {




        float num1 = 0; // Store input num 1
        float num2 = 0; // Store input num 2
        float result = 0; // Store result after calculating

        // check if the fields are empty
        if (TextUtils.isEmpty(inputNumTxt1.getText().toString())
                || TextUtils.isEmpty(inputNumTxt2.getText().toString())) {
            return;
        }

        // read EditText and fill variables with numbers
        num1 = Float.parseFloat(inputNumTxt1.getText().toString());
        num2 = Float.parseFloat(inputNumTxt2.getText().toString());

        // defines the button that has been clicked and performs the corresponding operation
        // write operation into oper, we will use it later for output
        //TODO: caculate result
        switch (v.getId()) {
            case R.id.btnAdd:
                oper = "+";
                result = num1 + num2;
                oper = String.format("%s%s%s%s%s", String.valueOf(num1)," + ",String.valueOf(num2)," = ", String.valueOf(result));
                break;
            case R.id.btnSub:
                oper = "-";
                result = num1 - num2;
                oper = String.format("%s%s%s%s%s", String.valueOf(num1)," - ",String.valueOf(num2)," = ", String.valueOf(result));

                break;
            case R.id.btnMult:
                oper = "*";
                result = num1 * num2;
                oper = String.format("%s%s%s%s%s", String.valueOf(num1)," * ",String.valueOf(num2)," = ", String.valueOf(result));

                break;
            case R.id.btnDiv:
                oper = "/";
                result = num1 / num2;
                oper = String.format("%s%s%s%s%s", String.valueOf(num1)," / ",String.valueOf(num2)," = ", String.valueOf(result));

                break;
            default:
                break;
        }
        // HINT:Using log.d to check your answer is correct before implement page turning
        Log.v("debug", "ANS " + result);
        //TODO: Pass the result String to jumpToResultLayout() and show the result at Result view
        jumpToResultLayout(String.valueOf(result));

        Log.d("Client","Client Send");
        Thread t = new thread();
        t.start();
    }

    class thread extends Thread {
        Socket socket;
        OutputStream out;
        String strToSend = "Hi I'm client";

        public void run() {
            try {
                System.out.println("Client: Waiting to connect...");
                int serverPort = 2000;

                // Create socket connect server
                socket = new Socket(ipAdd, serverPort);
                System.out.println("Connected!");

                // Create stream communicate with server
                out = socket.getOutputStream();
                String strToSend = "Hi I'm client";

                byte[] sendStrByte = new byte[1024];
                //System.arraycopy(strToSend.getBytes(), 0, sendStrByte, 0, strToSend.length());
                System.arraycopy(oper.getBytes(), 0, sendStrByte, 0, oper.length());
                out.write(sendStrByte);

            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }


    }

    /**
     * Function for page 2 (result page) setup
     */
    public void jumpToResultLayout(String resultStr) {
        setContentView(R.layout.result_page);

        //TODO: Bind return_button and textResult form result view
        // HINT: findViewById()
        // HINT: Remember to change type
        textResult = (TextView) findViewById(R.id.textResult);
        return_button = (Button) findViewById(R.id.return_button);


        if (textResult != null) {
            //TODO: Set the result text
            textResult.setText(resultStr);
        }

        if (return_button != null) {
            //TODO: prepare button listener for return button
            // HINT:
            // mybutton.setOnClickListener(new View.OnClickListener(){
            //      public void onClick(View v) {
            //          // Something to do..
            //      }
            // }

            return_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //setContentView(R.layout.activity_main);
                    jumpToMainLayout();
                }
            });
        }
    }


}
