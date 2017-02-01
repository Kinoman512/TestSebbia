package sebbia.ru.testsebbia;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import sebbia.ru.testsebbia.model.Categorie;
import sebbia.ru.testsebbia.view.fragment.CatFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MyFragmentManager.setActivity(this);
        toCatFragment();





//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                MyFragmentManager.replaceFragment( new NewsFragment());
//
//            }
//        });
    }

    public static void toCatFragment(){

        NetManager.getCategories(new MyListner() {
            @Override
            public void onSucceces(Object obj) {

                ArrayList<Categorie> list = (ArrayList<Categorie>) obj;

                CatFragment newFragment = new CatFragment();
                Bundle args = new Bundle();
                args.putSerializable("list", list);
                newFragment.setArguments(args);

                MyFragmentManager.replaceFragment(newFragment);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void replaceFragment(Fragment mFeedFragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_main, mFeedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
