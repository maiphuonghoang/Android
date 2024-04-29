package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.lview);
        initListView();
        tv = findViewById(R.id.tv);
        registerForContextMenu(tv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), selection, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListView() {
        String[] list = getResources().getStringArray(R.array.tech);
        adapter = new ArrayAdapter<String>(this, R.layout.item, list);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.mFile:
//                Toast.makeText(this,"File", Toast.LENGTH_SHORT ).show();
//                break;
//            case R.id.mEmail:
//                Toast.makeText(this,"Email", Toast.LENGTH_SHORT ).show();
//                break;
//            case R.id.mPhone:
//                Toast.makeText(this,"Phone", Toast.LENGTH_SHORT ).show();
//                break;
//            case R.id.mExit:
//                System.exit(0);
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.color_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.mBlue:
//                tv.setTextColor(Color.BLUE);
//                break;
//            case R.id.mViolet:
//                tv.setTextColor(Color.RED);
//                break;
//            case R.id.mYellow:
//                tv.setTextColor(Color.YELLOW);
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }


}