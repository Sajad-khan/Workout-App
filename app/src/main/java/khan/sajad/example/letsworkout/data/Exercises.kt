package khan.sajad.example.letsworkout.data

import khan.sajad.example.letsworkout.R
import khan.sajad.example.letsworkout.model.Exercise

class Exercises {
    fun loadExercises(): ArrayList<Exercise>{
        return arrayListOf(
            Exercise("Push Ups", R.drawable.push_up, false,
                completed = false,
                exerciseNo = 1
            ),
            Exercise("Jumping Jacks", R.drawable.jumping_jacks,
                selected = false,
                completed = false,
                exerciseNo = 2
            ),
            Exercise("Side Squats", R.drawable.side_to_side_squats,
                selected = false,
                completed = false,
                exerciseNo = 3
            ),
            Exercise("Spiderman Plank", R.drawable.spiderman_plank,
                selected = false,
                completed = false,
                exerciseNo = 4
            ),
            Exercise("Jump Squats", R.drawable.jump_squat,
                selected = false,
                completed = false,
                exerciseNo = 5
            ),
            Exercise("Reverse Crunches", R.drawable.reverse_crunches,
                selected = false,
                completed = false,
                exerciseNo = 6
            )
        )
    }
}