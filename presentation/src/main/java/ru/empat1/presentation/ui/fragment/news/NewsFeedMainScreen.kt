package ru.empat1.presentation.ui.fragment.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.empat1.domain.FeedPost
import ru.empat1.presentation.VkViewModel
import ru.empat1.presentation.navigation.AppNavGraph
import ru.empat1.presentation.navigation.NavigationItem
import ru.empat1.presentation.navigation.rememberNavigationState
import ru.empat1.presentation.ui.fragment.comments.CommentsScreen
import ru.empat1.presentation.ui.fragment.home.HomeScreen

@Composable
fun MainScreen(viewModel: VkViewModel) {
    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<ru.empat1.domain.FeedPost?> = remember {
        mutableStateOf(FeedPost(1))
    }

    Scaffold(
        bottomBar = {
            NavigationBar {

                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, it ->
                    NavigationBarItem(
                        selected = currentRout == it.screen.route,
                        onClick = {
                            navigationState.navigateTo(it.screen.route)
                        },
                        icon = { Icon(it.icon, null) },
                        label = { Text(text = stringResource(id = it.titleResId)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->

        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                HomeScreen(
                    paddingValues = innerPadding,
                    onCommentClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )
            },
            favouriteScreenContent = {
                commentsToPost.value?.let {
                    CommentsScreen(
                        paddingValues = innerPadding,
                        post = it,
                        onBackPressed = {
                            commentsToPost.value = null
                        },
                    )
                }
            },
            profileScreenContent = {
                TextCounter(name = "Profile", innerPadding)
            }
        )
    }
}

@Composable
private fun TextCounter(name: String, paddingValues: PaddingValues) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier
            .clickable { count++ }
            .padding(paddingValues),
        text = "$name Count: $count",
        color = Color.Black
    )
}