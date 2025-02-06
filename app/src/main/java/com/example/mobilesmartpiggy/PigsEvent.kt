package com.example.mobilesmartpiggy

sealed interface PigsEvent {
    object SavePig: PigsEvent
    data class SetParentFemale(val parentFemale: String): PigsEvent
    data class SetParentMale(val parentMale : String): PigsEvent
    object ShowDialog: PigsEvent
    object HideDialog: PigsEvent
    data class SortPigs(val sortType: SortType): PigsEvent
    data class  DeletePig(val pigs: Pigs): PigsEvent
}