package com.example.shobhraj.wisqli;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText your_name,to_update_id;
    Button submission,view_data,update_data;

    public static DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DatabaseHelper(MainActivity.this);
        your_name=findViewById(R.id.name);
        to_update_id=findViewById(R.id.student_id);

        submission=findViewById(R.id.submit);
        view_data=findViewById(R.id.view_data);
        update_data=findViewById(R.id.delete);

        addData();
        viewall();
        updateData();
    }

    public void addData(){
        submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinserted=mydb.insertData(your_name.getText().toString());
                if(isinserted==true)
                    Toast.makeText(MainActivity.this,"inserted successfully",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"oops some error occured ",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewall(){
        view_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getData();

                if(res.getCount()==0)
                {
                    showmessage("error","database is empty");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("ID : "+ res.getString(0) +"\n");
                    buffer.append(" NAME : " + res.getString(1) + "\n");
                }

                showmessage("data",buffer.toString());
            }
        });
    }

    public void showmessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData()
    {
        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdate=mydb.updateData(to_update_id.getText().toString(),your_name.getText().toString());
                if(isupdate==true)
                    Toast.makeText(MainActivity.this,"Database updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"error in updating", Toast.LENGTH_LONG).show();
            }
        });
    }
}
