package com.zefeng.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import com.zefeng.recycleview.dependency.DaggerMainComponent;
import com.google.android.material.snackbar.Snackbar;
import com.zefeng.recycleview.dependency.DaggerMainComponent;
import com.zefeng.recycleview.entity.User;
import com.zefeng.recycleview.presenter.Mainpresenter;
import com.zefeng.recycleview.view.SwipeToDeleteCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    private RecyclerView recyclerView;
    CoordinatorLayout coordinatorLayout ;

    @Inject
    Mainpresenter mainpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        DaggerMainComponent.create().inject(this);
        mainpresenter.bindView(this);
        mainpresenter.onViewCreated();
    }



    private void initView() {
        recyclerView  = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        this.coordinatorLayout  = findViewById(R.id.constraint);
        //recyclerView.setAdapter(new CustomAdapter(userList));
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void publicData(List<User> data) {
        //homepresenter call view publishdata 并且传入数据
        CustomAdapter mainAdaper = new CustomAdapter(data);
        recyclerView.setAdapter(mainAdaper);
//        ItemTouchHelper.SimpleCallback itemTouch =
//                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
//                    @Override
//                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                                mainpresenter.removeUser(viewHolder.getAbsoluteAdapterPosition());
//                                mainAdaper.notifyDataSetChanged();
//                    }
//                };
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position = viewHolder.getAbsoluteAdapterPosition();
                    User user = mainAdaper.getData(position);
                    mainpresenter.removeUser(position);
                    mainAdaper.notifyDataSetChanged();
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mainpresenter.restoreItem(user, position);
                        mainAdaper.notifyDataSetChanged();
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }







    @Override
    public void showMessage() {

    }



}