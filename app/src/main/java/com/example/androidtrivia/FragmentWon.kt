package com.example.androidtrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.androidtrivia.databinding.FragmentWonBinding

class FragmentWon : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentWonBinding>(
            inflater,
            R.layout.fragment_won, container, false
        )


        binding.nextMatchButton.setOnClickListener {
            it.findNavController().navigate(FragmentWonDirections.actionGameWonFragmentToFragmentGame())
        }


        val args = FragmentWonArgs.fromBundle(arguments!!)
        Toast.makeText(context,
            "Num correct ${args.numCorrect} num question ${args.numQuestions}",
            Toast.LENGTH_SHORT).show()

        return binding.root

    }
}