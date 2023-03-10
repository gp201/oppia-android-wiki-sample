package org.oppia.android.app.topic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.oppia.android.R
import org.oppia.android.app.activity.ActivityScope
import org.oppia.android.app.model.ProfileId
import org.oppia.android.app.spotlight.SpotlightFragment
import org.oppia.android.app.spotlight.SpotlightManager
import javax.inject.Inject

const val TOPIC_FRAGMENT_TAG = "TopicFragment"
const val PROFILE_ID_ARGUMENT_KEY = "profile_id"
const val TOPIC_ID_ARGUMENT_KEY = "topic_id"
const val STORY_ID_ARGUMENT_KEY = "story_id"

/** The presenter for [TopicActivity]. */
@ActivityScope
class TopicActivityPresenter @Inject constructor(private val activity: AppCompatActivity) {
  private lateinit var topicId: String

  private lateinit var profileId: ProfileId

  fun handleOnCreate(internalProfileId: Int, topicId: String, storyId: String?) {
    this.topicId = topicId
    activity.setContentView(R.layout.topic_activity)
    profileId = ProfileId.newBuilder().setInternalId(internalProfileId).build()
    if (getTopicFragment() == null) {
      val topicFragment = TopicFragment()
      val args = Bundle()
      args.putInt(PROFILE_ID_ARGUMENT_KEY, internalProfileId)
      args.putString(TOPIC_ID_ARGUMENT_KEY, topicId)
      if (storyId != null) {
        args.putString(STORY_ID_ARGUMENT_KEY, storyId)
      }
      topicFragment.arguments = args
      activity.supportFragmentManager.beginTransaction().add(
        R.id.topic_fragment_placeholder,
        topicFragment, TOPIC_FRAGMENT_TAG
      ).commitNow()
    }

    if (getSpotlightFragment() == null) {
      activity.supportFragmentManager.beginTransaction().add(
        R.id.topic_spotlight_fragment_placeholder,
        SpotlightFragment.newInstance(internalProfileId),
        SpotlightManager.SPOTLIGHT_FRAGMENT_TAG
      ).commitNow()
    }
  }

  private fun getTopicFragment(): TopicFragment? {
    return activity
      .supportFragmentManager
      .findFragmentById(
        R.id.topic_fragment_placeholder
      ) as TopicFragment?
  }

  private fun getSpotlightFragment(): SpotlightFragment? {
    return activity.supportFragmentManager.findFragmentByTag(
      SpotlightManager.SPOTLIGHT_FRAGMENT_TAG
    ) as? SpotlightFragment
  }
}
