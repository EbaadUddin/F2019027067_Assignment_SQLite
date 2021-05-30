package com.example.assignmentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyRecords extends AppCompatActivity {
    Button update;
    Button delete;
    EditText name,id,program;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_records);
        update=findViewById(R.id.btn_update);
        delete=findViewById(R.id.btn_delete);
        id=findViewById(R.id.std_Id);
        name=findViewById(R.id.std_Name);
        program=findViewById(R.id.std_Class);
        DB=new DBHelper(this);
        Intent intent = getIntent();

        String s_name = intent.getStringExtra("STUDENT_NAME");
        String s_id = intent.getStringExtra("STUDENT_ID");
        String s_class = intent.getStringExtra("STUDENT_PROGRAM");
        name.setText(s_name);
        id.setText(s_id);
        program.setText(s_class);



        try {


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nametxt = id.getText().toString();
                    String contacttxt = name.getText().toString();
                    String addresstxt = program.getText().toString();

                    Boolean checkUpdate = DB.update(nametxt, contacttxt, addresstxt);

                    if (checkUpdate == true) {
                        Toast.makeText(ModifyRecords.this, " record updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModifyRecords.this, "no record updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        try {


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nametxt = id.getText().toString();
                    Boolean checkDelete = DB.delete(nametxt);

                    if (checkDelete == true) {
                        Toast.makeText(ModifyRecords.this, " record deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModifyRecords.this, "no record deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}