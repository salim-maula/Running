package com.example.running.repositories.ui.viewmodels

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.running.repositories.MainRepository
import javax.inject.Inject

class StatisticsViewModel @ViewModelInject constructor(
        val mainRepository: MainRepository
): ViewModel() {

    var totalTimeRun = mainRepository.getTotalTimeInMillis()
    var totalDistance = mainRepository.getTotalDistance()
    var totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    var totalAvgSpeed = mainRepository.getTotalAvgSpeed()


    var runsSortedByDate = mainRepository.getAllRunsSortedByDate()
}