package com.example.myfamily

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfamily.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*


class HomeFragment : Fragment() {

    lateinit var inviteAdapter: InviteAdapter

    private val listContacts: ArrayList<ContactModel> = ArrayList()

    //View binding declare 26 to replace findbyview
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        //view binding code 34 36
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listMembers = listOf<MemberModel>(
            MemberModel(
                "Deepak",
                "9th_buildind_2nd_floor_maldiv_road_manali_9th_buildind_2nd_floor_9th_buildind_2nd_floor",
                "50%",
                "190",
                R.drawable.ic_boy
            ),
            MemberModel(
                "parv",
                "10th_buildind_2nd_floor_maldiv_road_manali_9th_buildind_2nd_floor_9th_buildind_2nd_floor",
                "70%",
                "211",
                R.drawable.ic_girl

            ),
            MemberModel(
                "Sidhi",
                "12th_buildind_2nd_floor_maldiv_road_manali_9th_buildind_2nd_floor_9th_buildind_2nd_floor",
                "40%",
                "120",
                R.drawable.ic_girls

            ),
            MemberModel(
                "Kishu",
                "13th_buildind_2nd_floor_maldiv_road_manali_9th_buildind_2nd_floor_9th_buildind_2nd_floor",
                "60%",
                "230",
                R.drawable.ic_yngmen


            ),
            MemberModel(
                "Kanu",
                "14th_buildind_4nd_floor_maldiv_road_manali_9th_buildind_2nd_floor_9th_buildind_2nd_floor",
                "80%",
                "220",
                R.drawable.ic_boy
            ),
        )
        //item member call
        val adapter = MemberAdapter(listMembers)

        binding.recycleMember.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleMember.adapter = adapter




        Log.d("Fatch89", "onViewCreated: Start hone wala hai")

        Log.d("Fatch89", "onViewCreated: Start hogya hai ${listContacts.size}")

        inviteAdapter = InviteAdapter(listContacts)
        facthDataBaseContacts()

        Log.d("Fatch89", "onViewCreated: End hone wala hai")


        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Fatch89", "onViewCreated: coroutines start hone wala hai")


            insertDataBaseContacts(facthContacts())

            Log.d("Fatch89", "onViewCreated: corotines End hone wala hai ${listContacts.size}")
        }



        binding.recyclerInvite.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerInvite.adapter = inviteAdapter


           //signout
        binding.iconThreeDots.setOnClickListener {

            SharedPrf.putBoolean(PrefContants.Is_User_Logged_In, false)

            FirebaseAuth.getInstance().signOut()
        }

    }

    private fun facthDataBaseContacts() {
        val database = MyFamilyDataBase.getDatabase(requireContext())
        database.contactDao().getAllContacts().observe(viewLifecycleOwner) {

            Log.d("Fatch89", "facthDataBaseContacts: ")

            listContacts.clear()
            listContacts.addAll(it)

            inviteAdapter.notifyDataSetChanged()
        }
    }


    private suspend fun insertDataBaseContacts(listContacts: ArrayList<ContactModel>) {
        val database = MyFamilyDataBase.getDatabase(requireContext())
        database.contactDao().insertAll(listContacts)
    }

    private fun facthContacts(): ArrayList<ContactModel> {
        Log.d("Fatch89", "facthContacts: start")
        val cr = requireActivity().contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        val listContacts: ArrayList<ContactModel> = ArrayList()

        if (cursor != null && cursor.count > 0) {
            while (cursor != null && cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))


                val hasPhoneNumber =
                    cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                if (hasPhoneNumber > 0) {
                    val pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                        arrayOf(id),
                        ""
                    )
                    if (pCur != null && pCur.count > 0) {
                        while (pCur != null && pCur.moveToNext()) {

                            val phoneNum =
                                pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            listContacts.add(ContactModel(name, phoneNum))

                        }
                        pCur.close()


                    }

                }

            }

            if (cursor != null) {
                cursor.close()
            }

        }
        Log.d("Fatch89", "facthContacts: End")
        return listContacts

    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

