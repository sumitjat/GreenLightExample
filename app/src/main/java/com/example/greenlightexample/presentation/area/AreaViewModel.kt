package com.example.greenlightexample.presentation.area

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greenlightexample.data.ResponseRepository
import com.example.greenlightexample.data.models.LocationClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(private val repository: ResponseRepository) : ViewModel() {

    var selected: String = ""


    private val searchData: MutableLiveData<String> = MutableLiveData()


    val areaData: LiveData<List<LocationClass>> = MediatorLiveData<List<LocationClass>>().apply {
        addSource(repository.responseData) {
            val list = it.responseArea
            val filter = searchData.value ?: ""
            value = getFilteredList(list, filter)
        }
        addSource(searchData) {
            val list =
                repository.responseData.value?.responseArea ?: return@addSource
            val filter = it ?: ""
            value = getFilteredList(list, filter)
        }
    }


    private fun getFilteredList(
        list: List<LocationClass.ResponseArea>,
        filters: String
    ): List<LocationClass> {
        return if (filters.isEmpty()) {
            list.filter { it.territory.contains(selected) }
        } else {
            list.filter {
                it.territory.contains(selected) && it.area.toLowerCase()
                    .contains(filters.toLowerCase())
            }
        }
    }

    fun setFilter(selectedTerritory: String) {
        selected = selectedTerritory
    }

    fun searchValue(query: String) {
        searchData.postValue(query)
    }
}