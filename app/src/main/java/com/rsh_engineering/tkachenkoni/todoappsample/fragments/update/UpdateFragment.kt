package com.rsh_engineering.tkachenkoni.todoappsample.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rsh_engineering.tkachenkoni.todoappsample.R
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo
import com.rsh_engineering.tkachenkoni.todoappsample.data.models.Priority
import com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel.RecordToDoViewModel
import com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

/**
 * Created by Nikolay Tkachenko on 23, September, 2020
 *
 **/


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val sharedViewModel: SharedViewModel by viewModels()
    private  val recordToDoViewModel: RecordToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        setHasOptionsMenu(true)

        view.current_title_et.setText(args.currentItem.title)
        view.current_description_et.setText(args.currentItem.description)
        view.current_priority_spinner.setSelection(sharedViewModel.parsePriorityToInt(args.currentItem.priority))
        view.current_priority_spinner.onItemSelectedListener = sharedViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> itemRemove()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun itemRemove() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            recordToDoViewModel.deletedRecord(args.currentItem)
            Toast.makeText(requireContext(),"Item was removed: ${args.currentItem.title}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Remove record: ${args.currentItem.title}")
        builder.setMessage("Do you want to remove ${args.currentItem.title} ?")
        builder.create().show()
    }

    private fun updateItem() {
        val title = current_title_et.text.toString()
        val descr = current_description_et.text.toString()
        val prior = current_priority_spinner.selectedItem.toString()

        val validation = sharedViewModel.verifyDataFromUser(title,descr)
        if(validation){
            val updateItem = RecordToDo(
                args.currentItem.id,
                title,
                sharedViewModel.parsePriority(prior),
                descr
            )
            recordToDoViewModel.updateRecord(updateItem)
            Toast.makeText(requireContext(), "Record was updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "You need fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }


}