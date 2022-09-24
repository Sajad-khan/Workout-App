package khan.sajad.example.letsworkout.data

import khan.sajad.example.letsworkout.R
import khan.sajad.example.letsworkout.model.Exercise

class Exercises {
    fun loadExercises(): ArrayList<Exercise>{
        return arrayListOf(
            Exercise("Push Ups", R.drawable.push_up, false),
            Exercise("Jumping Jacks", R.drawable.jumping_jacks, false),
            Exercise("Side Squats", R.drawable.side_to_side_squats, false),
            Exercise("Spiderman Plank", R.drawable.spiderman_plank, false),
            Exercise("Jump Squats", R.drawable.jump_squat, false),
            Exercise("Reverse Crunches", R.drawable.reverse_crunches, false)
        )
    }
}