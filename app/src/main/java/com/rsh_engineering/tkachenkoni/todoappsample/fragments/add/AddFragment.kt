package com.rsh_engineering.tkachenkoni.todoappsample.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rsh_engineering.tkachenkoni.todoappsample.R
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo
import com.rsh_engineering.tkachenkoni.todoappsample.data.models.Priority
import com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel.RecordToDoViewModel
import com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

/**
 * Created by Nikolay Tkachenko on 23, September, 2020
 *
 **/

class AddFragment : Fragment() {

    private val recordToDoViewModel: RecordToDoViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        setHasOptionsMenu(true)

        view.priority_spinner.onItemSelectedListener = sharedViewModel.listener

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb(){

        val title = title_et.text.toString()
        val priority = priority_spinner.selectedItem.toString()
        val descr = description_et.text.toString()

        val valid = sharedViewModel.verifyDataFromUser(title , descr)
        if (valid){
            //Insert to Database
            val insertData = RecordToDo(
                0,
                title,
                sharedViewModel.parsePriority(priority),
                descr
            )
            recordToDoViewModel.insertRecord(insertData)
            Toast.makeText(requireContext(), "Insert completed", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Not exists all data for insert", Toast.LENGTH_SHORT).show()
        }

    }


}