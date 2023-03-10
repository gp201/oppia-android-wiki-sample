package org.oppia.android.app.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.oppia.android.app.fragment.FragmentComponentImpl
import org.oppia.android.app.fragment.InjectableFragment
import javax.inject.Inject

/** Fragment that allows user to select a profile or create new ones. */
class ProfileChooserFragment : InjectableFragment(), RouteToAdminPinListener {
  @Inject
  lateinit var profileChooserFragmentPresenter: ProfileChooserFragmentPresenter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (fragmentComponent as FragmentComponentImpl).inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return profileChooserFragmentPresenter.handleCreateView(inflater, container)
  }

  override fun routeToAdminPin() {
    profileChooserFragmentPresenter.routeToAdminPin()
  }
}
