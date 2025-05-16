package ru.empat1.presentation.ui.fragment.home

import ru.empat1.domain.FeedPost
import ru.empat1.domain.PostComment

sealed class HomeScreenState {

    data class Post(val posts: List<FeedPost>) : HomeScreenState()
    data class Comment(val post: FeedPost, val comment: List<PostComment>) : HomeScreenState()
    object Install : HomeScreenState()
}