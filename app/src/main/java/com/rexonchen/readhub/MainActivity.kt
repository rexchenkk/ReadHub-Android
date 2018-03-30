package com.rexonchen.readhub

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.rexonchen.readhub.utils.network.ReadHubApi
import com.rexonchen.readhub.utils.permission.PermissionsActivity
import com.rexonchen.readhub.utils.permission.PermissionsChecker
import com.rexonchen.readhub.view.NewsListFragment

/**
 * Created by rexonchen on 2018/3/20.
 */
class MainActivity: AppCompatActivity(){
    private val REQUEST_CODE = 0
    val PERMISSIONS = arrayOf<String>(Manifest.permission.INTERNET,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var mPermissionsChecker: PermissionsChecker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container,NewsListFragment(),NewsListFragment.TAG).commit()
        }
        ReadHubApi.init(this,false)
        mPermissionsChecker =  PermissionsChecker(this)

    }
//    override fun onResume() {
//        super.onResume()
//        if (mPermissionsChecker!!.lacksPermissions(permissions = *PERMISSIONS)) {
//            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, permissions = *PERMISSIONS)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
//            finish()
//        }
//    }
}