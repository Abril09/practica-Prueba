package com.curso.practicapruebafinal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.curso.practicapruebafinal.model.local.PokemonEntity
import com.curso.practicapruebafinal.viewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment() {

    var id: String = ""
    lateinit var mViewModel: PokemonViewModel
    lateinit var pokemon: PokemonEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        arguments.let {
            id = arguments?.getString("id") ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("VIEW", id)

        id?.let {
            mViewModel.obtainPokemonByID(it).observe(viewLifecycleOwner, Observer {
                pokemon = it
                context?.let { it1 -> Glide.with(it1).load(it.image).into(imageView2) }
                tv_id.text = "#" + it.id
                tv_name.text = it.name
                tv_types.text = "Tipo: " + it.types
                tv_types2.text = "Habilidades: " + it.abilities
                ratingBar.rating= pokemon.rank.toFloat()
                Log.d("poke",it.toString())

            })
        }

        ratingBar.setOnRatingBarChangeListener { x, stars, iss ->
            pokemon.rank= stars.toDouble()
            mViewModel.updatePokemon(pokemon)
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }


}