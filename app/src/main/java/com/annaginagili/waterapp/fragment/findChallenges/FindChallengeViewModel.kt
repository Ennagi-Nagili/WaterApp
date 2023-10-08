package com.annaginagili.waterapp.fragment.findChallenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaginagili.waterapp.model.Challenge
import com.annaginagili.waterapp.room.ChallengeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FindChallengeViewModel: ViewModel() {
    private var challenges = MutableLiveData<List<Challenge>>()
    private var add = MutableLiveData<Boolean>()

    fun getChallenges(challengeDao: ChallengeDao) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = challengeDao.getAll()
            challenges.postValue(result)
        }
    }

    fun addChallenge(challenge: Challenge, challengeDao: ChallengeDao) {
        CoroutineScope(Dispatchers.IO).launch {
            challengeDao.addChallenge(challenge)
            add.postValue(true)
        }
    }

    fun observeChallenges(): LiveData<List<Challenge>> {
        return challenges
    }

    fun observeAdd(): LiveData<Boolean> {
        return add
    }
}