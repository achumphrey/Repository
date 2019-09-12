package com.example.roomself

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roomself.database.MainActivityViewModel
import com.example.roomself.database.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        model.showSuccess.observe(this, Observer {

            if (it == true){
                Toast.makeText(this,"Added user successfully",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        })

        tv_pressMe.setOnClickListener {

                model.addUser(User(116,"nameFirst 15","nameLast 15"))
        }

        model.getAllUsers()

        model.showUsers.observe(this, Observer {
            for (i in 1..it.size){
                Log.i("MainActivity",it[i].lastName)
            }
        })

        model.showDbSuccess.observe(this, Observer {
            if (it == true){
                Toast.makeText(this,"got user successfully",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Something went wrong with db",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
