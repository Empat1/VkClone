package ru.empat1.domain

data class FeedPosts(
    var feedPost: List<FeedPost> = listOf(
        FeedPost(id = 1),
        FeedPost(id = 2),
        FeedPost(id = 3)
    )
)