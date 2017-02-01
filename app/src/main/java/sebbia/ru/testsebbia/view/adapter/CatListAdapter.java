package sebbia.ru.testsebbia.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sebbia.ru.testsebbia.MyFragmentManager;
import sebbia.ru.testsebbia.MyListner;
import sebbia.ru.testsebbia.NetManager;
import sebbia.ru.testsebbia.R;
import sebbia.ru.testsebbia.Util;
import sebbia.ru.testsebbia.model.Categorie;
import sebbia.ru.testsebbia.model.New;
import sebbia.ru.testsebbia.view.fragment.NewsFragment;

/**
 * Created by User on 01.02.2017.
 */

public class CatListAdapter extends BaseAdapter {

    Context mContext;
    List<Categorie> listCats;

    public CatListAdapter(Context mContext, List<Categorie> listCats) {
        this.mContext = mContext;
        this.listCats = listCats;
    }


    @Override
    public int getCount() {
        return listCats.size();
    }

    @Override
    public Object getItem(int position) {
        return listCats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;// lcw.get(position).getId();
    }

    @Override
    public View getView(int position, View someView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (someView == null) {
            someView = inflater.inflate(R.layout.item_cat, parent, false);
            TextView catText = (TextView) someView.findViewById(R.id.cat);

            final Categorie cat = listCats.get(position);

            catText.setText(cat.getName());

            someView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NetManager.getNewsByCat(cat.getId(), 0, new MyListner() {
                        @Override
                        public void onSucceces(Object obj) {



                            ArrayList<New> list = (ArrayList<New>) obj;

                            if(list.size()== 0){
                                Util.showMassage("Новостей в данной категории нет!");
                                return;
                            }

                            NewsFragment newFragment = new NewsFragment();
                            Bundle args = new Bundle();
                            args.putSerializable("list", list);
                            args.putSerializable("num", 0);
                            args.putSerializable("id", cat.getId());
                            newFragment.setArguments(args);

                            MyFragmentManager.replaceFragment(newFragment);
                        }
                    });


                }
            });

        }


        return someView;


    }
}