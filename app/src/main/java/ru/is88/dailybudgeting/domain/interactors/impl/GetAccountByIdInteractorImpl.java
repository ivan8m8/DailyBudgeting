//package ru.is88.dailybudgeting.domain.interactors.impl;
//
//import ru.is88.dailybudgeting.domain.mExecutor.Executor;
//import ru.is88.dailybudgeting.domain.mExecutor.MainThread;
//import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
//import ru.is88.dailybudgeting.domain.interactors.GetAccountByIdInteractor;
//import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
//import ru.is88.dailybudgeting.domain.repositories.AccountRepository;
//
//public class GetAccountByIdInteractorImpl extends AbstractInteractor implements GetAccountByIdInteractor {
//
//    private long id;
//    private AccountRepository accountRepository;
//    private GetAccountByIdInteractor.Callback callback;
//
//    public GetAccountByIdInteractorImpl(Executor threadExecutor, MainThread mMainThread,
//                                        long id,
//                                        AccountRepository accountRepository,
//                                        Callback callback) {
//        super(threadExecutor, mMainThread);
//
//        this.id = id;
//        this.accountRepository = accountRepository;
//        this.callback = callback;
//    }
//
//    @Override
//    public void run() {
//        final AbstractAccount abstractAccount = accountRepository.getAccountById(this.id);
//        if (abstractAccount == null) {
//            mMainThread.post(new Runnable() {
//                @Override
//                public void run() {
//                    callback.onAccountNotFound();
//                }
//            });
//        } else {
//            mMainThread.post(new Runnable() {
//                @Override
//                public void run() {
//                    callback.onAccountRetrieved(abstractAccount);
//                }
//            });
//        }
//    }
//}
