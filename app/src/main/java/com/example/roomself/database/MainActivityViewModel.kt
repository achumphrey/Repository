package com.example.roomself.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var showUsers: MutableLiveData<List<User>> = MutableLiveData()

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var showDbSuccess: MutableLiveData<Boolean> = MutableLiveData()



    var userDao = UserDatabase.getDatabase(application)?.userDao()


    fun addUser(user : User){
        compositeDisposable.add(
            userDao!!.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({showSuccess.value = true},{
                     Log.i("ViewModel error",it.message)
                    showSuccess.value=false})
        )
    }

    fun getAllUsers(){
        compositeDisposable.add(
            userDao!!.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({users -> showUsers.value = users
                    showDbSuccess.value = true

                },{})
        )
    }

}