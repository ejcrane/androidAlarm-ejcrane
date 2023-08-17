package edu.uchicago.ejcrane.androidalarm.presentation.screens.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.ejcrane.androidalarm.R
import edu.uchicago.ejcrane.androidalarm.common.Constants
import edu.uchicago.ejcrane.androidalarm.presentation.viewmodels.YoutubeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    youtubeViewModel: YoutubeViewModel,
    navController: NavController
) {

    //observe the vid
    val vid = youtubeViewModel.vid.value
    val activity = (LocalContext.current as? Activity)


    Scaffold(
        modifier = Constants.modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                ) ,
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier
                                .clickable {
                                    navController.popBackStack()
                                }
                                .align(Alignment.CenterVertically)
                                .padding(20.dp, 0.dp, 0.dp, 0.dp))

                        Text(
                            text = "Details",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp
                        )

                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {

                            Icon(imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                modifier = Modifier
                                    .clickable {
                                        val sendIntent = Intent(Intent.ACTION_SEND)
                                        sendIntent.type = "text/plain"
                                        sendIntent.putExtra(
                                            Intent.EXTRA_TEXT,
                                            "You must check out this video!: " +
                                                    "https://youtube.com/watch?v=${vid.id.videoId}"
                                        )
                                        activity?.startActivity(sendIntent)
                                    }
                                    .align(Alignment.CenterVertically)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp))
                        }

                    }

                })
        }) {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues = paddingValues)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState(0))
                .padding(20.dp, 0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {


                Divider()
                Spacer(Modifier.height(20.dp))
                with(vid.snippet){
                    title?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Start,
                            fontSize = 22.sp
                        )
                    }
                    channelTitle?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp
                        )
                    }

                    description?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp
                        )
                    }


                }

                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        Toast.makeText(activity, "Add to Favorites Button Pressed", Toast.LENGTH_LONG).show()
                    },

                    colors =
                    ButtonDefaults.buttonColors(containerColor = Color.Green)

                ) {
                    Text(text = "Add to Favorites")
                }

            }
        }
    }

}