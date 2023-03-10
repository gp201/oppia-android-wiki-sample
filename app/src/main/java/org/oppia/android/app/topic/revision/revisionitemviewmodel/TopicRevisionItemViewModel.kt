package org.oppia.android.app.topic.revision.revisionitemviewmodel

import org.oppia.android.app.model.EphemeralSubtopic
import org.oppia.android.app.topic.revision.RevisionSubtopicSelector
import org.oppia.android.app.viewmodel.ObservableViewModel
import org.oppia.android.domain.translation.TranslationController

/** [ObservableViewModel] for child views of recycler view present in the [TopicRevisionFragment]. */
class TopicRevisionItemViewModel(
  val topicId: String,
  ephemeralSubtopic: EphemeralSubtopic,
  val entityType: String,
  val onRevisionItemPressed: RevisionSubtopicSelector,
  translationController: TranslationController
) : ObservableViewModel() {
  /** The subtopic being displayed. */
  val subtopic = ephemeralSubtopic.subtopic

  /** The localized title of the subtopic being displayed. */
  val subtopicTitle by lazy {
    translationController.extractString(subtopic.title, ephemeralSubtopic.writtenTranslationContext)
  }
}
