package com.example.assignmentdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewRecords extends AppCompatActivity {
    ListView listview;
    DBHelper DB;
    TextView view;
    SwipeRefreshLayout refreshLayout;
    public void refreshList()
    {
        Cursor res=DB.viewData();
        ArrayList<String> std_records=new ArrayList<>();
        while(res.moveToNext())
        {
            //std_records.add(res.getString(0));
            String stid=res.getString(0);
            String stname=res.getString(1);
            String pg=res.getString(2);
            int n=res.getCount();
            view.setText("RECORDS " + n);
            String rd=stid +"\n"+stname+"\n" + pg;
            std_records.add(rd);
            final ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,std_records);
            listview.setAdapter(adapter);
            refreshLayout.setRefreshing(false);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        listview=findViewById(R.id.records);
        refreshLayout=findViewById(R.id.sr_layout);
        view=findViewById(R.id.textView);
        DB=new DBHelper(this);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {

                refreshList();

            }
        });




        Cursor res=DB.viewData();
        ArrayList<String> std_records=new ArrayList<>();
        if(res.getCount()==0)
        {
            Toast.makeText(ViewRecords.this,"no record exists",Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext())
        {
            //std_records.add(res.getString(0));
            String stid=res.getString(0);
            String stname=res.getString(1);
            String pg=res.getString(2);
            int n=res.getCount();
            view.setText("RECORDS " + n);
            String rd=stid +"\n"+stname+"\n" + pg;
            std_records.add(rd);
            final ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,std_records);
            listview.setAdapter(adapter);
        }


        //std_records=getResources().getStringArray(R.array.songs_list);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String value=std_records.get(position);
                String[] arr=value.split("\n");
                //int ln=arr.length;
                //Toast.makeText(getApplicationContext(),ln+"\n"+arr[0]+"\n"+arr[1]+"\n"+arr[2],Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), ModifyRecords.class);
                i.putExtra("STUDENT_ID",arr[0]);
                i.putExtra("STUDENT_NAME",arr[1]);
                i.putExtra("STUDENT_PROGRAM",arr[2]);
                startActivity(i);
            }
        });





    }
}