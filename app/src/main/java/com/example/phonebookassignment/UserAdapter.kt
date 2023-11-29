package com.example.phonebookassignment

import android.view.*
import android.view.View.OnCreateContextMenuListener
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var onClickItem: ((user: User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnCreateContextMenuListener {
        private val logo: TextView = itemView.findViewById(R.id.tvLogoUser)
        private val name: TextView = itemView.findViewById(R.id.tvNameUser)
        private val container: LinearLayout = itemView.findViewById(R.id.llContainerUser)
        private lateinit var currentUser: User

        init {
            container.setOnClickListener{
                onClickItem?.invoke(currentUser)
            }
            itemView.setOnCreateContextMenuListener(this);
        }

        fun bind(user: User) {
            currentUser = user
            if (!user.name.isNullOrEmpty()) logo.text = user.name[0].toString()
            name.text = user.name
        }

        override fun onCreateContextMenu(
            menu: ContextMenu,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            val call = menu.add(Menu.NONE, 1, 1, "Call")
            val sms = menu.add(Menu.NONE, 2, 2, "SMS")
            val email = menu.add(Menu.NONE, 3, 3, "Email")
            call.setOnMenuItemClickListener(onEditMenu)
            sms.setOnMenuItemClickListener(onEditMenu)
            email.setOnMenuItemClickListener(onEditMenu)
        }

        private val onEditMenu: MenuItem.OnMenuItemClickListener =
            MenuItem.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> {
                        Toast.makeText(itemView.context, "Call ${currentUser.name}", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(itemView.context, "SMS ${currentUser.name}", Toast.LENGTH_SHORT).show()
                    }
                    3 ->{
                        Toast.makeText(itemView.context, "Email ${currentUser.name}", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
    }
}