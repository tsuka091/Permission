package com.example.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import permissions.dispatcher.*

// 参考： https://qiita.com/offwhite/items/d2371e477b307268f97e
// 1.RuntimePermissionsアノテーションつける
//   これだけやった状態でビルドしようとするとエラーになる
//   A failure occurred while executing org.jetbrains.kotlin.gradle.internal.kaptexecution
@RuntimePermissions
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 5.2で作った関数名+WithPermissionCheckというメソッドが生成されているので呼ぶ
        nextExecutionWithPermissionCheck()
    }

    // 2.許可出た or 失敗したときに呼ばれるメソッドを作る
    // 3.NeedsPermissionアノテーションをつける
    //   複数の権限を取得したいときは（A, B）の形で書く
    // 4.ビルド -> 自動生成コードができる
    @NeedsPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
    fun nextExecution() {
        Toast.makeText(applicationContext, "needs", Toast.LENGTH_SHORT).show()
    }

    // 6.onRequestPermissionsResultをオーバーライド
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 7.自動生成されたonRequestPermissionsResultメソッドを呼ぶ
        onRequestPermissionsResult(requestCode, grantResults)
    }
}