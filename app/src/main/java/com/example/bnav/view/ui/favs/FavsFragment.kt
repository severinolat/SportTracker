package com.example.bnav.view.ui.favs

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bnav.R
import com.example.bnav.databinding.FragmentCategoriesBinding
import com.example.bnav.databinding.FragmentFavsBinding
import com.example.bnav.view.adapter.CategoriesAdapter
import com.example.bnav.view.adapter.FavsAdapter
import com.example.bnav.viewmodel.FavsViewModel
import com.example.bnav.viewmodel.SportCategoriesViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  var _binding: FragmentFavsBinding?=null
    lateinit var favsAdapter: FavsAdapter

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
        _binding = FragmentFavsBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerView()
        createData()
        return view
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            favsAdapter = FavsAdapter()
            adapter = favsAdapter


        }
    }



    fun createData() {


        val viewModel = ViewModelProvider(this).get(FavsViewModel::class.java)
        viewModel.makeApiCall()

        viewModel.recyclerListData?.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                binding.progressBar.visibility = View.INVISIBLE
                it.teams?.let { it1 -> favsAdapter.setListData(it1) }
            }else{
                Toast.makeText(context, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}