package com.example.administrator.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    NoteDbHelper dbHelper;
    private EditText titleEdit;
    private EditText bodyEdit;
    private Button confirmBtn;
    private int rowId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editactivity);
        titleEdit=findViewById(R.id.title);
        bodyEdit=findViewById(R.id.body);
        confirmBtn=findViewById(R.id.confirm);
        dbHelper=new NoteDbHelper(EditActivity.this);
        dbHelper.open();
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO更新数据
                String title=titleEdit.getEditableText().toString();
                String body=bodyEdit.getEditableText().toString();
                int id=rowId;
                boolean result=dbHelper.updateNote(id,title,body);
                if (result){
                    Toast.makeText(EditActivity.this,"编辑成功",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(EditActivity.this,"编辑失败",Toast.LENGTH_LONG).show();
                }

                finish();
            }
        });
        Bundle extras=getIntent().getExtras();
        if (extras!=null){
            String title=extras.getString("title");
            String body=extras.getString("body");
            rowId =extras.getInt("id");
            if (title!=null){
                titleEdit.setText(title);
            }
            if (body!=null){
                bodyEdit.setText(body);
            }
        }
    }
}
