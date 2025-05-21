package com.swaz.notebook.data

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthView : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val currentUser : FirebaseUser?get() = auth.currentUser

    fun login(
        email : String,
        password : String,
        onResult : (Boolean) -> Unit

    )
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                onResult(task.isSuccessful)
            }
    }
    fun register(
        email : String,
        password : String,
        onResult : (Boolean) -> Unit
    )
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                onResult(task.isSuccessful)
            }
    }
    fun logout(){
        auth.signOut()
    }
}