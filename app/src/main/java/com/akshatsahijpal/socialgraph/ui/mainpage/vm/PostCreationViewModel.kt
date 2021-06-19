package com.akshatsahijpal.socialgraph.ui.mainpage.vm

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshatsahijpal.socialgraph.repository.main.MainBulkRepository
import com.akshatsahijpal.socialgraph.util.Constants
import com.akshatsahijpal.socialgraph.util.Event
import com.akshatsahijpal.socialgraph.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostCreationViewModel @Inject constructor(
    private var repo: MainBulkRepository,
    context: Context,
    private var dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private var _postStatus = MutableLiveData<Event<Resource<Any>>>()
    var observerPostStatus: LiveData<Event<Resource<Any>>> = _postStatus

    fun createPost(imageUri: Uri, text: String) {
        if (text.isEmpty()) {
            _postStatus.postValue(Event(Resource.Fail(message = Constants.EMPTY_FIELD_ERROR)))
        } else {
            _postStatus.postValue(Event(Resource.Loading()))
            viewModelScope.launch(dispatcher) {
                val res: Resource<Any> = repo.createPost(imageUri, text)
                _postStatus.postValue(Event(res))
            }
        }
    }

}
