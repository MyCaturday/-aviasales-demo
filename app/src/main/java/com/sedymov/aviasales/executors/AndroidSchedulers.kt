package com.sedymov.aviasales.executors

import com.sedymov.aviasales.core.executors.RxSchedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidSchedulers : RxSchedulers {

    override val mainThreadScheduler: Scheduler = AndroidSchedulers.mainThread()

    override val ioScheduler: Scheduler = Schedulers.io()

    override val computationScheduler: Scheduler = Schedulers.computation()
}