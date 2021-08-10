package com.learn.tindertemplate.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.learn.tindertemplate.R
import com.learn.tindertemplate.data.model.ResultsItem
import com.learn.tindertemplate.databinding.ProfileViewBinding

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    private var userList: List<ResultsItem>? = null

    inner class ProfileViewHolder(val binding: ProfileViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.profile_view,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        userList?.let {
            holder.binding.user = it[position].user
        }
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    fun setUserList(list: List<ResultsItem>?) {
        this.userList = list
        notifyDataSetChanged()
    }
}