package khan.sajad.example.letsworkout.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(val name: String,
                    @DrawableRes val image: Int,
                    val completed: Boolean)
