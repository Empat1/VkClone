package ru.empat1.presentation.ui.fragment.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.empat1.presentation.CommentsViewModel
import ru.empat1.presentation.CommentsViewModelFactory
import androidx.compose.foundation.lazy.items
import ru.empat1.domain.PostComment

@Composable
fun CommentsScreen(
    post : ru.empat1.domain.FeedPost,
    paddingValues : PaddingValues,
    onBackPressed: () -> Unit,
) {
    val viewModel : CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(post)
    )

    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentState = screenState.value

    Text(
        modifier = Modifier.padding(8.dp),
        text = post.contentText
    )
    if(currentState is CommentsScreenState.Comments) {

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(currentState.comments, key = { it.id }) {
                Card {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        CommentIem(comment = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun CommentIem(
    comment: ru.empat1.domain.PostComment
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                4.dp
            )
    ){
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = comment.authorAvatarId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "${comment.authorName} CommentId: ${comment.id}",
                color = Color.Black,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = comment.commentText,
                color = Color.Black,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.publicationDate,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
private fun PreviewComment(){
    CommentIem(comment = PostComment(id = 0))
}