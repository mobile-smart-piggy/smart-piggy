package com.example.mobilesmartpiggy

data class PigsState (
    val pigs: List<Pigs> = emptyList(),
    val parentFemale:String = "",
    val parentMale:String = "",
    val isAddingPig: Boolean = false,
    val sortType: SortType = SortType.PARENT_FEMALE
)