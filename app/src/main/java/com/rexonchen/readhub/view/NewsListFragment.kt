package com.rexonchen.readhub.view

import android.app.Fragment
import android.os.Bundle
import android.view.View
import com.rexonchen.readhub.utils.fragment.BaseBackFragment

/**
 * Created by rexonchen on 2018/3/22.
 */
class NewsListFragment:BaseBackFragment(){
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {}
    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

    }
    companion object {
        val TAG = "NewListViewModel"
    }
}