package com.sedymov.aviasales.core.util

import io.reactivex.disposables.Disposable

fun Disposable?.unsubscribe(onError: ((Throwable) -> Unit)?) {

    this?.let { disposable ->

        if (!disposable.isDisposed) {

            try {

                disposable.dispose()

            } catch(t: Throwable) {

                onError?.invoke(t)
            }
        }
    }
}