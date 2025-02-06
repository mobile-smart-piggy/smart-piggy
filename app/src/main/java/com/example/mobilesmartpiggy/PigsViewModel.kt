package com.example.mobilesmartpiggy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PigsViewModel (
    private val dao: PigsDAO
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.PARENT_FEMALE)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _pigs = _sortType
        .flatMapLatest { _sortType ->
            when(_sortType){
                SortType.PARENT_FEMALE -> dao.getPigsByMom()
                SortType.PARENT_MALE -> dao.getPigsByDad()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(PigsState())
    private val state = combine(_state, _sortType, _pigs) { state, sortType, pigs ->
        state.copy(
            pigs = pigs,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), PigsState())

    fun onEvent(event: PigsEvent){
        when(event) {
            is PigsEvent.DeletePig -> {
                viewModelScope.launch {
                    dao.deletePig(event.pigs)
                }
            }
            PigsEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingPig = false
                ) }
            }
            PigsEvent.SavePig -> {
                val parentFemale = state.value.parentFemale
                val parentMale = state.value.parentFemale

                if(parentFemale.isBlank() || parentMale.isBlank()){
                    return
                }

                val pigs = Pigs(
                    parentFemale = parentFemale,
                    parentMale = parentMale
                )
                viewModelScope.launch {
                    dao.upsertPig(pigs)
                }
                _state.update { it.copy(
                    isAddingPig = false,
                    parentFemale = "",
                    parentMale = "",
                ) }
            }
            is PigsEvent.SetParentFemale -> {
                _state.update { it.copy(
                    parentFemale = event.parentFemale
                ) }
            }
            is PigsEvent.SetParentMale -> {
                _state.update { it.copy(
                    parentMale = event.parentMale
                ) }
            }
            PigsEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingPig = true
                ) }
            }
            is PigsEvent.SortPigs -> {
                _sortType.value = event.sortType
            }
        }
    }
}