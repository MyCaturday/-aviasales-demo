package com.sedymov.aviasales.core.executors

import io.reactivex.Scheduler

interface RxSchedulers {

    val mainThreadScheduler: Scheduler

    val ioScheduler: Scheduler

    val computationScheduler: Scheduler
}