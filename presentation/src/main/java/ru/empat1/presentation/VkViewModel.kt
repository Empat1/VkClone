package ru.empat1.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.empat1.presentation.ui.fragment.home.HomeScreenState

class VkViewModel : ViewModel() {
    private val sourceList = mutableListOf<ru.empat1.domain.FeedPost>().apply {
        repeat(10) {
            add(ru.empat1.domain.FeedPost(id = it))
        }
    }

    private val sourceListComments = mutableListOf<ru.empat1.domain.PostComment>().apply {
        repeat(10) {
            add(ru.empat1.domain.PostComment(id = it))
        }
    }

    private val initState = HomeScreenState.Post(posts = sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var saveState: HomeScreenState? = initState

    fun showComments(feedPost: ru.empat1.domain.FeedPost){
        saveState = _screenState.value
        _screenState.value = HomeScreenState.Comment(
            post = feedPost,
            comment = sourceListComments
        )
    }

    fun closeComments(){
        saveState?.let {
            _screenState.value = it
        }
    }

    fun updateCount(feedPost: ru.empat1.domain.FeedPost, item: ru.empat1.domain.StatisticItem) {
        val currentState = screenState.value
        if(currentState !is HomeScreenState.Post) return

        val oldPosts = currentState.posts.toMutableList() ?: mutableListOf()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = HomeScreenState.Post(newPosts)
    }

    fun remove(feedPost: ru.empat1.domain.FeedPost) {
        val currentState = screenState.value
        if(currentState !is HomeScreenState.Post) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = HomeScreenState.Post(oldPosts)
    }
}