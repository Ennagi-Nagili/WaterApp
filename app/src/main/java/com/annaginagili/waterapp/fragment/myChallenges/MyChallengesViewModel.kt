package com.annaginagili.waterapp.fragment.myChallenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaginagili.waterapp.model.Challenge
import com.annaginagili.waterapp.room.ChallengeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyChallengesViewModel: ViewModel() {
    private var challenges = MutableLiveData<List<Challenge>>()

    fun getChallenges(challengeDao: ChallengeDao) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = challengeDao.getAll()
            challenges.postValue(result)
        }
    }

    fun observeChallenges(): LiveData<List<Challenge>> {
        return challenges
    }
}