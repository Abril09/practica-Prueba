package com.curso.practicapruebafinal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.curso.practicapruebafinal.model.local.PokemonEntity
import com.curso.practicapruebafinal.viewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),PokemonAdapter.CallbackInterface {

    //1- declaro variable del viewmodel
    lateinit var adapter: PokemonAdapter
    lateinit var mViewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        adapter =PokemonAdapter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = recyclerView
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = adapter

        mViewModel.exposeLiveDataFromDatabase().observe(viewLifecycleOwner, Observer {
            Log.d("VIEW", it.toString())
            adapter.updateAdapter(it)
        })
        //    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun passTheData(pokemon: PokemonEntity) {
        val bundle = Bundle()
        bundle.putString("id",pokemon.id)
       findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}