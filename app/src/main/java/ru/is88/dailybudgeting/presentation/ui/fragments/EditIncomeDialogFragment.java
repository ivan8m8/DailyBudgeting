package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.support.v7.app.AppCompatDialogFragment;

import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.EditItemPresenter;

public class EditIncomeDialogFragment
        extends AppCompatDialogFragment
        implements EditItemPresenter.View<Income> {

    @Override
    public void onItemRetrieved(Income item) {

    }

    @Override
    public void onItemUpdated(Income item) {

    }

    @Override
    public void onProgressStarted() {

    }

    @Override
    public void onProgressFinished() {

    }

    @Override
    public void showError(String message) {

    }
}
