package ru.is88.dailybudgeting.domain.repositories;

import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;

public interface MonthDayRepository {

    void update(MonthDay monthDay);
    void insert(MonthDay monthDay);

    MonthDay getMonthDayById(long id);
    List<MonthDay> getAllMonthDays();
}
