package khan.sajad.example.letsworkout.model

import androidx.annotation.DrawableRes
data class Exercise(val name: String,
                    @DrawableRes val image: Int,
                    var selected: Boolean,
                    private var completed: Boolean,
                    var exerciseNo: Int){
}
