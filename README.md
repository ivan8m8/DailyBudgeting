# DailyBudgeting
Description appears soon...<br>
## The app strcture
**1. The whole DailyBudgeting Android app project consists of two modules:**

   * this one, which is within this repo – it's actually the app itself, though, without a sync function;
   * syncing user's data – it's implemented as a separate module for security measures.

   So, if you consider to compile the app or clone the repo, you may want to comment out syncing lines in the code.
   
   
**2. This project has two branches:**

   * ``kotlin-mvvm-rx-aac``. It's a current branch that is supported and is used to compile the DailyBudgeting app for the Google Play. The following stack is used: ``Kotlin``, ``MVVM``, ``RxJava``, ``Android Architecture Components``;
   * ``java-ca-dbflow``. It's a previous, the first branch, that is no longer supported. The following stack is used: ``Java``, ``Clean Architecture``, ``DBFlow``.

   Also, the SOLID principles are surely respected for both branches.
