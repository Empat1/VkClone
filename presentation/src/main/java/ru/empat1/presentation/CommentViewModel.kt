package ru.empat1.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.empat1.presentation.ui.fragment.comments.CommentsScreenState

class CommentsViewModel(
    feedPost: ru.empat1.domain.FeedPost
) : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: ru.empat1.domain.FeedPost) {
        val comments = mutableListOf<ru.empat1.domain.PostComment>().apply {
            repeat(10) {
                add(ru.empat1.domain.PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }
}
