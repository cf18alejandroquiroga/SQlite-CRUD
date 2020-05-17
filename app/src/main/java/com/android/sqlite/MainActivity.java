package com.android.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Context ctx;

    private EditText attribute1;
    private EditText attribute2;
    private EditText attribute3;

    private Button insertButton;
    private Button deleteButton;
    private Button editLastButton;

    private TextView elementsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;

        attribute1 = findViewById(R.id.attribute1_input);
        attribute2 = findViewById(R.id.attribute2_input);
        attribute3 = findViewById(R.id.attribute3_input);
        insertButton = findViewById(R.id.insert_button);
        deleteButton = findViewById(R.id.delete_button);
        editLastButton = findViewById(R.id.editLast_button);
        elementsList = findViewById(R.id.elements_tv);

        String exampleElementsDisplay = "";
        List<ExampleElement> eeList = AccessData.getAccessData(ctx).getExampleElements();

        if (!eeList.isEmpty()) elementsList.setText(ExampleElementListToString(eeList));

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check the username and password for login
                String att1 = attribute1.getText().toString();
                String att2 = attribute2.getText().toString();
                String att3 = attribute3.getText().toString();

                ExampleElement ee = new ExampleElement(att1, att2, att3);
                AccessData.getAccessData(ctx).saveExampleElement(ee);

                List<ExampleElement> eeList = AccessData.getAccessData(ctx).getExampleElements();
                elementsList.setText(ExampleElementListToString(eeList));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ExampleElement> eeList = AccessData.getAccessData(ctx).getExampleElements();
                for(ExampleElement ee: eeList){
                    AccessData.getAccessData(ctx).deleteExampleElement(ee);
                }

                List<ExampleElement> eeList2 = AccessData.getAccessData(ctx).getExampleElements();
                elementsList.setText(ExampleElementListToString(eeList2));
            }
        });

        editLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ExampleElement> eeList = AccessData.getAccessData(ctx).getExampleElements();
                ExampleElement ee = eeList.get(eeList.size() - 1);

                ee.setAttribute1(attribute1.getText().toString());
                ee.setAttribute2(attribute2.getText().toString());
                ee.setAttribute3(attribute3.getText().toString());

                AccessData.getAccessData(ctx).updateExampleElement(ee);

                List<ExampleElement> eeList2 = AccessData.getAccessData(ctx).getExampleElements();
                elementsList.setText(ExampleElementListToString(eeList));
            }
        });
    }

    private String ExampleElementListToString(List<ExampleElement> eeList){
        String result = "";
        for (ExampleElement ee : eeList){
            result += "Id: " + ee.getId() +"\n" +
                    "Attribute 1: " + ee.getAttribute1() + "\n" +
                    "Attribute 2: " + ee.getAttribute2() + "\n" +
                    "Attribute 3: " + ee.getAttribute3() + "\n\n";
        }
        return result;
    }
}
