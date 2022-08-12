package com.example.greenlightexample.presentation.zone

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenlightexample.data.ResponseRepository
import com.example.greenlightexample.data.models.LocationClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneViewModel @Inject constructor(private val repository: ResponseRepository) : ViewModel() {


    val zoneData: LiveData<List<LocationClass>> = Transformations.map(repository.responseData) {
        if(it == null) return@map emptyList()
        return@map it.responseZone
    }

    fun getData() {
        viewModelScope.launch {
            repository.fetchDetails()

        }
    }
}