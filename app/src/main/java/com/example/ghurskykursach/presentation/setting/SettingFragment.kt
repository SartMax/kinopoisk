package com.example.ghurskykursach.presentation.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.ghurskykursach.AuthActivity
import com.example.ghurskykursach.databinding.FragmentSettingBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private lateinit var auth: FirebaseAuth
    var status = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()

        binding.btnLogOut.setOnClickListener {
            auth.signOut()
            var intent = Intent(context, AuthActivity::class.java)
            startActivity(intent)
        }

        binding.btnHelpMe.setOnClickListener {
            val baseLink = try {
                requireContext().packageManager.getPackageInfo("org.telegram.messenger", 0)
                "tg://"
            } catch (_: Exception) {
                "https://t.me/"
            }

            try {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(baseLink + "meteostation13luban_bot")
                )
                context?.startActivity(urlIntent)
            } catch (_: Exception) {}
        }

    }
}