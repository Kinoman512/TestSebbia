package sebbia.ru.testsebbia.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sebbia.ru.testsebbia.MainActivity;
import sebbia.ru.testsebbia.MyFragmentManager;
import sebbia.ru.testsebbia.MyListner;
import sebbia.ru.testsebbia.NetManager;
import sebbia.ru.testsebbia.R;
import sebbia.ru.testsebbia.model.New;
import sebbia.ru.testsebbia.view.adapter.NewsAdapter;

/**
 * Created by User on 01.02.2017.
 */

public class NewsFragment extends Fragment {


    private View rootView;
    private List<New> listNew;
    private int num;
    private int idCat;


    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_news, container, false);
        Context context = inflater.getContext();

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("list")) {

            num = (int) bundle.getSerializable("num");
            idCat = (int) bundle.getSerializable("id");
            listNew = (List<New>) bundle.getSerializable("list");

        } else if (bundle == null) {
//            Toast.makeText(getActivity(), "Error Нет setWords", Toast.LENGTH_LONG).show();
        }


        NewsAdapter mAdapter = new NewsAdapter(context,
                listNew);

        ListView lv = (ListView) rootView.findViewById(R.id.listNews);

        TextView num_news = (TextView) rootView.findViewById(R.id.num_news);
        Button btn_cat = (Button) rootView.findViewById(R.id.btn_cat);

        ImageView left = (ImageView) rootView.findViewById(R.id.left);
        final ImageView right = (ImageView) rootView.findViewById(R.id.right);

        right.setVisibility(View.INVISIBLE);
        if(num == 0){
            left.setVisibility(View.INVISIBLE);
        }else{
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToPage(num - 1);
                }
            });
        }

        NetManager.getNewsByCat(idCat, num + 1, new MyListner() {
            @Override
            public void onSucceces(Object obj) {

                final ArrayList<New> listNextNews = (ArrayList<New>) obj;

                if(listNextNews.size() > 0){
                    right.setVisibility(View.VISIBLE);
                    right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            NewsFragment newFragment = new NewsFragment();
                            Bundle args = new Bundle();
                            args.putSerializable("list", listNextNews);
                            args.putSerializable("num", num + 1);
                            args.putSerializable("id", idCat );
                            newFragment.setArguments(args);

                            MyFragmentManager.replaceFragment(newFragment);
                        }
                    });
                }


            }
        });


        btn_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.toCatFragment();
            }
        });



        num_news.setText("# " + num);
        lv.setAdapter(mAdapter);



        return rootView;
    }


    void goToPage(final int page){
        NetManager.getNewsByCat(idCat, page, new MyListner() {
            @Override
            public void onSucceces(Object obj) {
                ArrayList<New> list = (ArrayList<New>) obj;

                NewsFragment newFragment = new NewsFragment();
                Bundle args = new Bundle();
                args.putSerializable("list", list);
                args.putSerializable("num", page);
                args.putSerializable("id", idCat);
                newFragment.setArguments(args);

                MyFragmentManager.replaceFragment(newFragment);
            }
        });
    }



}
