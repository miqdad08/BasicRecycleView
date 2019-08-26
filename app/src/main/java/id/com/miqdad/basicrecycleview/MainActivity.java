package id.com.miqdad.basicrecycleview;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdapterRcycler adapterRcycler;
    RecyclerView rvMain;
    Toolbar toolbar;
    ArrayList<ModelMovie> arrayList;
    private String[] id = {"1001", "1002", "1003"};
    private String[] judul = {"Seribu satu Manusia", "Hai Tayo", "Mati Aja antum"};
    private String[] subJudul = {"Miqdad", "Tayo", "iya"};
    private int[] gambar = {R.drawable.menontotn, R.drawable.indo, R.drawable.sejarah};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        arrayList = new ArrayList<>();
        rvMain = findViewById(R.id.rvMain);
        setData();
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setHasFixedSize(true);
        adapterRcycler = new AdapterRcycler(arrayList);
        rvMain.setAdapter(adapterRcycler);


    }

    private void setData(){
        int count = 0;
        for (String id : id){
            arrayList.add(new ModelMovie(id, judul[count], subJudul[count], gambar[count]));
            count++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        search(searchView);
        return true;



    }

    private void search(SearchView searchView){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (adapterRcycler!=null)adapterRcycler.getFilter().filter(s);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.changeLanguange:
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

}
