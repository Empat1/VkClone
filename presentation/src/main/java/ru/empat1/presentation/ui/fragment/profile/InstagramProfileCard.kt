package ru.empat1.presentation.ui.fragment.profile

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.empat1.presentation.R
import ru.empat1.presentation.ui.fragment.main.MainViewModel

@Composable
fun InstagramProfileCard(
    viewModel: MainViewModel
) {
    val isFollowed = viewModel.isFollowing.observeAsState(false)

    Log.d("RECOMPOSITION", "InstagramProfileCard")

    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(
            topStart = 4.dp,
            topEnd = 4.dp
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.background)
    ) {
        Log.d("RECOMPOSITION", "Card")
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp),
                )
                UserStatistics(title = "Posts", value = "6,950")
                UserStatistics(title = "Followers", value = "436M")
                UserStatistics(title = "Following", value = "76")
            }
            Text(
                text = "Instagram",
                fontSize = 32.sp,
                fontFamily = FontFamily.Cursive
            )
            Text(
                text = "#YoursToMake",
                fontSize = 14.sp
            )
            Text(
                text = "www.facebook.com/emotional_health",
                fontSize = 14.sp
            )
            FollowButton(isFollowed = isFollowed) {
                viewModel.changeFollowing()
            }
        }
    }
}

@Composable
private fun FollowButton(
    isFollowed: State<Boolean>,
    onClickListener: () -> Unit
) {
    Log.d("RECOMPOSITION", "FollowButton")

    Button(
        onClick = { onClickListener.invoke() },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed.value) {
                Color.Red
            } else {
                Color.Red.copy(alpha = 0.5f)
            }
        )
    ) {
        val text = if (isFollowed.value) {
            "Unfollow"
        } else {
            "Follow"
        }
        Text(text)
    }
}


@Composable
private fun UserStatistics(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier.height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
    }
}