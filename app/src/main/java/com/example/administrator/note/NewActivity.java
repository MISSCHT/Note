package com.example.administrator.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    private EditText titleEdit;
    private EditText bodyEdit;
    private Button confirmBtn;
    NoteDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editactivity);
            dbHelper=new NoteDbHelper(NewActivity.this);
            dbHelper.open();
        titleEdit=findViewById(R.id.title);
        bodyEdit=findViewById(R.id.body);
        confirmBtn=findViewById(R.id.confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO保存数据
                String title=titleEdit.getEditableText().toString();
                String body=bodyEdit.getEditableText().toString();
                if (title!=null&&body!=null){
                    int id=dbHelper.createNote(title,body);
                    if (id>0){
                        Toast.makeText(NewActivity.this,"增加成功",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(NewActivity.this,"增加失败",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    return;
                }
                finish();
            }
        });
    }


}
