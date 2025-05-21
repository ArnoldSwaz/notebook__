package com.swaz.notebook.data

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.swaz.notebook.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteView : ViewModel(){
    private val db = FirebaseFirestore.getInstance()
    private val ref = db.collection("notes")
    private val _note = MutableStateFlow<List<Note>>(
        emptyList()
    )
    val notes : StateFlow<List<Note>> = _note


    fun fetchNotes(
        userId : String
    )
    {
        ref.orderBy("timestamp",
            Query.Direction.DESCENDING

        )
            db.collection("users")
                .document(userId)
                .collection("notes")
                .addSnapshotListener { snapshot, error ->
                if (
                    error != null
                )
                {
                   return@addSnapshotListener
                }
                    val list = snapshot?.documents?.mapNotNull { doc ->
                        doc.toObject(Note::class.java)!!.copy(id = doc.id)
                    }
                        ?: emptyList()
                    _note.value = list
            }
    }
    fun addNote(
        userId : String,
        title : String,
        content : String,
        onComplete : () -> Unit = {}
    )
    {
        val note = Note(
            title = title,
            content = content,
            timestamp = System.currentTimeMillis()
        )
        db.collection("users")
            .document(userId)
            .collection("notes")
            .add(note)
            .addOnSuccessListener {
                onComplete()
            }
            .addOnFailureListener {
                onComplete()
            }

    }
    fun updateNote(
        userId : String,
        noteId : String,
        title : String,
        content : String,
        onComplete : () -> Unit = {}

    )
    {
        val uptNote = mapOf(
            "title" to  title,
            "content" to  content,
            "timestamp" to System.currentTimeMillis()

        )
        db.collection("users")
            .document(userId)
            .collection("notes")
            .document(noteId)
            .update(uptNote)
            .addOnSuccessListener {
                onComplete()
            }
            .addOnFailureListener {
                onComplete()
            }
    }
    fun deleteNote(
        userId : String,
        noteId : String,
    )
    {
        db.collection("users")
            .document(userId)
            .collection("notes")
            .document(noteId)
            .delete()
    }
    fun getNoteById(
        noteId: String,
    )
    :Note?
    {
        return _note.value.find { it.id == noteId }

    }
}