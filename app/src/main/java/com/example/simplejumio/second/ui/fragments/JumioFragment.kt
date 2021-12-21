package com.example.simplejumio.second.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.simplejumio.BaseApplication
import com.example.simplejumio.BaseFragment
import com.example.simplejumio.R
import com.example.simplejumio.common.ui.injectViewModel
import com.jumio.defaultui.JumioActivity
import com.jumio.sdk.JumioSDK
import com.jumio.sdk.enums.JumioDataCenter
import com.jumio.sdk.result.JumioFaceResult
import com.jumio.sdk.result.JumioIDResult
import com.jumio.sdk.result.JumioResult
import javax.inject.Inject

class JumioFragment : BaseFragment() {

    private lateinit var identitySdk: JumioSDK
    private lateinit var startBtn: Button

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private lateinit var viewModel: JumioViewModel

    override fun updateActionsClickState(state: Boolean) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as BaseApplication).secondComponent().inject(this)
        val root = inflater.inflate(R.layout.jumio_fragment, container, false)
        startBtn = root.findViewById(R.id.second_fragment_setup_sdk_btn)
        viewModel = injectViewModel(providerFactory)
        startBtn.setOnClickListener {
            initializeIdentitySDK()
        }
        return root
    }

    private fun initializeIdentitySDK() {
        identitySdk = JumioSDK(requireContext())
        identitySdk.token =
            "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAJXOMY4CMQxA0bukXkuOYyc2HSXt3iBO7BOstEiIuzMDJ6D9esV_lLhf_8ql1N7MBIkNsZWfMte67aNvVAphAdLswJkBLmOCspMFuuteJ3_jSK-hMSFHJLBHh1kloLFjny3COh74P-Mbvn4jD81qtU6sQJx-6Eow92hAojtIm8U-xz_bY_oaVh3QlwKLEGiKwHYn6UZ7pJTnCzQxtAT-AAAA.1bWyfa4Py9lWd5BnZLBhHfSUR5ygtrarQVbB-eWjUsOWc1XCVt1jO-YLQlZLTO5zpWAz-aRsi3pp60fM_2Rfng"

        identitySdk.dataCenter = JumioDataCenter.EU

        if (JumioSDK.isRooted(requireContext())) {
            throw Exception()
        }
        if (!JumioSDK.isSupportedPlatform(requireContext())) {
            throw Exception()
        }

        if (!JumioSDK.hasAllRequiredPermissions(requireContext())) {
            JumioSDK.getMissingPermissions(requireContext())
        }

        val intent = Intent(requireContext(), JumioActivity::class.java)
        intent.putExtra(JumioActivity.EXTRA_TOKEN, identitySdk.token)
        intent.putExtra(JumioActivity.EXTRA_DATACENTER, JumioDataCenter.EU.name)
        sdkForResultLauncher.launch(intent)
    }

    private val sdkForResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val jumioResult: JumioResult =
                result.data?.getSerializableExtra(JumioActivity.EXTRA_RESULT) as JumioResult

            if (jumioResult.isSuccess) {
                Log.d("jumio_result", "RESULT_OK")
                jumioResult.credentialInfos?.forEach {
                    when (jumioResult.getResult(it)) {
                        is JumioIDResult -> {
                        }
                        is JumioFaceResult -> { //check your face result here
                        }
                    }
                }
            } else {
                jumioResult.error?.let {
                    Log.d("jumio_result", it.message)
                }
            }
        }

}