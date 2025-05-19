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

    init {
        fetchNotes()
    }
    private fun fetchNotes()
    {
        ref.orderBy("timestamp",
            Query.Direction.DESCENDING

        )
            .addSnapshotListener { snapshot, _ ->
                if (
                    snapshot != null &&
                    ! snapshot.isEmpty
                )
                {
                    val list = snapshot.documents.map {
                        it.toObject( Note::class.java)!!.copy( id = it.id)
                    }
                    _note.value = list
                }
            }
    }
    fun addNote( note : Note)
    {
        ref.add(note)
    }
    fun updateNote( note : Note)
    {
        ref.document(note.id).set(note)
    }
    fun deleteNote( id : String )
    {
       ref.document(id).delete()
    }
    fun getNoteById(
        id: String,
        onResult : (Note?) -> Unit
    )
    {
        ref.document(id).get()
            .addOnSuccessListener {
                val note = it.toObject(Note::class.java)
                    ?.copy( id = it.id)
            }
    }
}