package com.example.finalpracticeproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalpracticeproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // FragmentViewBinding 초기화
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 버튼 클릭 시, 이름 변경
        binding.nameBtn.setOnClickListener {
            val inputName = binding.nameInput.text.toString()
            viewModel.setName(inputName)
            binding.mainName.text = viewModel.name.value
            findNavController().navigate(R.id.action_mainFragment_to_recordFragment)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

