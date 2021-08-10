package com.learn.tindertemplate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.learn.tindertemplate.myutils.Resource
import com.learn.tindertemplate.repositories.ApiRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

class MainActivityViewModel:ViewModel() {
    private lateinit var apiRepositoryImpl: ApiRepositoryImpl

    fun setApiRepo(apiRepo: ApiRepositoryImpl){
        apiRepositoryImpl=apiRepo
    }
    fun getProfile(url:String)= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val res = apiRepositoryImpl.getProfileData(url)
            if (res.isSuccessful && res.body() != null) {
                emit(Resource.success(data = res.body()))
            } else {
                res.errorBody()?.string().let {
                    if (it != null) {
                        val jObjError = JSONObject(it)
                        val message = jObjError.getJSONObject("error").getString("message")
                        emit(Resource.error(data = null,message = message))
                    }else{
                        emit(Resource.error(data = null, message = res.message()))
                    }

                }
            }

        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}