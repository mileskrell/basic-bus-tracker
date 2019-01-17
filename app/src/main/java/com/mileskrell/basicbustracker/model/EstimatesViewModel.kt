package com.mileskrell.basicbustracker.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mileskrell.basicbustracker.repo.Repository

/**
 * View model storing a list of Stops
 */
class EstimatesViewModel(application: Application) : AndroidViewModel(application) {
    private val _estimates: MutableLiveData<List<Stop>> = MutableLiveData()

    val estimates = _estimates as LiveData<List<Stop>>

    val repository: Repository = Repository()

    suspend fun fetchNewEstimates() {
        _estimates.postValue(repository.fetchInitialData())
    }
}
