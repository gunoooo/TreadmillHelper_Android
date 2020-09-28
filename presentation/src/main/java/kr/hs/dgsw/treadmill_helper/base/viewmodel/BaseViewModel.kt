package kr.hs.dgsw.treadmill_helper.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent

/**
 * @author gunwoo
 *
 * BaseViewModel
 */
@Suppress("UNCHECKED_CAST")
open class BaseViewModel : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }
    fun setIsLoading(boolean: Boolean) {
        isLoading.value = boolean
    }

    val onErrorEvent = SingleLiveEvent<Throwable>()

    fun addDisposable(single: Single<*>, observer: DisposableSingleObserver<*>) {
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer as SingleObserver<Any>) as Disposable)
    }
    fun addDisposable(completable: Completable, observer: DisposableCompletableObserver) {
        compositeDisposable.add(completable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer))
    }
    fun addDisposable(observable: Observable<*>, observer: DisposableObserver<*>): Disposable {
        val disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer as Observer<Any>) as Disposable
        compositeDisposable.add(disposable)
        return disposable
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}