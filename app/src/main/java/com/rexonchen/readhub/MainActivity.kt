package com.rexonchen.readhub

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.bihe0832.base.permission.PermissionsActivity
import com.bihe0832.base.permission.PermissionsChecker
import com.rexonchen.readhub.view.NewsListFragment
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by rexonchen on 2018/3/20.
 */
class MainActivity:SupportActivity(){
    private val REQUEST_CODE = 0
    val PERMISSIONS = arrayOf<String>(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var mPermissionsChecker: PermissionsChecker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container,NewsListFragment(),NewsListFragment.TAG).commit()
        }
    }
    override fun onResume() {
        super.onResume()
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker!!.lacksPermissions(permissions = *PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, permissions = *PERMISSIONS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish()
        }
    }
}