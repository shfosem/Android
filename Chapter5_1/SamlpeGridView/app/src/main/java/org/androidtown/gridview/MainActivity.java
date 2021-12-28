package org.androidtown.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText editText;

    GridView gridView;
    SingerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView =(GridView) findViewById(R.id.gridView);

        adapter = new SingerAdapter();


        adapter.addItem(new SingleItem("소녀시대","010-1000-1000",20,R.drawable.singer));
        adapter.addItem(new SingleItem("걸스데이","010-2000-2000",22,R.drawable.singer2));
        adapter.addItem(new SingleItem("여자친구","010-3000-3000",21, R.drawable.singer3));
        adapter.addItem(new SingleItem("티아라","010-4000-4000",24,R.drawable.singer4));
        adapter.addItem(new SingleItem("AOA","010-5000-5000",25,R.drawable.singer5));

        gridView.setAdapter(adapter);
        editText =(EditText) findViewById(R.id.editText);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingleItem item = (SingleItem) adapter.getItem(i);
                Toast.makeText(getApplicationContext(),"선택 : "+ item.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingleItem> items= new ArrayList<SingleItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingleItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
           SingleItemView view = new SingleItemView(getApplicationContext());
           SingleItem item = items.get(i);
           view.setName(item.getName());
           view.setMobile(item.getMobile());
           view.setAge(item.getAge());
           view.setImage(item.getResId());
            return view;
        }
    }
}