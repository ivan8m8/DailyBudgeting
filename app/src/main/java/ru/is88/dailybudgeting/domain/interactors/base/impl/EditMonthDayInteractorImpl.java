package ru.is88.dailybudgeting.domain.interactors.base.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditMonthDayInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.repositories.MonthDayRepository;

public class EditMonthDayInteractorImpl extends AbstractInteractor implements Interactor {

    private EditMonthDayInteractor.Callback callback;
    private MonthDay updatedMonthDay;
    private MonthDayRepository monthDayRepository;
    private int id;
    private String amountString;
    private String description;

    public EditMonthDayInteractorImpl(Executor threadExecutor,
                                      MainThread mainThread,
                                      EditMonthDayInteractor.Callback callback,
                                      MonthDay updatedMonthDay,
                                      MonthDayRepository monthDayRepository,
                                      int id,
                                      String amountString,
                                      String description) {
        super(threadExecutor, mainThread);

        this.callback = callback;
        this.updatedMonthDay = updatedMonthDay;
        this.monthDayRepository = monthDayRepository;
        this.id = id;
        this.amountString = amountString;
        this.description = description;
    }

    @Override
    public void run() {
        // check if it exists in the database
        int id = updatedMonthDay.getId();
        MonthDay monthDayToEdit = monthDayRepository.getMonthDayById(id);
        if (monthDayToEdit == null){
            monthDayToEdit = new MonthDay(this.id, amountString, description);
            monthDayRepository.insert(monthDayToEdit);
        } else {
            monthDayToEdit.setAmountString(amountString);
            monthDayToEdit.setDescription(description);
            monthDayRepository.update(monthDayToEdit);
        }

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onMonthDayUpdated(updatedMonthDay);
            }
        });
    }
}
