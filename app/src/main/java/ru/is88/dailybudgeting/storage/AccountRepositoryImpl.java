//package ru.is88.dailybudgeting.storage;
//
//import android.support.annotation.NonNull;
//
//import com.raizlabs.android.dbflow.sql.language.SQLite;
//
//import java.util.List;
//
//import ru.is88.dailybudgeting.domain.Repository;
//import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
//import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
//import ru.is88.dailybudgeting.domain.models.accounts.Income;
//import ru.is88.dailybudgeting.storage.converters.StorageModelConverter;
//import ru.is88.dailybudgeting.storage.model.TableIncome;
//
//public class AccountRepositoryImpl<T extends AbstractAccount> implements Repository<AbstractAccount> {
//
//    @Override
//    public void insert(@NonNull AbstractAccount item) {
//
//    }
//
//    @Override
//    public void update(@NonNull AbstractAccount item) {
//
//    }
//
//    @Override
//    public AbstractAccount getItemById(long id) {
//        return null;
//    }
//
//    @Override
//    public List<AbstractAccount> getItemList(int month, int year) {
//        return null;
//    }
//
//    @Override
//    public void insert(@NonNull Income income) {
//        TableIncome dbItem = StorageModelConverter.convertToStorageModel(income);
//        dbItem.synced = false;
//        dbItem.insert();
//        //TODO: Sync
//    }
//
//    @Override
//    public void update(Income income) {
//        TableIncome dbItem = StorageModelConverter.convertToStorageModel(income);
//        dbItem.synced = false;
//        dbItem.update();
//        //TODO: Sync
//    }
//
//    @Override
//    public List<Income> getIncomeList(int month, int year) {
//        return null;
//    }
//
//    @Override
//    public void insert(FixedExpense fixedExpense) {
//
//    }
//
//    @Override
//    public void update(FixedExpense fixedExpense) {
//
//    }
//
//    @Override
//    public List<FixedExpense> getFixedExpenseList(int month, int year) {
//        return null;
//    }
//
//    @Override
//    public AbstractAccount getAccountById(long id) {
//        return null;
//    }
//}
