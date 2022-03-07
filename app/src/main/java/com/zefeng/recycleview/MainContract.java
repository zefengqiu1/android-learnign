package com.zefeng.recycleview;

import com.zefeng.recycleview.entity.User;

import java.util.List;

public interface MainContract {
    interface View {
        void showloading();
        void hideloading();
        void publicData(List<User> userlist);
        void showMessage();

    }

    interface Presenter{
        void bindView(MainContract.View view);
        void unbindView();
        void onViewCreated();
        void onItemClicked();
        void onBackClicked();
        void removeUser(int pos);

        void restoreItem(User user, int position);
    }

    interface Interactor{
        void getList();
    }

    interface Router {
        void finish();
        void openFullJoke();
    }

    interface Repo {
        void getDataFromServer();
    }
}
