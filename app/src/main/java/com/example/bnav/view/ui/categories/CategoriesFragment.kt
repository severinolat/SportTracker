package com.example.bnav.view.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bnav.databinding.FragmentCategoriesBinding
import com.example.bnav.view.adapter.CategoriesAdapter
import com.example.bnav.viewmodel.SportCategoriesViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  var _binding:FragmentCategoriesBinding ?=null
    lateinit var categoriesAdapter: CategoriesAdapter

    private val binding get() = _binding!!






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerView()
        createData()
        return view
    }


    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
            categoriesAdapter = CategoriesAdapter()
            adapter = categoriesAdapter


        }
    }



    fun createData() {

        val viewModel = ViewModelProvider(this).get(SportCategoriesViewModel::class.java)
        viewModel.makeApiCall()

        viewModel.recyclerListData?.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                binding.progressBar.visibility = View.INVISIBLE
                it.sports?.let { it1 -> categoriesAdapter.setListData(it1) }
            }else{
                Toast.makeText(context, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}