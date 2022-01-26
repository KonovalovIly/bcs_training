package ru.konovalovily.notes.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.fragment.app.Fragment
import ru.konovalovily.notes.databinding.FragmentCustomTextViewBinding

class CustomTextViewFragment : Fragment() {

    private var _binding: FragmentCustomTextViewBinding? = null
    private val binding: FragmentCustomTextViewBinding
        get() = _binding ?: throw RuntimeException("FragmentCustomTextViewBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCustomTextViewBinding.inflate(layoutInflater).also { _binding = it }.root

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAnimation()
    }

    private fun startAnimation(){
        val finalY = binding.htmlText.y
        val startY = finalY - TRANSLATION
        val objectAnimator = ObjectAnimator.ofFloat(
            binding.htmlText,
            "translationY",
            startY,
            finalY
        ).apply {
            duration = DURATION
            interpolator = BounceInterpolator()
        }
        objectAnimator.start()
    }



    companion object {
        private const val DURATION: Long = 1000
        private const val TRANSLATION = 80

        fun newInstance() = CustomTextViewFragment()
    }
}