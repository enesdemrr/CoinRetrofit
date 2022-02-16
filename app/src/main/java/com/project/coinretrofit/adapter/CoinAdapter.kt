package com.project.coinretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.coinretrofit.databinding.CoinItemBinding
import com.project.coinretrofit.model.CoinModel

class CoinAdapter (val coinList : ArrayList<CoinModel>?) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(){
    class CoinViewHolder(val bind : CoinItemBinding) : RecyclerView.ViewHolder(bind.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val bind = CoinItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CoinViewHolder(bind)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind.apply {
            coinName.text = coinList?.get(position)?.symbol.toString()
            coinValue.text = coinList?.get(position)?.lastPrice.toString()
        }

    }

    override fun getItemCount(): Int {
        return coinList!!.size
    }

    fun updateCoinList(newCoinList : List<CoinModel>){
        coinList?.clear()
        coinList?.addAll(newCoinList)
        notifyDataSetChanged()
    }
}