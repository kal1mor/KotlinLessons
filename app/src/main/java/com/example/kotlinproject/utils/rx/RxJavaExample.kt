package com.example.kotlinproject.utils.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class RxJavaExample {

    fun observableJust1(){
        //1 способ
        val developers: Observable<String> = Observable.just(
            "IOS",
            "Android",
            "Flutter"
        )
        developers.doAfterNext {
            Log.w("next", it)
        }.doOnError {
            //here will be exception if throw
        }.doOnComplete {
            Log.w("completed", "finish")
        }.subscribe()
    }

    fun observableJust2(){
        //2 способ
        val developersAnotherWay: Observable<String> = Observable.just(
            "IOS",
            "Android",
            "Flutter"
        )
        developersAnotherWay.subscribe({
            Log.w("next", it)
        }, {
            //here will be exception if throw
        }, {
            Log.w("completed", "finish")
        })
    }

    fun observbleJust3(){
        val devList = listOf<String>("IOS", "Android", "Flutter")

        Observable.create<String> { emitter ->
            devList.forEach { developer ->
                if (developer.isEmpty()) {
                    emitter.onError(Exception("value is empty"))
                }
                emitter.onNext(developer)
            }
        }.doAfterNext {
            Log.w("next from create", it)
        }.doOnError {

        }.doOnComplete {
            Log.w("complete from create", "finished")
        }.subscribe({}, {
            Log.w("error", it.message.toString())
        })
    }

    fun flatMap(){
        val developersFlatMap = Observable.just("IOS", "Android", "Flutter")
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.just("$it 2")
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.w("result", it.toString())
            }
    }

    fun zip(){
        Observable.zip(
            Observable.just("IOS", "Android", "Flutter"),
            Observable.just("Swift", "Kotlin", "Dart")
        ) { dev, lang ->
            "$dev writes in $lang"
        }.subscribe({ Log.w("result zip", it) })
    }

    fun concat(){
        val devs = Observable.just("IOS", "Android", "Flutter")
        val langs = Observable.just("Swift", "Kotlin", "Dart")
        val comps = Observable.just("Apple", "Google")

        Observable.concat(devs, langs, comps)
            .subscribe() { Log.w("result concat", it.toString()) }
    }

    fun publishSubjectExample(){
        val publishSubject = PublishSubject.create<Int>()
        publishSubject.onNext(1)
        publishSubject.onNext(2)
        publishSubject.onNext(3)
        publishSubject.subscribe({Log.w("publish value", it.toString())})
        publishSubject.onNext(4)
        publishSubject.onNext(5)
        publishSubject.onNext(6)
        publishSubject.subscribe({Log.w("publish value2", it.toString())})
    }
    fun asyncSubjectExample(){
        val asyncSubject = AsyncSubject.create<Int>()
        asyncSubject.onNext(1)
        asyncSubject.onNext(2)
        asyncSubject.onNext(3)
        asyncSubject.subscribe({Log.w("async early", it.toString())})
        asyncSubject.onNext(4)
        asyncSubject.onNext(5)
        asyncSubject.subscribe({Log.w("async later", it.toString())})
        asyncSubject.onNext(6)
        asyncSubject.onComplete()
    }

    fun behaviorSubjectExample(){
        val behaviorSubject = BehaviorSubject.create<Int>()
        behaviorSubject.onNext(1)
        behaviorSubject.onNext(2)
        behaviorSubject.onNext(3)
        behaviorSubject.subscribe({Log.w("behavior early", it.toString())})
        behaviorSubject.onNext(4)
        behaviorSubject.onNext(5)
        behaviorSubject.subscribe({Log.w("behavior later", it.toString())})
        behaviorSubject.onNext(6)
    }

    fun replySubjectExample(){
        val replaySubject = ReplaySubject.create<Int>()
        replaySubject.onNext(1)
        replaySubject.onNext(2)
        replaySubject.onNext(3)
        replaySubject.subscribe({Log.w("early", it.toString())})
        replaySubject.onNext(4)
        replaySubject.onNext(5)
        replaySubject.onNext(6)
        replaySubject.subscribe({Log.w("later", it.toString())})
    }
}