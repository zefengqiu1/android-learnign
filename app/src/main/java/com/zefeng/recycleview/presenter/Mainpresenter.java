package com.zefeng.recycleview.presenter;

import com.zefeng.recycleview.MainContract;
import com.zefeng.recycleview.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

public class Mainpresenter implements MainContract.Presenter {

    private MainContract.Router router;
    private MainContract.Interactor interactor;


    MainContract.View view;
    List<User> userList = new ArrayList<>();
    @Inject
    public Mainpresenter(MainContract.Router router, MainContract.Interactor interactor) {
        this.router = router;
        this.interactor = interactor;
    }

    private void initData() {

        for(int i=0;i<5;i++) {
            Random rand = new Random(); //instance of random class
            int upperbound = 25;
            //generate random values from 0-24
            int int_random = rand.nextInt(upperbound);
            String name = "zefeng"+String.valueOf(int_random);
            userList.add(new User(name,int_random));
        }
    }

    @Override
    public void bindView(MainContract.View view) {
       this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void onViewCreated() {
        if(null!=view){
            initData();
            view.publicData(userList);//这里传入获取的数据，给到前面的view界面。
        }
    }

    @Override
    public void onItemClicked() {

    }

    @Override
    public void onBackClicked() {

    }

    @Override
    public void removeUser(int pos) {
        userList.remove(pos);
    }

    @Override
    public void restoreItem(User user, int position) {
        userList.add(position,user);
    }
}
