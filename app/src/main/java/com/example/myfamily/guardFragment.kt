package com.example.myfamily

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfamily.databinding.FragmentGuardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class guardFragment : Fragment(), InviteMailAdapter.OnActionClick {

    lateinit var binding: FragmentGuardBinding
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGuardBinding.inflate(inflater, container, false)

        binding.btn.setOnClickListener {

            btn()
        }
        return binding.root
    }

    private fun btn() {
        val mail = binding.inviteStatus.text.toString()

        val firestore = Firebase.firestore

        val data = hashMapOf(
            "invite_status" to 0

        )
        val senderMail = FirebaseAuth.getInstance().currentUser?.email.toString()

        firestore.collection("users").document(mail)
            .collection("invites")
            .document(senderMail)  //change name
            .set(data).addOnSuccessListener {

            }.addOnFailureListener {

            }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInvite()
    }

    private fun getInvite() {

        val firestore = Firebase.firestore
        firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.email.toString())
            .collection("invites").get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val list: ArrayList<String> = ArrayList()
                    for (item in it.result) {
                        if (item.get("invite_status") == 0L) {
                            list.add(item.id)
                        }
                    }
                    Log.d("invite89", "getInvite: $list")

                    val adapter = InviteMailAdapter(list,this)
                    binding.recyclerRequest.adapter = adapter

                }
            }
    }

    companion object {

        @JvmStatic
        fun newInstance() = guardFragment()


    }

    override fun onAcceptClick(mail: String) {
        Log.d("invite89", "onAcceptClick: $mail")
        val firestore = Firebase.firestore

        val data = hashMapOf(
            "invite_status" to 1

        )
        val senderMail = FirebaseAuth.getInstance().currentUser?.email.toString()

        firestore.collection("users").document(senderMail)
            .collection("invites")
            .document(mail)  //change name
            .set(data).addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    override fun onDenyClick(mail: String) {
        Log.d("invite89", "onDenyClick: $mail")
        val firestore = Firebase.firestore

        val data = hashMapOf(
            "invite_status" to -1

        )
        val senderMail = FirebaseAuth.getInstance().currentUser?.email.toString()

        firestore.collection("users").document(senderMail)
            .collection("invites")
            .document(mail)  //change name
            .set(data).addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

}