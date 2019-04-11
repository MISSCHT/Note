package com.example.administrator.note;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private ListView listView;
    private NotesAdapter adapter;
//    private List<Notebean> notebeanlist;
    List<Notebean> notedblist;
    NoteDbHelper dbHelper;
    private String LOGTAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
//        initDate();

        btnAdd=findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNote();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDate();
        initView();
    }

    //新增笔记
    private void createNote(){
        Intent intent=new Intent(MainActivity.this,NewActivity.class);
        startActivity(intent);
    }
    void initDate(){
        dbHelper=new NoteDbHelper(MainActivity.this);
        dbHelper.open();
    }
    void initView() {
        notedblist = dbHelper.getAllNotes();
        Log.e(LOGTAG, "notedblist" + notedblist + "");
//        notebeanlist = new ArrayList<Notebean>();
//        for (int i = 0; i < 20; i++) {
//            Notebean m = new Notebean();
//            m.setId(i);
//            m.setBody(i + "2222222222");
//            m.setTitle(i + "1111111111111");
//            notebeanlist.add(m);
//          int rowId=dbHelper.createNote(i+"555555555555",i+"6666666666");
//  }
        listView = (ListView)findViewById(R.id.list);
        adapter = new NotesAdapter(this, R.layout.note_item, notedblist);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(MainActivity.this).setTitle("对话框")
                        .setMessage("确定删除？")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notedblist.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create();
                dialog.show();
                return true;
            }
        });
        //listView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Notebean m=notebeanlist.get(position);
//                Toast.makeText(MainActivity.this,m.toString(),Toast.LENGTH_LONG).show();
                Notebean m = new Notebean();
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("title", notedblist.get(position).getTitle());
                intent.putExtra("body",notedblist.get(position).getBody());
                intent.putExtra("id", notedblist.get(position).getId());
                startActivity(intent);
            }
        });
        //listView长按删除事件


    }
}
