package com.junianto.notesapp.ui.edit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.junianto.notesapp.MainActivity
import com.junianto.notesapp.R
import com.junianto.notesapp.db.notes.Notes
import com.junianto.notesapp.ui.home.HomeViewModel
import com.junianto.notesapp.utils.getCurrentDate
import com.junianto.notesapp.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment(R.layout.fragment_edit) {
    lateinit var viewModel: HomeViewModel
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).homeViewModel
        navController = Navigation.findNavController(view)

        val args = arguments
        val titleargs: String? = args?.getString("title")
        val descargs: String? = args?.getString("description")
        val idargs: Long? = args?.getLong("id")

        val editTitle: EditText = view.findViewById(R.id.editNoteTitle)
        val editDesc: EditText = view.findViewById(R.id.editNoteDescription)
        val editTime: TextView = view.findViewById(R.id.editTime)

        editTitle.setText(titleargs.toString())
        editDesc.setText(descargs.toString())
        editTime.text = getCurrentDate()

        noteUpdateBtn.setOnClickListener {
            val title = editTitle.text.toString()
            val description = editDesc.text.toString()
            val timeStamp = editTime.text.toString()
            val itemId = idargs
            val item = Notes(
                title,
                description,
                timeStamp
            )

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_LONG).show()
                Log.d("ERROR", "TextField not Filled")
            } else {
                Toast.makeText(context, "Note Updated", Toast.LENGTH_LONG).show()
                Log.d("SUCCESS", "Note Updated, Id = $itemId")
                item.id = itemId!!
                viewModel.update(item)
                navController.navigate(R.id.action_editFragment_to_homeFragment)
                hideSoftKeyboard(requireActivity(), requireView())
            }
        }

        noteCancelBtn.setOnClickListener {
            navController.navigate(R.id.action_editFragment_to_homeFragment)
            hideSoftKeyboard(requireActivity(), requireView())
        }
    }
}