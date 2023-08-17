package edu.uchicago.ejcrane.androidalarm.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.uchicago.ejcrane.androidalarm.data.models.Item

object Constants {

    val modifier = Modifier.padding(paddingValues = PaddingValues(all = 0.dp))

    val contactEndpoint = "https://r8g9uf6f1m.execute-api.us-east-1.amazonaws.com/Prod/"
    val ytApiKey = "AIzaSyAQvOd_KXjbRssfNNSAZQ_AdMeMDzWnO94"
    val ytBaseUrl = "https://www.googleapis.com/youtube/v3/"

    val fakeVid = Item()
}