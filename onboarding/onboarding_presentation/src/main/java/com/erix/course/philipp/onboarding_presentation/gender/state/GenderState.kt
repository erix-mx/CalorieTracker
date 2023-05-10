package com.erix.course.philipp.onboarding_presentation.gender.state

import com.erix.course.philipp.core_main.domain.model.Gender

data class GenderState(
    val selectedGender: Gender = Gender.Male,
)