package com.example.greenlightexample.presentation.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.greenlightexample.data.ResponseRepository
import com.example.greenlightexample.data.models.LocationClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegionViewModel @Inject constructor(private val repository: ResponseRepository) :
    ViewModel() {

    var selected: String = ""


    val regionData: LiveData<List<LocationClass>> = Transformations.map(repository.responseData) {
        it.responseRegion.filter { zone ->
            return@filter zone.territory.contains(selected)
        }
    }

    fun setFilter(selectedTerritory: String) {
        selected = selectedTerritory
    }
}