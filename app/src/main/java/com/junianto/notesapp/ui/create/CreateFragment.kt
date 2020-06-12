package com.junianto.notesapp.ui.create

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.junianto.notesapp.MainActivity
import com.junianto.notesapp.R
import com.junianto.notesapp.db.Notes
import com.junianto.notesapp.ui.home.HomeViewModel
import com.junianto.notesapp.utils.getCurrentDate
import com.junianto.notesapp.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment(R.layout.fragment_create) {
    lateinit var viewModel: HomeViewModel
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).homeViewModel
        navController = Navigation.findNavController(view)

        val addTitle: EditText = view.findViewById(R.id.noteTitle)
        val addDesc: EditText = view.findViewById(R.id.noteDescription)

        noteSaveBtn.setOnClickListener {
            val title = addTitle.text.toString()
            val description = addDesc.text.toString()
            val timeStamp = getCurrentDate()
            val item = Notes(title, description, timeStamp)

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_LONG).show()
                Log.d("ERROR", "TextField not Filled")
            } else {
                Toast.makeText(context, "Note Created", Toast.LENGTH_LONG).show()
                Log.d("SUCCESS", "Note Created")
                viewModel.insert(item)
                navController.navigate(R.id.action_createFragment_to_homeFragment)
                hideSoftKeyboard(requireActivity(), requireView())
            }
        }

        noteCancelBtn.setOnClickListener {
            navController.navigate(R.id.action_createFragment_to_homeFragment)
        }
    }
}