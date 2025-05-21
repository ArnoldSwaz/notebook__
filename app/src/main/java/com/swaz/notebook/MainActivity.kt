package com.swaz.notebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import com.swaz.notebook.data.AuthView
import com.swaz.notebook.data.NoteView
import com.swaz.notebook.nav.AppNav
import com.swaz.notebook.ui.theme.NotebookTheme

class MainActivity : ComponentActivity() {
    private val noteView = NoteView()
    private val authView = AuthView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent{
            AppNav()


        }
    }
}

