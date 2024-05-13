/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>,
            val nameTheme: String
    )

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val allQuestions: MutableList<Question> = mutableListOf(
            //Базовые фразы 1
            Question(text = "Привет",
                    answers = listOf("Hola", "Adiós", "Buenos días", "Hasta luego"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Доброе утро",
                    answers = listOf("Buenos días", "Buenas tardes", "Buenas noches", "Bienvenido"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Спасибо",
                    answers = listOf("Gracias", "Por favor", "De nada", "Disculpe"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Доброй ночи",
                    answers = listOf("Buenas noches", "Buenas tardes", "Adiós", "Estoy bien"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Извините",
                    answers = listOf("Disculpe", "Salida", "A la izquierda", "La casa"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Buenas tardes",
                    answers = listOf("Добрый день/вечер", "Доброе утро", "До свидания", "Доброй ночи"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "De nada",
                    answers = listOf("Не за что/Пожалуйста", "Пожалуйста (просьба)", "До свидания", "Спасибо"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Por favor",
                    answers = listOf("Пожалуйста (просьба)", "Не за что/Пожалуйста", "Спасибо", "Пока"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Hasta luego",
                    answers = listOf("До свидания", "Доброе утро", "Доброй ночи", "Извините"), nameTheme = "Тема 1. Базовые фразы (1)"),
            Question(text = "Adiós",
                    answers = listOf("Пока", "Привет", "Доброй ночи", "Спасибо"), nameTheme = "Тема 1. Базовые фразы (1)"),
            //Базовые фразы 2
            Question(text = "Да",
                    answers = listOf("Si", "Yo", "Mi", "Su"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "Нет",
                    answers = listOf("No", "Yo", "Tu", "Ella"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "Как тебя зовут?",
                    answers = listOf("¿Cómo te llamas?", "¿De dónde eres?", "¿Cuántos años tienes?", "¿Hablas Inglés?"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "Меня зовут...",
                    answers = listOf("Me llamo...", "Yo soy de...", "Yo tengo...", "Soy de..."), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "Как твои дела?",
                    answers = listOf("¿Cómo estás?", "¿De dónde eres?", "¿En qué trabajas?", "¿Hablas ruso?"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "Estoy bien",
                    answers = listOf("Я в порядке", "Да", "Мне плохо", "Счастливый"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "¿Cuántos años tienes?",
                    answers = listOf("Сколько тебe лет?", "Откуда ты?", "Как твои дела?", "Как тебя зовут?"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "Yo tengo ... años",
                    answers = listOf("Мне ... лет", "У меня есть ... денег", "Я хочу ... дом", "Мне нужно ... недель"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "¿De dónde eres?",
                    answers = listOf("Откуда ты?", "Как долго?", "Как твои дела?", "Сколько стоит?"), nameTheme = "Тема 2. Базовые фразы (2)"),
            Question(text = "Yo soy de ...",
                    answers = listOf("Я из ...", "Я ...", "Я хочу ...", "Мне нужно ..."), nameTheme = "Тема 2. Базовые фразы (2)"),
            //Места 1
            Question(text = "Музей",
                    answers = listOf("El museo", "El restaurante", "La gasolinera", "El ayuntamiento"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "Библиотека",
                    answers = listOf("La biblioteca", "El supermercado", "La universidad", "El apartamento"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "Парк",
                    answers = listOf("El parque", "El supermercado", "El ayuntamiento", "La gasolinera"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "Дом",
                    answers = listOf("La casa", "La estación", "La cafetería", "El banco"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "Школа",
                    answers = listOf("La escuela", "El mercado", "El bar", "El teatro"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "El apartamento",
                    answers = listOf("Квартира", "Центр города", "Музей", "Отель"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "La universidad",
                    answers = listOf("Университет", "Школа", "Зоопарк", "Магазин"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "El aeropuerto",
                    answers = listOf("Аэропорт", "Дом", "Школа", "Парковка"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "El restaurante",
                    answers = listOf("Ресторан", "Вокзал", "Университет", "Площадь"), nameTheme = "Тема 3. Места (1)"),
            Question(text = "El metro",
                    answers = listOf("Метро", "Вокзал", "Собор", "Заправка"), nameTheme = "Тема 3. Места (1)"),
            //Места 2
            Question(text = "Отель",
                    answers = listOf("El hotel", "La casa", "El ayuntamiento", "El apartamento"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "Театр",
                    answers = listOf("El teatro", "La escuela", "El supermercado", "La casa"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "Больница",
                    answers = listOf("El hospital", "El museo", "El restaurante", "El parque"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "Супермаркет",
                    answers = listOf("El supermercado", "La cafetería", "El banco", "La escuela"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "Заправка",
                    answers = listOf("La gasolinera", "La biblioteca", "El ayuntamiento", "El banco"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "La cafetería",
                    answers = listOf("Кафе", "Метро", "Собор", "Школа"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "La estación",
                    answers = listOf("Вокзал", "Аэропорт", "Зоопарк", "Аптека"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "El banco",
                    answers = listOf("Банк", "Метро", "Заправка", "Отель"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "El mercado",
                    answers = listOf("Рынок", "Аптека", "Театр", "Вокзал"), nameTheme = "Тема 4. Места (2)"),
            Question(text = "La catedral",
                    answers = listOf("Собор", "Центр города", "Магазин", "Заправка"), nameTheme = "Тема 4. Места (2)"),
            //Семья
            Question(text = "Родители",
                    answers = listOf("Los padres", "El hermano", "Los hijos", "Los abuelos"), nameTheme = "Тема 5. Семья"),
            Question(text = "Брат",
                    answers = listOf("El hermano", "La hermana", "El hijo", "La madre"), nameTheme = "Тема 5. Семья"),
            Question(text = "Сестра",
                    answers = listOf("La hermana", "El hermano", "La madre", "La hija"), nameTheme = "Тема 5. Семья"),
            Question(text = "Дети",
                    answers = listOf("Los hijos", "Los padres", "Los abuelos", "El hermano"), nameTheme = "Тема 5. Семья"),
            Question(text = "Мама",
                    answers = listOf("La madre", "El padre", "La hija", "La abuela"), nameTheme = "Тема 5. Семья"),
            Question(text = "El padre",
                    answers = listOf("Папа", "Мама", "Сын", "Сестра"), nameTheme = "Тема 5. Семья"),
            Question(text = "El hijo",
                    answers = listOf("Сын", "Дочь", "Сестра", "Брат"), nameTheme = "Тема 5. Семья"),
            Question(text = "La hija",
                    answers = listOf("Дочь", "Сын", "Сестра", "Брат"), nameTheme = "Тема 5. Семья"),
            Question(text = "La abuela",
                    answers = listOf("Бабушка", "Дедушка", "Брат", "Родители"), nameTheme = "Тема 5. Семья"),
            Question(text = "El abuelo",
                    answers = listOf("Дедушка", "Бабушка", "Родители", "Брат"), nameTheme = "Тема 5. Семья"),
            //Дом
            Question(text = "Комната",
                    answers = listOf("La habitación", "La casa", "El dormitorio", "El salón"), nameTheme = "Тема 6. Дом"),
            Question(text = "Кухня",
                    answers = listOf("La cocina", "La casa", "La habitación", "La ventana"), nameTheme = "Тема 6. Дом"),
            Question(text = "Спальня",
                    answers = listOf("El dormitorio", "La cocina", "La habitación", "La casa"), nameTheme = "Тема 6. Дом"),
            Question(text = "Кровать",
                    answers = listOf("La cama", "El armario", "La puerta", "La mesa"), nameTheme = "Тема 6. Дом"),
            Question(text = "Шкаф",
                    answers = listOf("El armario", "La cama", "La mesa", "El dormitorio"), nameTheme = "Тема 6. Дом"),
            Question(text = "La ventana",
                    answers = listOf("Окно", "Кресло", "Дверь", "Стол"), nameTheme = "Тема 6. Дом"),
            Question(text = "La puerta",
                    answers = listOf("Дверь", "Окно", "Кровать", "Стул"), nameTheme = "Тема 6. Дом"),
            Question(text = "El salón",
                    answers = listOf("Гостиная", "Спальня", "Кухня", "Кресло"), nameTheme = "Тема 6. Дом"),
            Question(text = "La mesa",
                    answers = listOf("Стол", "Стул", "Окно", "Холодильник"), nameTheme = "Тема 6. Дом"),
            Question(text = "La silla",
                    answers = listOf("Стул", "Стол", "Кровать", "Плита"), nameTheme = "Тема 6. Дом"),
            //Погода
            Question(text = "Погода",
                    answers = listOf("El tiempo", "El banco", "La hija", "La puerta"), nameTheme = "Тема 7. Погода"),
            Question(text = "Жарко",
                    answers = listOf("Hace calor", "Hace frio", "Hace sol", "Llover"), nameTheme = "Тема 7. Погода"),
            Question(text = "Холодно",
                    answers = listOf("Hace frio", "Hace calor", "Nevar", "Hace sol"), nameTheme = "Тема 7. Погода"),
            Question(text = "Солнечно",
                    answers = listOf("Hace sol", "Hace frio", "Hace viento", "Tronar"), nameTheme = "Тема 7. Погода"),
            Question(text = "Ветрено",
                    answers = listOf("Hace viento", "Hace sol", "Hace calor", "Llover"), nameTheme = "Тема 7. Погода"),
            Question(text = "Llover",
                    answers = listOf("Идет дождь", "Ветрено", "Идет снег", "Гремит гром"), nameTheme = "Тема 7. Погода"),
            Question(text = "Nevar",
                    answers = listOf("Идет снег", "Гремит гром", "Жарко", "Идет дождь"), nameTheme = "Тема 7. Погода"),
            Question(text = "Tronar",
                    answers = listOf("Гремит гром", "Идет снег", "Ветрено", "Холодно"), nameTheme = "Тема 7. Погода"),
            Question(text = "Hace buen tiempo",
                    answers = listOf("Погода хорошая", "Погода плохая", "Жарко", "Холодно"), nameTheme = "Тема 7. Погода"),
            Question(text = "Hace mal tiempo",
                    answers = listOf("Погода плохая", "Погода хорошая", "Солнечно", "Ветрено"), nameTheme = "Тема 7. Погода"),
            //Числительные
            Question(text = "Один",
                    answers = listOf("Uno", "Seis", "Ocho", "Diez"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Два",
                    answers = listOf("Dos", "Tres", "Nueve", "Quince"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Три",
                    answers = listOf("Tres", "Veinte", "Setenta", "Treinta"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Четыре",
                    answers = listOf("Cuatro", "Catorce", "Trece", "Quince"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Пять",
                    answers = listOf("Cinco", "Once", "Dos", "Mil"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Seis",
                    answers = listOf("Шесть", "Семь", "Два", "Один"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Siete",
                    answers = listOf("Семь", "Шесть", "Пять", "Восемь"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Ocho",
                    answers = listOf("Восемь", "Девять", "Три", "Один"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Nueve",
                    answers = listOf("Девять", "Десять", "Два", "Восемь"), nameTheme = "Тема 8. Числительные"),
            Question(text = "Diez",
                    answers = listOf("Десять", "Девять", "Один", "Три"), nameTheme = "Тема 8. Числительные"),
            //Продуктовый магазин
            Question(text = "Фрукты",
                    answers = listOf("La fruta", "La verdura", "La naranja", "El melocotón"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "Сахар",
                    answers = listOf("El azúcar", "El arroz", "La manzana", "El plátano"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "Молоко",
                    answers = listOf("La leche", "El agua", "El limón", "El melón"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "Хлеб",
                    answers = listOf("El pan", "El azúcar", "La manzana", "El melocotón"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "Вода",
                    answers = listOf("El agua", "La leche", "La manzana", "El limón"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "Las verduras",
                    answers = listOf("Овощи", "Фрукты", "Масло", "Чай"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "El arroz",
                    answers = listOf("Рис", "Хлеб", "Бутылка", "Мясо"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "El chocolate",
                    answers = listOf("Шоколад", "Овощи", "Конфета", "Чай"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "La sal",
                    answers = listOf("Соль", "Вода", "Сахар", "Перец"), nameTheme = "Тема 9. Продуктовый магазин"),
            Question(text = "La carne",
                    answers = listOf("Мясо", "Сахар", "Масло", "Вода"), nameTheme = "Тема 9. Продуктовый магазин"),
            //Дни недели
            Question(text = "Понедельник",
                    answers = listOf("Lunes", "Enero", "El año", "La semana"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Вторник",
                    answers = listOf("Martes", "Marzo", "Sábado", "Domingo"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Среда",
                    answers = listOf("Miércoles", "El mes", "Lunes", "La semana"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Четверг",
                    answers = listOf("Jueves", "Martes", "El año", "Viernes"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Неделя",
                    answers = listOf("La semana", "El año", "El mes", "El día"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "El día",
                    answers = listOf("День", "Неделя", "Год", "Месяц"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Viernes",
                    answers = listOf("Пятница", "Среда", "Четверг", "День"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Sábado",
                    answers = listOf("Суббота", "Неделя", "Понедельник", "Год"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Domingo",
                    answers = listOf("Воскресенье", "Вторник", "Среда", "Неделя"), nameTheme = "Тема 10. Дни недели"),
            Question(text = "Fin de semana",
                    answers = listOf("Выходные", "Воскресенье", "Неделя", "Год"), nameTheme = "Тема 10. Дни недели")
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 10
    //val numQuestions = Math.min((allQuestions.size + 1) / 2, 10)
    private val questions: MutableList<Question> = mutableListOf<Question>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val args = GameFragmentArgs.fromBundle(requireArguments())
        for (i in allQuestions){
            if (i.nameTheme == args.nameTheme){
                questions.add(i)
            }
        }

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(numQuestions,questionIndex))
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
                }
            }
        }
        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
