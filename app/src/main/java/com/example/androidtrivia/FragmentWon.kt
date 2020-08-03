package com.example.androidtrivia

import android.content.Intent
import android.os.Bundle
import android.view.*
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
            it.findNavController()
                .navigate(FragmentWonDirections.actionGameWonFragmentToFragmentGame())
        }


        val args = FragmentWonArgs.fromBundle(arguments!!)
        Toast.makeText(
            context,
            "Num correct ${args.numCorrect} num question ${args.numQuestions}",
            Toast.LENGTH_SHORT
        ).show()

        // set has option menu
        setHasOptionsMenu(true)

        return binding.root

    }


    //Build and call an implicit intent
    private fun getShareIntent(): Intent {
        val args = FragmentWonArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,
                args.numCorrect,
                args.numQuestions))
        }

        return shareIntent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu,menu)

        // check if the activity resolve
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)){
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.winnerShareIcon).isVisible = false
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // start intent to share result
            R.id.winnerShareIcon -> shareSuccess()
        }

        return super.onOptionsItemSelected(item)
    }
}