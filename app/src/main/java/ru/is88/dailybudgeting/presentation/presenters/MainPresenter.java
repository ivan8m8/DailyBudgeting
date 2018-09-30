package ru.is88.dailybudgeting.presentation.presenters;

import java.util.List;

import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View<T> extends BaseView {
        void showItemList(List<T> tList);
        void onClickItem(long id, int position);
    }

    void getItemList(int year, int month);
}
