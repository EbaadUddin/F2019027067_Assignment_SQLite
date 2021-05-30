package com.example.assignmentdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    EditText Id,Name,Program;
    ViewPager viewPager;
    TabLayout tabLayout;
    Button insert,update,delete,viewData;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Id=findViewById(R.id.std_Id);
        Name=findViewById(R.id.std_Name);
        Program=findViewById(R.id.std_Class);
        insert=findViewById(R.id.btn_update);
        //delete=findViewById(R.id.btn_Delete);
        //update=findViewById(R.id.btn_Update);
        viewData=findViewById(R.id.btn_delete);

        DB=new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=Id.getText().toString();
                String contacttxt=Name.getText().toString();
                String addresstxt=Program.getText().toString();

                Boolean checkInsert=DB.insert(nametxt,contacttxt,addresstxt);

                if(checkInsert==true)
                {
                    Toast.makeText(MainActivity.this,"new record inserted",Toast.LENGTH_SHORT).show();
                    Id.setText("");
                    Name.setText("");
                    Program.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this,"no record inserted",Toast.LENGTH_SHORT).show();
                }

            }
        });




        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), ViewRecords.class);
                startActivity(i);

            }
        });

    }
}