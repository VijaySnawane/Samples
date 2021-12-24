package com.example.jetpack_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_prac.ui.theme.MyPracApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPracApplicationTheme {
                Conversation(conversationSample)
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    MyPracApplicationTheme {
        Conversation(conversationSample)
    }
}

val conversationSample = listOf(
    Message(
        "Colleague",
        "Test...Test...Test..."
    ),
    Message(
        "Colleague",
        """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trimMargin()
    ),
    Message(
        "Colleague",
        """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trimMargin()
    ),
    Message(
        "Colleague",
        "Searching for alternatives to XML layouts..."
    ),
    Message(
        "Colleague",
        """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trimMargin()
    ),
    Message(
        "Colleague",
        "It's available from API 21+ :)"
    ),
    Message(
        "Colleague",
        "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
    ),
    Message(
        "Colleague",
        "Android Studio next version's name is Arctic Fox"
    ),
    Message(
        "Colleague",
        "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
    ),
    Message(
        "Colleague",
        "I didn't know you can now run the emulator directly from Android Studio"
    ),
    Message(
        "Colleague",
        "Compose Previews are great to check quickly how a composable layout looks like"
    ),
    Message(
        "Colleague",
        "Previews are also interactive after enabling the experimental setting"
    ),
    Message(
        "Colleague",
        "Have you tried writing build.gradle with KTS?"
    ),
)

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "contact photo",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(6.dp, MaterialTheme.colors.secondary)
        )
        Spacer(modifier = Modifier.width(8.dp))


        //====================================================================================
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = message.author)
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
       //===============================================================================================




    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    MyPracApplicationTheme {
        MessageCard(message = Message("Vijay1", "Sonawane1"))
    }
}

