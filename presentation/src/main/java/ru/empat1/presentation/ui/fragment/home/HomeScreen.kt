package ru.empat1.presentation.ui.fragment.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.empat1.presentation.ui.fragment.news.NewsFeedViewModel
import ru.empat1.presentation.ui.fragment.utils.VkTopAppBar
import ru.empat1.presentation.ui.theme.PostCard
import ru.empat1.domain.FeedPost
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit,
) {

    val viewModel: NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        NewsFeedScreenState.Initial -> {}

        is NewsFeedScreenState.Posts -> VkNewsFeedScreen(
            viewModel = viewModel,
            paddingValues = paddingValues,
            posts = currentState.posts,
            onCommentClickListener = onCommentClickListener
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun VkNewsFeedScreen(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues),
        topBar = {
            VkTopAppBar("", Icons.Filled.Menu, {})
        },
    ) { paddingValues ->
        LazyColumnFeedPosts(
            posts,
            viewModel,
            paddingValues,
            onCommentClickListener = { feedPost ->
                onCommentClickListener(feedPost)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun LazyColumnFeedPosts(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit
) {
    val lazyStateList = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues),
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        state = lazyStateList,
    ) {

        items(
            items = posts,
            key = { it.id }
        ) { feedPost ->

            val dismissThresholds = with(receiver = LocalDensity.current) {
                LocalConfiguration.current.screenWidthDp.dp.toPx() * 0.30f
            }

            val state = rememberSwipeToDismissBoxState(
                positionalThreshold = { dismissThresholds },
                confirmValueChange = { value ->
                    val isDismissed = value == SwipeToDismissBoxValue.EndToStart
                    if (isDismissed) viewModel.removePost(feedPost)
                    true
                }
            )

            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = state,
                enableDismissFromEndToStart = true,
                enableDismissFromStartToEnd = false,
                backgroundContent = {},
            ) {
                PostCard(
                    feedPost,
                    onLikeClickListener = {
                        viewModel.updateCount(feedPost, it)
                    },
                    onShareClickListener = {
                        viewModel.updateCount(feedPost, it)
                    },
                    onViewClickListener = {
                        viewModel.updateCount(feedPost, it)
                    },
                    onCommentClickListener = {
                        onCommentClickListener(feedPost)
                    },
                )
            }
        }
    }
}