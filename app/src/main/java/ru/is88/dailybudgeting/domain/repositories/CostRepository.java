package ru.is88.dailybudgeting.domain.repositories;

import ru.is88.dailybudgeting.domain.models.Cost;

public interface CostRepository {

    void insert(Cost cost);
    void update(Cost cost);
    void delete(Cost cost);
}
