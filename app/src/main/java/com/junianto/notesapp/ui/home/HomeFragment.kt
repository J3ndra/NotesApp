package com.junianto.notesapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.junianto.notesapp.MainActivity
import com.junianto.notesapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(R.layout.fragment_home) {

    lateinit var navController: NavController
    lateinit var viewModel: HomeViewModel
    lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).homeViewModel
        navController = Navigation.findNavController(view)

        setRecyclerView()

        viewModel.getAllNotes().observe(requireActivity(), Observer { Notes ->
            adapter.differ.submitList(Notes)
            adapter.notifyDataSetChanged()
        })

        addNote.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_createFragment)
        }

    }


    private fun setRecyclerView() {
        adapter = NotesAdapter()
        listNote.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listNote.adapter = adapter
    }
}