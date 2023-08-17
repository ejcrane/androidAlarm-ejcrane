package edu.uchicago.ejcrane.androidalarm.presentation.screens.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.ejcrane.androidalarm.common.Constants
import edu.uchicago.ejcrane.androidalarm.presentation.viewmodels.ContactViewModel
import edu.uchicago.ejcrane.androidalarm.presentation.widgets.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    contactViewModel: ContactViewModel,
    navController: NavController
) {
    Scaffold(
        modifier = Constants.modifier,
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Contact",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = Color.White)
                .wrapContentSize(Alignment.TopCenter)
        ) {
            val modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .wrapContentSize(Alignment.TopCenter)
                .padding(20.dp)

            Column (modifier = modifier) {
                Text(text = "Having issues or want to let us know how we're doing? " +
                        "Feel free to send us an email!",
                    textAlign = TextAlign.Justify)

                OutlinedTextField(value = contactViewModel.email.value,
                    onValueChange = { contactViewModel.setEmail(it) },
                    label = { Text(text = "Email") })

                OutlinedTextField(value = contactViewModel.subject.value,
                    onValueChange = { contactViewModel.setSubject(it) },
                    label = { Text(text = "Subject") })

                OutlinedTextField(value = contactViewModel.body.value,
                    onValueChange = { contactViewModel.setBody(it) },
                    label = { Text(text = "Body") })

                Button(onClick = { contactViewModel.onSend() },
                    modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                    Text(text = "Submit")
                }
            }


        }
    }

}