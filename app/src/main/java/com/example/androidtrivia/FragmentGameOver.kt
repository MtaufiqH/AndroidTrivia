package com.example.androidtrivia
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.androidtrivia.R
import com.example.androidtrivia.databinding.FragmentGameoverBinding

class FragmentGameOver : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentGameoverBinding>(inflater,
            R.layout.fragment_gameover,container,false)


        binding.apply {
            tryAgainButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_fragmentGame_to_fragmentGameOver)
            }
        }

        return binding.root
    }
}