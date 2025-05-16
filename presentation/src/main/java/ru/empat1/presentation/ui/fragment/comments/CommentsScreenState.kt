package ru.empat1.presentation.ui.fragment.comments

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: ru.empat1.domain.FeedPost,
        val comments: List<ru.empat1.domain.PostComment>
    ) : CommentsScreenState()
}
