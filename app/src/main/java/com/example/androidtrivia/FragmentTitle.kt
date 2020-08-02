package com.example.androidtrivia

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidtrivia.databinding.FragmentTitleBinding


class FragmentTitle : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(
            inflater,
            R.layout.fragment_title, container, false
        )



        binding.apply {
            btnPlay.setOnClickListener {
                it.findNavController().navigate(R.id.fragmentTitle_to_fragmentGame)
            }
        }


        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
