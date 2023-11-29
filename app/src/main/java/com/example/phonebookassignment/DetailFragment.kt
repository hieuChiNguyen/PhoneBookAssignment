package com.example.phonebookassignment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment

class DetailFragment: Fragment() {

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        user = bundle?.getParcelable(ListPhoneBookFragment.DETAIL_FRAGMENT_CODE)
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<EditText>(R.id.edtNameDetail).setText(user?.name ?: "")
        view.findViewById<EditText>(R.id.edtEmailDetail).setText(user?.email ?: "")
        view.findViewById<EditText>(R.id.edtPhoneDetail).setText(user?.phone ?: "")
    }
}