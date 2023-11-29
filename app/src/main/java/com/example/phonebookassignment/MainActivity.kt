package com.example.phonebookassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), AddUserFragment.AddUserListener, ListPhoneBookFragment.NavigateToAddUserListener {

    companion object{
        const val LIST_SIZE = "LIST_SIZE"
    }

    private lateinit var listPhoneBookFragment: ListPhoneBookFragment
    private lateinit var addUserFragment: AddUserFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPhoneBookFragment = ListPhoneBookFragment()
        listPhoneBookFragment.setNavigateToAddUserListener(this)

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, listPhoneBookFragment)
        fragmentTransaction.commit()
    }

    override fun addUser(user: User) {
        listPhoneBookFragment.addUser(user)
    }

    override fun navigateToAddUser() {

        val bundle = Bundle()
        bundle.putInt(LIST_SIZE, listPhoneBookFragment.getListSize())

        addUserFragment = AddUserFragment()
        addUserFragment.arguments = bundle
        addUserFragment.setAddUserListener(this)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, addUserFragment)
            .addToBackStack("AddUserFragment").commit()
    }

}