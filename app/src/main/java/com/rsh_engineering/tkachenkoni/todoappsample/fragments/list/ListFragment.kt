package com.rsh_engineering.tkachenkoni.todoappsample.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsh_engineering.tkachenkoni.todoappsample.R
import com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel.RecordToDoViewModel
import com.rsh_engineering.tkachenkoni.todoappsample.data.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * Created by Nikolay Tkachenko on 23, September, 2020
 *
 **/

class ListFragment : Fragment() {

    private val recordToDoViewModel: RecordToDoViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListAdapter by lazy {
        ListAdapter()
    }

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
        var view = inflater.inflate(R.layout.fragment_list, container, false)

        val rcView = view.recycler
        rcView.adapter = adapter
        rcView.layoutManager = LinearLayoutManager(requireActivity())
        recordToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            records ->
            sharedViewModel.checkIfDatabaseHasNoRecord(records)
            adapter.setData(records)
        })

        sharedViewModel.noRecordsInDatabase.observe(viewLifecycleOwner, Observer {
            showNoRecordsDatabase(it)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        //Set Menu
        setHasOptionsMenu(true)


        return view
    }

    private fun showNoRecordsDatabase(noData: Boolean) {
        if(noData){
            view?.no_data_imageView?.visibility = View.VISIBLE
            view?.no_data_textView?.visibility = View.VISIBLE
        }else{
            view?.no_data_imageView?.visibility = View.INVISIBLE
            view?.no_data_textView?.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_delete_all -> itemsAllRemove()
            R.id.menu_search ->{}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun itemsAllRemove() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            recordToDoViewModel.deletedAllRecords()
            Toast.makeText(requireContext(),"All items were removed ", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Remove all records")
        builder.setMessage("Do you want to remove all records ?")
        builder.create().show()
    }
}