package com.erix.course.philipp.onboarding_presentation.gender.events

import com.erix.course.philipp.core_main.domain.model.Gender

sealed class GenderEvent {
    class OnSelectedGender(val gender: Gender): GenderEvent()
}
