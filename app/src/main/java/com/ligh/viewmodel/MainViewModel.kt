package com.ligh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {



    fun  test(){
        viewModelScope.launch {
            val job1 = launch {
                repeat(20){
                    println("1")
                }
            }
            val job2 = launch {
                repeat(20){
                    println("2")
                }
            }
            job1.join()
            println("Finished")
        }




    }

}