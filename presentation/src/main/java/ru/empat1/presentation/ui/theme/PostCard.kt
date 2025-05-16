package ru.empat1.presentation.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.empat1.presentation.R

@Composable
fun PostCard(
    feedPost: ru.empat1.domain.FeedPost,
    onViewClickListener: (ru.empat1.domain.StatisticItem) -> Unit,
    onShareClickListener: (ru.empat1.domain.StatisticItem) -> Unit,
    onCommentClickListener: (ru.empat1.domain.StatisticItem) -> Unit,
    onLikeClickListener: (ru.empat1.domain.StatisticItem) -> Unit
) {
    Card {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = feedPost.communityName)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(feedPost.contentImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                statistics = feedPost.statistics,
                onViewClickListener = onViewClickListener,
                onShareClickListener = onShareClickListener,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = onLikeClickListener
            )
        }
    }
}

@Composable
private fun PostHeader(
    feedPost: ru.empat1.domain.FeedPost
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(feedPost.avatarResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "/dev/null",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "14:00",
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<ru.empat1.domain.StatisticItem>,
    onViewClickListener: (ru.empat1.domain.StatisticItem) -> Unit,
    onShareClickListener: (ru.empat1.domain.StatisticItem) -> Unit,
    onCommentClickListener: (ru.empat1.domain.StatisticItem) -> Unit,
    onLikeClickListener: (ru.empat1.domain.StatisticItem) -> Unit
) {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            val viewsItem = statistics.getItemByType(ru.empat1.domain.StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_view_count,
                text = viewsItem.count.toString(),
                clickListener = {
                    onViewClickListener.invoke(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val shareItem = statistics.getItemByType(ru.empat1.domain.StatisticType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = shareItem.count.toString(),
                clickListener = {
                    onShareClickListener.invoke(shareItem)
                }
            )
            val commentItem = statistics.getItemByType(ru.empat1.domain.StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentItem.count.toString(),
                clickListener = {
                    onCommentClickListener.invoke(commentItem)
                }
            )
            val likeItem = statistics.getItemByType(ru.empat1.domain.StatisticType.LIKES)
            IconWithText(
                iconResId = R.drawable.ic_like,
                text = likeItem.count.toString(),
                clickListener = {
                    onLikeClickListener.invoke(likeItem)
                }
            )
        }
    }
}

private fun List<ru.empat1.domain.StatisticItem>.getItemByType(type: ru.empat1.domain.StatisticType): ru.empat1.domain.StatisticItem {
    return this.find { it.type == type } ?: throw RuntimeException("aaaa")
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    clickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            clickListener.invoke()
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}