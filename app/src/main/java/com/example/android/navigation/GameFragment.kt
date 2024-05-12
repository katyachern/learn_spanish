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
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>,
            val numTheme: Int
    )

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val allQuestions: MutableList<Question> = mutableListOf(
            //Базовые фразы 1
            Question(text = "Привет",
                    answers = listOf("Hola", "Adiós", "Buenos días", "Hasta luego"), numTheme = 1),
            Question(text = "Доброе утро",
                    answers = listOf("Buenos días", "Buenas tardes", "Buenas noches", "Bienvenido"), numTheme = 1),
            Question(text = "Спасибо",
                    answers = listOf("Gracias", "Por favor", "De nada", "Disculpe"), numTheme = 1),
            Question(text = "Доброй ночи",
                    answers = listOf("Buenas noches", "Buenas tardes", "Adiós", "Estoy bien"), numTheme = 1),
            Question(text = "Извините",
                    answers = listOf("Disculpe", "Salida", "A la izquierda", "La casa"), numTheme = 1),
            Question(text = "Buenas tardes",
                    answers = listOf("Добрый день/вечер", "Доброе утро", "До свидания", "Доброй ночи"), numTheme = 1),
            Question(text = "De nada",
                    answers = listOf("Не за что/Пожалуйста", "Пожалуйста (просьба)", "До свидания", "Спасибо"), numTheme = 1),
            Question(text = "Por favor",
                    answers = listOf("Пожалуйста (просьба)", "Не за что/Пожалуйста", "Спасибо", "Пока"), numTheme = 1),
            Question(text = "Hasta luego",
                    answers = listOf("До свидания", "Доброе утро", "Доброй ночи", "Извините"), numTheme = 1),
            Question(text = "Adiós",
                    answers = listOf("Пока", "Привет", "Доброй ночи", "Спасибо"), numTheme = 1),
            //Базовые фразы 2
            Question(text = "Да",
                    answers = listOf("Si", "Yo", "Mi", "Su"), numTheme = 2),
            Question(text = "Нет",
                    answers = listOf("No", "Yo", "Tu", "Ella"), numTheme = 2),
            Question(text = "Как тебя зовут?",
                    answers = listOf("¿Cómo te llamas?", "¿De dónde eres?", "¿Cuántos años tienes?", "¿Hablas Inglés?"), numTheme = 2),
            Question(text = "Меня зовут...",
                    answers = listOf("Me llamo...", "Yo soy de...", "Yo tengo...", "Soy de..."), numTheme = 2),
            Question(text = "Как твои дела?",
                    answers = listOf("¿Cómo estás?", "¿De dónde eres?", "¿En qué trabajas?", "¿Hablas ruso?"), numTheme = 2),
            Question(text = "Estoy bien",
                    answers = listOf("Я в порядке", "Да", "Мне плохо", "Счастливый"), numTheme = 2),
            Question(text = "¿Cuántos años tienes?",
                    answers = listOf("Сколько тебe лет?", "Откуда ты?", "Как твои дела?", "Как тебя зовут?"), numTheme = 2),
            Question(text = "Yo tengo ... años",
                    answers = listOf("Мне ... лет", "У меня есть ... денег", "Я хочу ... дом", "Мне нужно ... недель"), numTheme = 2),
            Question(text = "¿De dónde eres?",
                    answers = listOf("Откуда ты?", "Как долго?", "Как твои дела?", "Сколько стоит?"), numTheme = 2),
            Question(text = "Yo soy de ...",
                    answers = listOf("Я из ...", "Я ...", "Я хочу ...", "Мне нужно ..."), numTheme = 2),
            //Места 1
            Question(text = "Музей",
                    answers = listOf("El museo", "El restaurante", "La gasolinera", "El ayuntamiento"), numTheme = 3),
            Question(text = "Библиотека",
                    answers = listOf("La biblioteca", "El supermercado", "La universidad", "El apartamento"), numTheme = 3),
            Question(text = "Парк",
                    answers = listOf("El parque", "El supermercado", "El ayuntamiento", "La gasolinera"), numTheme = 3),
            Question(text = "Дом",
                    answers = listOf("La casa", "La estación", "La cafetería", "El banco"), numTheme = 3),
            Question(text = "Школа",
                    answers = listOf("La escuela", "El mercado", "El bar", "El teatro"), numTheme = 3),
            Question(text = "El apartamento",
                    answers = listOf("Квартира", "Центр города", "Музей", "Отель"), numTheme = 3),
            Question(text = "La universidad",
                    answers = listOf("Университет", "Школа", "Зоопарк", "Магазин"), numTheme = 3),
            Question(text = "El aeropuerto",
                    answers = listOf("Аэропорт", "Дом", "Школа", "Парковка"), numTheme = 3),
            Question(text = "El restaurante",
                    answers = listOf("Ресторан", "Вокзал", "Университет", "Площадь"), numTheme = 3),
            Question(text = "El metro",
                    answers = listOf("Метро", "Вокзал", "Собор", "Заправка"), numTheme = 3),
            //Места 2
            Question(text = "Отель",
                    answers = listOf("El hotel", "La casa", "El ayuntamiento", "El apartamento"), numTheme = 4),
            Question(text = "Театр",
                    answers = listOf("El teatro", "La escuela", "El supermercado", "La casa"), numTheme = 4),
            Question(text = "Больница",
                    answers = listOf("El hospital", "El museo", "El restaurante", "El parque"), numTheme = 4),
            Question(text = "Супермаркет",
                    answers = listOf("El supermercado", "La cafetería", "El banco", "La escuela"), numTheme = 4),
            Question(text = "Заправка",
                    answers = listOf("La gasolinera", "La biblioteca", "El ayuntamiento", "El banco"), numTheme = 4),
            Question(text = "La cafetería",
                    answers = listOf("Кафе", "Метро", "Собор", "Школа"), numTheme = 4),
            Question(text = "La estación",
                    answers = listOf("Вокзал", "Аэропорт", "Зоопарк", "Аптека"), numTheme = 4),
            Question(text = "El banco",
                    answers = listOf("Банк", "Метро", "Заправка", "Отель"), numTheme = 4),
            Question(text = "El mercado",
                    answers = listOf("Рынок", "Аптека", "Театр", "Вокзал"), numTheme = 4),
            Question(text = "La catedral",
                    answers = listOf("Собор", "Центр города", "Магазин", "Заправка"), numTheme = 4),
            //Семья
            Question(text = "Родители",
                    answers = listOf("Los padres", "El hermano", "Los hijos", "Los abuelos"), numTheme = 5),
            Question(text = "Брат",
                    answers = listOf("El hermano", "La hermana", "El hijo", "La madre"), numTheme = 5),
            Question(text = "Сестра",
                    answers = listOf("La hermana", "El hermano", "La madre", "La hija"), numTheme = 5),
            Question(text = "Дети",
                    answers = listOf("Los hijos", "Los padres", "Los abuelos", "El hermano"), numTheme = 5),
            Question(text = "Мама",
                    answers = listOf("La madre", "El padre", "La hija", "La abuela"), numTheme = 5),
            Question(text = "El padre",
                    answers = listOf("Папа", "Мама", "Сын", "Сестра"), numTheme = 5),
            Question(text = "El hijo",
                    answers = listOf("Сын", "Дочь", "Сестра", "Брат"), numTheme = 5),
            Question(text = "La hija",
                    answers = listOf("Дочь", "Сын", "Сестра", "Брат"), numTheme = 5),
            Question(text = "La abuela",
                    answers = listOf("Бабушка", "Дедушка", "Брат", "Родители"), numTheme = 5),
            Question(text = "El abuelo",
                    answers = listOf("Дедушка", "Бабушка", "Родители", "Брат"), numTheme = 5),
            //Дом
            Question(text = "Комната",
                    answers = listOf("La habitación", "La casa", "El dormitorio", "El salón"), numTheme = 6),
            Question(text = "Кухня",
                    answers = listOf("La cocina", "La casa", "La habitación", "La ventana"), numTheme = 6),
            Question(text = "Спальня",
                    answers = listOf("El dormitorio", "La cocina", "La habitación", "La casa"), numTheme = 6),
            Question(text = "Кровать",
                    answers = listOf("La cama", "El armario", "La puerta", "La mesa"), numTheme = 6),
            Question(text = "Шкаф",
                    answers = listOf("El armario", "La cama", "La mesa", "El dormitorio"), numTheme = 6),
            Question(text = "La ventana",
                    answers = listOf("Окно", "Кресло", "Дверь", "Стол"), numTheme = 6),
            Question(text = "La puerta",
                    answers = listOf("Дверь", "Окно", "Кровать", "Стул"), numTheme = 6),
            Question(text = "El salón",
                    answers = listOf("Гостиная", "Спальня", "Кухня", "Кресло"), numTheme = 6),
            Question(text = "La mesa",
                    answers = listOf("Стол", "Стул", "Окно", "Холодильник"), numTheme = 6),
            Question(text = "La silla",
                    answers = listOf("Стул", "Стол", "Кровать", "Плита"), numTheme = 6),
            //Погода
            Question(text = "Погода",
                    answers = listOf("El tiempo", "El banco", "La hija", "La puerta"), numTheme = 7),
            Question(text = "Жарко",
                    answers = listOf("Hace calor", "Hace frio", "Hace sol", "Llover"), numTheme = 7),
            Question(text = "Холодно",
                    answers = listOf("Hace frio", "Hace calor", "Nevar", "Hace sol"), numTheme = 7),
            Question(text = "Солнечно",
                    answers = listOf("Hace sol", "Hace frio", "Hace viento", "Tronar"), numTheme = 7),
            Question(text = "Ветрено",
                    answers = listOf("Hace viento", "Hace sol", "Hace calor", "Llover"), numTheme = 7),
            Question(text = "Llover",
                    answers = listOf("Идет дождь", "Ветрено", "Идет снег", "Гремит гром"), numTheme = 7),
            Question(text = "Nevar",
                    answers = listOf("Идет снег", "Гремит гром", "Жарко", "Идет дождь"), numTheme = 7),
            Question(text = "Tronar",
                    answers = listOf("Гремит гром", "Идет снег", "Ветрено", "Холодно"), numTheme = 7),
            Question(text = "Hace buen tiempo",
                    answers = listOf("Погода хорошая", "Погода плохая", "Жарко", "Холодно"), numTheme = 7),
            Question(text = "Hace mal tiempo",
                    answers = listOf("Погода плохая", "Погода хорошая", "Солнечно", "Ветрено"), numTheme = 7),
            //Числительные
            Question(text = "Один",
                    answers = listOf("Uno", "Seis", "Ocho", "Diez"), numTheme = 8),
            Question(text = "Два",
                    answers = listOf("Dos", "Tres", "Nueve", "Quince"), numTheme = 8),
            Question(text = "Три",
                    answers = listOf("Tres", "Veinte", "Setenta", "Treinta"), numTheme = 8),
            Question(text = "Четыре",
                    answers = listOf("Cuatro", "Catorce", "Trece", "Quince"), numTheme = 8),
            Question(text = "Пять",
                    answers = listOf("Cinco", "Once", "Dos", "Mil"), numTheme = 8),
            Question(text = "Seis",
                    answers = listOf("Шесть", "Семь", "Два", "Один"), numTheme = 8),
            Question(text = "Siete",
                    answers = listOf("Семь", "Шесть", "Пять", "Восемь"), numTheme = 8),
            Question(text = "Ocho",
                    answers = listOf("Восемь", "Девять", "Три", "Один"), numTheme = 8),
            Question(text = "Nueve",
                    answers = listOf("Девять", "Десять", "Два", "Восемь"), numTheme = 8),
            Question(text = "Diez",
                    answers = listOf("Десять", "Девять", "Один", "Три"), numTheme = 8),
            //Продуктовый магазин
            Question(text = "Фрукты",
                    answers = listOf("La fruta", "La verdura", "La naranja", "El melocotón"), numTheme = 9),
            Question(text = "Сахар",
                    answers = listOf("El azúcar", "El arroz", "La manzana", "El plátano"), numTheme = 9),
            Question(text = "Молоко",
                    answers = listOf("La leche", "El agua", "El limón", "El melón"), numTheme = 9),
            Question(text = "Хлеб",
                    answers = listOf("El pan", "El azúcar", "La manzana", "El melocotón"), numTheme = 9),
            Question(text = "Вода",
                    answers = listOf("El agua", "La leche", "La manzana", "El limón"), numTheme = 9),
            Question(text = "Las verduras",
                    answers = listOf("Овощи", "Фрукты", "Масло", "Чай"), numTheme = 9),
            Question(text = "El arroz",
                    answers = listOf("Рис", "Хлеб", "Бутылка", "Мясо"), numTheme = 9),
            Question(text = "El chocolate",
                    answers = listOf("Шоколад", "Овощи", "Конфета", "Чай"), numTheme = 9),
            Question(text = "La sal",
                    answers = listOf("Соль", "Вода", "Сахар", "Перец"), numTheme = 9),
            Question(text = "La carne",
                    answers = listOf("Мясо", "Сахар", "Масло", "Вода"), numTheme = 9),
            //Дни недели
            Question(text = "Понедельник",
                    answers = listOf("Lunes", "Enero", "El año", "La semana"), numTheme = 10),
            Question(text = "Вторник",
                    answers = listOf("Martes", "Marzo", "Sábado", "Domingo"), numTheme = 10),
            Question(text = "Среда",
                    answers = listOf("Miércoles", "El mes", "Lunes", "La semana"), numTheme = 10),
            Question(text = "Четверг",
                    answers = listOf("Jueves", "Martes", "El año", "Viernes"), numTheme = 10),
            Question(text = "Неделя",
                    answers = listOf("La semana", "El año", "El mes", "El día"), numTheme = 10),
            Question(text = "El día",
                    answers = listOf("День", "Неделя", "Год", "Месяц"), numTheme = 10),
            Question(text = "Viernes",
                    answers = listOf("Пятница", "Среда", "Четверг", "День"), numTheme = 10),
            Question(text = "Sábado",
                    answers = listOf("Суббота", "Неделя", "Понедельник", "Год"), numTheme = 10),
            Question(text = "Domingo",
                    answers = listOf("Воскресенье", "Вторник", "Среда", "Неделя"), numTheme = 10),
            Question(text = "Fin de semana",
                    answers = listOf("Выходные", "Воскресенье", "Неделя", "Год"), numTheme = 10)
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    //private lateinit var spinner: Spinner
    private var questionIndex = 0
    private val numQuestions = 10
    //val numQuestions = Math.min((allQuestions.size + 1) / 2, 10)
    private val questions: MutableList<Question> = mutableListOf<Question>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val args = GameFragmentArgs.fromBundle(requireArguments())
        for (i in allQuestions){
            if (i.numTheme == args.numTheme){
            questions.add(i)
            }
        }

        //spinner = view.findViewById(R.id.spinner)
        //val selected = spinner.selectedItem
        //val textView = view.findViewById(R.id.themeTextView)
        //textView.themeTextView = "Выбрано: $selected"

        // Получаем экземпляр элемента Spinner
        //setContentView(R.layout.activity_main)
        //spinner = findViewById<View>(R.id.spinner)
        //val spinnerArray = resources.getStringArray(R.array.spinner_array)
        // Настраиваем адаптер
        //val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerArray)
        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Вызываем адаптер
        //spinner.setAdapter(arrayAdapter)
        //spinner.adapter = arrayAdapter

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
