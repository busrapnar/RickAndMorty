package com.busrapinar.rickandmorty2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busrapinar.rickandmorty2.R
import com.busrapinar.rickandmorty2.api.models.ResultCharacter
import com.busrapinar.rickandmorty2.databinding.CharacterAdapterRowBinding
import com.busrapinar.rickandmorty2.util.loadUrl

class CharactersAdapter(
    private val list: List<ResultCharacter>,
    private val adapterItemClick : (Int) -> Unit
) :
    RecyclerView.Adapter<CharactersAdapter.CharacterAdapterVH>() {

    class CharacterAdapterVH(val binding: CharacterAdapterRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapterVH {
        val binding = CharacterAdapterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterAdapterVH(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CharacterAdapterVH, position: Int) {
        val listByPosition = list[position]
        holder.binding.apply {
            root.setOnClickListener {
                adapterItemClick.invoke(list[position].id)
            }
            ivIcon.loadUrl(listByPosition.image)
            tvName.text = listByPosition.name
            tvGender.text = listByPosition.gender
            tvStatus.apply {
                when(listByPosition.status) {
                    "Dead" -> {
                        tvStatus.setTextColor(this.context.getColor(R.color.red))
                    }
                    else -> {
                        tvStatus.setTextColor(this.context.getColor(R.color.green))
                    }
                }
                text = listByPosition.status
            }
        }
    }
}