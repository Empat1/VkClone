package ru.empat1.presentation.navigation

import android.net.Uri
import ru.empat1.domain.FeedPost

sealed class Screen(
    val route: String
) {
    object Favorite : Screen(ROUTE_FAVOURITE)
    object Profile : Screen(ROUTE_PROFILE)

    object NewsFeed : Screen(ROUTE_NEWS_FEED)

    // Вложенный экран навигации, который будет представлять Comments и NewsFeed
    object Home : Screen(ROUTE_HOME)

    object Comments : Screen(ROUTE_COMMENTS) {

        private const val ROUTE_COMMENTS_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            return "$ROUTE_COMMENTS_FOR_ARGS/${feedPost.id}"
        }

    }

    companion object {
        const val KEY_FEED_POST = "feed_post"

        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}" // Экран Лента комментариев

        const val ROUTE_HOME = "home" // Экран Лента с постами
    }

}

fun String.encode(): String = Uri.encode(this)