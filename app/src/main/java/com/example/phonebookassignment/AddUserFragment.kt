package com.example.phonebookassignment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddUserFragment : Fragment() {

    private lateinit var addUserListener: AddUserListener
    private var lastIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lastIndex = arguments?.getInt(MainActivity.LIST_SIZE) ?: 0
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnCreateAddUser).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.edtNameAddUser).text.toString()
            val phone = view.findViewById<EditText>(R.id.edtPhoneAddUser).text.toString()
            val email = view.findViewById<EditText>(R.id.edtEmailAddUser).text.toString()
            if(name.isEmpty() || phone.isEmpty() || email.isEmpty()){
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
            else{
                val user = User(
                    lastIndex + 1,
                    name,
                    phone,
                    email
                )
                addUserListener.addUser(user)
               parentFragmentManager.popBackStack()
            }
        }
    }

    fun setAddUserListener(addUserListener: AddUserListener){
        this.addUserListener = addUserListener
    }

    interface AddUserListener{
        fun addUser(user: User)
    }
}