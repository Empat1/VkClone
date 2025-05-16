package ru.empat1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class CommentsViewModelFactory(
    private val feedPost: ru.empat1.domain.FeedPost
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return CommentsViewModel(feedPost) as T
    }
}