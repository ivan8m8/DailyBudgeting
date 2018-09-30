package ru.is88.dailybudgeting.domain;

import android.support.annotation.NonNull;

import java.util.List;

public interface Repository<T> {

    void insert(@NonNull T item);
    void update(@NonNull T item);

    T getItemById(long id);
    List<T> getItemList(int year, int month);
}
