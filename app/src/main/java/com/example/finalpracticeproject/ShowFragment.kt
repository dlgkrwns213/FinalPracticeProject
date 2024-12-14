package com.example.finalpracticeproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.finalpracticeproject.databinding.FragmentShowBinding

class ShowFragment : Fragment() {
    private val viewModel: ShowViewModel by viewModels()
    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShow.setOnClickListener {
            viewModel.setName(binding.nameInput.text.toString() ?: "")

            viewModel.showList().observe(viewLifecycleOwner, {dataList ->
                val dataListString = dataList.map {
                    if (it.money >= 0)
                        return@map "입금: ${it.money}원 시간: ${it.date} 메모: ${it.memo}"
                    else
                        return@map "출금: ${-it.money}원 시간: ${it.date} 메모: ${it.memo}"
                }
                val adapter =
                    ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, dataListString)
                binding.showList.adapter = adapter
            })
        }
    }


}