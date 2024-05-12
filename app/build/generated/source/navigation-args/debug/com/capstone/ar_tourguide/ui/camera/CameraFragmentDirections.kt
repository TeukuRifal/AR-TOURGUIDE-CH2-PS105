package com.capstone.ar_tourguide.ui.camera

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.capstone.ar_tourguide.R

public class CameraFragmentDirections private constructor() {
  public companion object {
    public fun actionCameraFragmentToDetailWisataFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_cameraFragment_to_detailWisataFragment)
  }
}
