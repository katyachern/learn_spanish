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
            val nameTheme: String,
            val testTheme: String
    )

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val allQuestions: MutableList<Question> = mutableListOf(
            //Тема 1. Базовые фразы
            //Тест 1. Базовые фразы
            Question(text = "Привет",
                    answers = listOf("Hola", "Adiós", "Buenos días", "Hasta luego"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Доброе утро",
                    answers = listOf("Buenos días", "Buenas tardes", "Buenas noches", "Bienvenido"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Спасибо",
                    answers = listOf("Gracias", "Por favor", "De nada", "Disculpe"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Доброй ночи",
                    answers = listOf("Buenas noches", "Buenas tardes", "Adiós", "Estoy bien"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Извините",
                    answers = listOf("Disculpe", "Salida", "A la izquierda", "La casa"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Buenas tardes",
                    answers = listOf("Добрый день/вечер", "Доброе утро", "До свидания", "Доброй ночи"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "De nada",
                    answers = listOf("Не за что/Пожалуйста", "Пожалуйста (просьба)", "До свидания", "Спасибо"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Por favor",
                    answers = listOf("Пожалуйста (просьба)", "Не за что/Пожалуйста", "Спасибо", "Пока"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Hasta luego",
                    answers = listOf("До свидания", "Доброе утро", "Доброй ночи", "Извините"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            Question(text = "Adiós",
                    answers = listOf("Пока", "Привет", "Доброй ночи", "Спасибо"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 1"),
            //Тест 2. Базовые фразы
            Question(text = "Да",
                    answers = listOf("Si", "Yo", "Mi", "Su"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "Нет",
                    answers = listOf("No", "Yo", "Tu", "Ella"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "Как тебя зовут?",
                    answers = listOf("¿Cómo te llamas?", "¿De dónde eres?", "¿Cuántos años tienes?", "¿Hablas inglés?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "Меня зовут...",
                    answers = listOf("Me llamo...", "Yo soy de...", "Yo tengo...", "Soy de..."), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "Как твои дела?",
                    answers = listOf("¿Cómo estás?", "¿De dónde eres?", "¿En qué trabajas?", "¿Hablas ruso?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "Estoy bien",
                    answers = listOf("Я в порядке", "Да", "Мне плохо", "Счастливый"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "¿Cuántos años tienes?",
                    answers = listOf("Сколько тебe лет?", "Откуда ты?", "Как твои дела?", "Как тебя зовут?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "Yo tengo ... años",
                    answers = listOf("Мне ... лет", "У меня есть ... денег", "Я хочу ... дом", "Мне нужно ... недель"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "¿De dónde eres?",
                    answers = listOf("Откуда ты?", "Как долго?", "Как твои дела?", "Сколько стоит?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            Question(text = "Yo soy de ...",
                    answers = listOf("Я из ...", "Я ...", "Я хочу ...", "Мне нужно ..."), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 2"),
            //Тест 3. Базовые фразы
            Question(text = "Сколько тебe лет?",
                    answers = listOf("¿Cuántos años tienes?", "¿Cómo estás?", "¿Cómo te llamas?", "¿De dónde eres?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "Откуда ты?",
                    answers = listOf("¿De dónde eres?", "¿Cómo estás?", "¿Cómo te llamas?", "¿Hablas Inglés?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "Пока",
                    answers = listOf("Adiós", "Yo", "De nada", "Hola"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "Я не понимаю",
                    answers = listOf("No entiendo", "No puede", "No quiero", "Hasta luego"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "Вы говорите по-испански?",
                    answers = listOf("¿Hablas español?", "¿Hablas ruso?", "¿Hablas inglés?", "¿En qué trabajas?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "¿Cómo te llamas?",
                    answers = listOf("Как тебя зовут?", "Откуда ты?", "Как твои дела?", "Сколько стоит?"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "Gracias",
                    answers = listOf("Спасибо", "Пожалуйста (просьба)", "Извините", "Привет"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "Disculpe",
                    answers = listOf("Извините", "Спасибо", "Пока", "Нет"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "No lo sé",
                    answers = listOf("Я не знаю", "Я не понимаю", "Я не могу", "Я не хочу"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            Question(text = "Hasta mañana",
                    answers = listOf("Увидимся завтра", "До свидания", "Доброе утро", "Я в порядке"), nameTheme = "Тема 1. Базовые фразы", testTheme = "Тест 3"),
            //Тема 2. Места
            //Тест 1. Места
            Question(text = "Музей",
                    answers = listOf("El museo", "El restaurante", "La gasolinera", "El ayuntamiento"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "Библиотека",
                    answers = listOf("La biblioteca", "El supermercado", "La universidad", "El apartamento"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "Парк",
                    answers = listOf("El parque", "El supermercado", "El ayuntamiento", "La gasolinera"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "Дом",
                    answers = listOf("La casa", "La estación", "La cafetería", "El banco"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "Школа",
                    answers = listOf("La escuela", "El mercado", "El bar", "El teatro"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "El apartamento",
                    answers = listOf("Квартира", "Центр города", "Музей", "Отель"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "La universidad",
                    answers = listOf("Университет", "Школа", "Зоопарк", "Магазин"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "El aeropuerto",
                    answers = listOf("Аэропорт", "Дом", "Школа", "Парковка"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "El restaurante",
                    answers = listOf("Ресторан", "Вокзал", "Университет", "Площадь"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            Question(text = "El metro",
                    answers = listOf("Метро", "Вокзал", "Собор", "Заправка"), nameTheme = "Тема 2. Места", testTheme = "Тест 1"),
            //Тест 2. Места
            Question(text = "Отель",
                    answers = listOf("El hotel", "La casa", "El ayuntamiento", "El apartamento"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "Театр",
                    answers = listOf("El teatro", "La escuela", "El supermercado", "La casa"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "Больница",
                    answers = listOf("El hospital", "El museo", "El restaurante", "El parque"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "Супермаркет",
                    answers = listOf("El supermercado", "La cafetería", "El banco", "La escuela"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "Заправка",
                    answers = listOf("La gasolinera", "La biblioteca", "El ayuntamiento", "El banco"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "La cafetería",
                    answers = listOf("Кафе", "Метро", "Собор", "Школа"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "La estación",
                    answers = listOf("Вокзал", "Аэропорт", "Зоопарк", "Аптека"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "El banco",
                    answers = listOf("Банк", "Метро", "Заправка", "Отель"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "El mercado",
                    answers = listOf("Рынок", "Аптека", "Театр", "Вокзал"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            Question(text = "La catedral",
                    answers = listOf("Собор", "Центр города", "Магазин", "Заправка"), nameTheme = "Тема 2. Места", testTheme = "Тест 2"),
            //Тест 3. Места
            Question(text = "Вокзал",
                    answers = listOf("La estación", "El hotel", "El banco", "La gasolinera"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "Банк",
                    answers = listOf("El banco", "La escuela", "La catedral", "El mercado"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "Метро",
                    answers = listOf("El metro", "La biblioteca", "El ayuntamiento", "El restaurante"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "Полиция",
                    answers = listOf("La policía", "La universidad", "La estación", "La escuela"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "Город",
                    answers = listOf("La ciudad", "La casa", "La cafetería", ""), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "El hospital",
                    answers = listOf("Больница", "Полиция", "Банк", "Собор"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "El supermercado",
                    answers = listOf("Супермаркет", "Театр", "Аэропорт", "Площадь"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "El parque",
                    answers = listOf("Парк", "Магазин", "Город", "Кафе"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "La capital",
                    answers = listOf("Столица", "Вокзал", "Заправка", "Музей"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            Question(text = "La lavandería",
                    answers = listOf("Прачечная", "Школа", "Аптека", "Кафе"), nameTheme = "Тема 2. Места", testTheme = "Тест 3"),
            //Тема 3. Семья
            //Тест 1. Семья
            Question(text = "Родители",
                    answers = listOf("Los padres", "El hermano", "Los hijos", "Los abuelos"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "Брат",
                    answers = listOf("El hermano", "La hermana", "El hijo", "La madre"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "Сестра",
                    answers = listOf("La hermana", "El hermano", "La madre", "La hija"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "Дети",
                    answers = listOf("Los hijos", "Los padres", "Los abuelos", "El hermano"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "Мама",
                    answers = listOf("La madre", "El padre", "La hija", "La abuela"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "El padre",
                    answers = listOf("Папа", "Мама", "Сын", "Сестра"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "El hijo",
                    answers = listOf("Сын", "Дочь", "Сестра", "Брат"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "La hija",
                    answers = listOf("Дочь", "Сын", "Сестра", "Брат"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "La abuela",
                    answers = listOf("Бабушка", "Дедушка", "Брат", "Родители"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            Question(text = "El abuelo",
                    answers = listOf("Дедушка", "Бабушка", "Родители", "Брат"), nameTheme = "Тема 3. Семья", testTheme = "Тест 1"),
            //Тест 2. Семья
            Question(text = "Семья",
                    answers = listOf("La familia", "La hermana", "La hija", "La nieta"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "Внук",
                    answers = listOf("El nieto", "El tío", "El hijo", "El abuelo"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "Внучка",
                    answers = listOf("La nieta", "La familia", "La hija", "La abuela"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "Тётя",
                    answers = listOf("La tía", "La sobrina", "La hermanastra", "La hija"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "Дядя",
                    answers = listOf("El tío", "La tía", "El hijo", "La nieta"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "La prima",
                    answers = listOf("Двоюродная сестра", "Двоюродный брат", "Бабушка", "Сестра"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "El primo",
                    answers = listOf("Двоюродный брат", "Двоюродная сестра", "Внучка", "Сын"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "El sobrino",
                    answers = listOf("Племянник", "Племянница", "Тётя", "Дедушка"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "La sobrina",
                    answers = listOf("Племянница", "Племянник", "Семья", "Внучка"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            Question(text = "Yo",
                    answers = listOf("Я", "Ты", "Дядя", "Дочь"), nameTheme = "Тема 3. Семья", testTheme = "Тест 2"),
            //Тест 3. Семья
            Question(text = "Племянник",
                    answers = listOf("El sobrino", "La sobrina", "La prima", "El tío"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "Бабушка",
                    answers = listOf("La abuela", "La sobrina", "El padre", "La hermana"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "Сын",
                    answers = listOf("El hijo", "El tío", "El padre", "El sobrino"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "Сводный брат",
                    answers = listOf("El hermanastro", "La hermanastra", "La madre", "El tío"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "Отчим",
                    answers = listOf("El padrastro", "El primo", "El sobrino", "El abuelo"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "La familia",
                    answers = listOf("Семья", "Родители", "Сын", "Племянник"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "La madre",
                    answers = listOf("Мама", "Бабушка", "Внучка", "Дочь"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "Los padres",
                    answers = listOf("Родители", "Внук", "Мачеха", "Отчим"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "La hermanastra",
                    answers = listOf("Сводная сестра", "Сводный брат", "Дедушка", "Тётя"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            Question(text = "La madrastra",
                    answers = listOf("Мачеха", "Бабушка", "Семья", "Сестра"), nameTheme = "Тема 3. Семья", testTheme = "Тест 3"),
            //Тема 4. Дом
            //Тест 1. Дом
            Question(text = "Комната",
                    answers = listOf("La habitación", "La casa", "El dormitorio", "El salón"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "Кухня",
                    answers = listOf("La cocina", "La casa", "La habitación", "La ventana"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "Спальня",
                    answers = listOf("El dormitorio", "La cocina", "La habitación", "La casa"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "Кровать",
                    answers = listOf("La cama", "El armario", "La puerta", "La mesa"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "Шкаф",
                    answers = listOf("El armario", "La cama", "La mesa", "El dormitorio"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "La ventana",
                    answers = listOf("Окно", "Кресло", "Дверь", "Стол"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "La puerta",
                    answers = listOf("Дверь", "Окно", "Кровать", "Стул"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "El salón",
                    answers = listOf("Гостиная", "Спальня", "Кухня", "Кресло"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "La mesa",
                    answers = listOf("Стол", "Стул", "Окно", "Холодильник"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            Question(text = "La silla",
                    answers = listOf("Стул", "Стол", "Кровать", "Плита"), nameTheme = "Тема 4. Дом", testTheme = "Тест 1"),
            //Тест 2. Дом
            Question(text = "Коридор",
                    answers = listOf("El pasillo", "El comedor", "El horno", "La silla"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "Столовая",
                    answers = listOf("El comedor", "El despacho", "La lámpara", "La puerta"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "Кабинет",
                    answers = listOf("El despacho", "La mesa", "El salón", "El armario"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "Плита",
                    answers = listOf("El horno", "El baño", "El armario", "La puerta"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "Ванная комната",
                    answers = listOf("El baño", "El comedor", "La cama", "El dormitorio"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "El balcón",
                    answers = listOf("Балкон", "Кабинет", "Плита", "Стол"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "La lámpara",
                    answers = listOf("Лампа", "Диван", "Двор", "Кресло"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "El sofá",
                    answers = listOf("Диван", "Коридор", "Плита", "Стул"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "La televisión",
                    answers = listOf("Телевизор", "Окно", "Кухня", "Кровать"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            Question(text = "El jardín",
                    answers = listOf("Двор", "Кабинет", "Кухня", "Кровать"), nameTheme = "Тема 4. Дом", testTheme = "Тест 2"),
            //Тест 3. Дом
            Question(text = "Стул",
                    answers = listOf("La silla", "La mesa", "El sofá", "El comedor"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "Дверь",
                    answers = listOf("La puerta", "El salón", "El sofá", "La cama"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "Окно",
                    answers = listOf("La ventana", "El jardín", "La cocina", "La lámpara"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "Потолок",
                    answers = listOf("El techo", "La televisión", "El jardín", "El despacho"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "Ковёр",
                    answers = listOf("La alfombra", "La cocina", "El salón", "El horno"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "El baño",
                    answers = listOf("Ванная комната", "Ковёр", "Потолок", "Холодильник"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "El dormitorio",
                    answers = listOf("Спальня", "Коридор", "Ковёр", "Кухня"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "La cocina",
                    answers = listOf("Кухня", "Потолок", "Кабинет", "Коридор"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "El suelo",
                    answers = listOf("Пол", "Кухня", "Окно", "Холодильник"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            Question(text = "La cómoda",
                    answers = listOf("Комод", "Окно", "Кабинет", "Потолок"), nameTheme = "Тема 4. Дом", testTheme = "Тест 3"),
            //Тема 5. Погода и времена года
            //Тест 5. Погода и времена года
            Question(text = "Погода",
                    answers = listOf("El tiempo", "El banco", "La hija", "La puerta"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Жарко",
                    answers = listOf("Hace calor", "Hace frio", "Hace sol", "Llover"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Холодно",
                    answers = listOf("Hace frio", "Hace calor", "Nevar", "Hace sol"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Солнечно",
                    answers = listOf("Hace sol", "Hace frio", "Hace viento", "Tronar"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Ветрено",
                    answers = listOf("Hace viento", "Hace sol", "Hace calor", "Llover"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Llover",
                    answers = listOf("Идет дождь", "Ветрено", "Идет снег", "Гремит гром"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Nevar",
                    answers = listOf("Идет снег", "Гремит гром", "Жарко", "Идет дождь"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Tronar",
                    answers = listOf("Гремит гром", "Идет снег", "Ветрено", "Холодно"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Hace buen tiempo",
                    answers = listOf("Погода хорошая", "Погода плохая", "Жарко", "Холодно"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            Question(text = "Hace mal tiempo",
                    answers = listOf("Погода плохая", "Погода хорошая", "Солнечно", "Ветрено"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 1"),
            //Тест 2. Погода и времена года
            Question(text = "Лето",
                    answers = listOf("El verano", "La primavera", "El otoño", "Nevar"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "Весна",
                    answers = listOf("La primavera", "El otoño", "El verano", "El invierno"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "Зима",
                    answers = listOf("El invierno", "La primavera", "El sol", "Llover"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "Осень",
                    answers = listOf("El otoño", "El verano", "Tronar", "El tiempo"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "Прохладно",
                    answers = listOf("Hace fresco", "La primavera", "Hace frio", "Hace calor"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "El sol",
                    answers = listOf("Солнце", "Ветер", "Идет снег", "Жарко"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "La lluvia",
                    answers = listOf("Дождь", "Солнце", "Ветрено", "Солнечно"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "El viento",
                    answers = listOf("Ветер", "Солнце", "Снег", "Солнечно"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "La nieve",
                    answers = listOf("Снег", "Ветер", "Идет снег", "Ветрено"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            Question(text = "La niebla",
                    answers = listOf("Туман", "Снег", "Погода хорошая", "Жарко"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 2"),
            //Тест 3. Погода и времена года
            Question(text = "Солнце",
                    answers = listOf("El sol", "El viento", "La lluvia", "Llover"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "Ветер",
                    answers = listOf("El viento", "Tronar", "El sol", "El verano"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "Гремит гром",
                    answers = listOf("Tronar", "Hace viento", "Hace frio", "Llover"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "Ветрено",
                    answers = listOf("Hace viento", "Nevar", "El verano", "La nieve"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "Идет снег",
                    answers = listOf("Nevar", "El sol", "La nieve", "La primavera"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "El otoño",
                    answers = listOf("Осень", "Весна", "Погода", "Жарко"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "Hace fresco",
                    answers = listOf("Прохладно", "Солнце", "Снег", "Ветер"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "La primavera",
                    answers = listOf("Весна", "Осень", "Жарко", "Снег"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "El tiempo",
                    answers = listOf("Погода", "Жарко", "Дождь", "Снег"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            Question(text = "Hace calor",
                    answers = listOf("Жарко", "Снег", "Солнечно", "Дождь"), nameTheme = "Тема 5. Погода и времена года", testTheme = "Тест 3"),
            //Тема 6. Числительные
            //Тест 1. Числительные
            Question(text = "Один",
                    answers = listOf("Uno", "Seis", "Ocho", "Diez"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Два",
                    answers = listOf("Dos", "Tres", "Nueve", "Quince"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Три",
                    answers = listOf("Tres", "Veinte", "Setenta", "Treinta"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Четыре",
                    answers = listOf("Cuatro", "Catorce", "Trece", "Quince"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Пять",
                    answers = listOf("Cinco", "Once", "Dos", "Mil"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Seis",
                    answers = listOf("Шесть", "Семь", "Два", "Один"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Siete",
                    answers = listOf("Семь", "Шесть", "Пять", "Восемь"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Ocho",
                    answers = listOf("Восемь", "Девять", "Три", "Один"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Nueve",
                    answers = listOf("Девять", "Десять", "Два", "Восемь"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            Question(text = "Diez",
                    answers = listOf("Десять", "Девять", "Один", "Три"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 1"),
            //Тест 2. Числительные
            Question(text = "Одиннадцать",
                    answers = listOf("Once", "Doce", "Dieciséis", "Quince"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Двенадцать",
                    answers = listOf("Doce", "Trece", "Quince", "Catorce"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Тринадцать",
                    answers = listOf("Trece", "Quince", "Once", "Doce"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Четырнадцать",
                    answers = listOf("Catorce", "Doce", "Trece", "Dieciséis"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Пятнадцать",
                    answers = listOf("Quince", "Trece", "Catorce", "Dieciocho"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Dieciséis",
                    answers = listOf("Шестнадцать", "Пятнадцать", "Восемнадцать", "Девятнадцать"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Diecisiete",
                    answers = listOf("Семнадцать", "Пятнадцать", "Шестнадцать", "Тринадцать"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Dieciocho",
                    answers = listOf("Восемнадцать", "Девятнадцать", "Тринадцать", "Одиннадцать"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Diecinueve",
                    answers = listOf("Девятнадцать", "Пятнадцать", "Одиннадцать", "Двенадцать"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            Question(text = "Veinte",
                    answers = listOf("Двадцать", "Шестнадцать", "Восемнадцать", "Девятнадцать"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 2"),
            //Тест 3. Числительные
            Question(text = "Тридцать",
                    answers = listOf("Treinta", "Ochenta", "Mil", "Setenta"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Сорок",
                    answers = listOf("Cuarenta", "Setenta", "Sesenta", "Mil"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Пятьдесят",
                    answers = listOf("Cincuenta", "Ochenta", "Veinte", "Diecinueve"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Шестьдесят",
                    answers = listOf("Sesenta", "Setenta", "Ciento", "Ochenta"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Семьдесят",
                    answers = listOf("Setenta", "Sesenta", "Mil", "Ciento"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Ochenta",
                    answers = listOf("Восемьдесят", "Миллион", "Тринадцать", "Десять"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Noventa",
                    answers = listOf("Девяносто", "Шестьдесят", "Двенадцать", "Два"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Ciento",
                    answers = listOf("Сто", "Двенадцать", "Один", "Восемьдесят"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Mil",
                    answers = listOf("Тысяча", "Миллион", "Шестьдесят", "Тринадцать"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            Question(text = "Millón",
                    answers = listOf("Миллион", "Восемьдесят", "Десять", "Тысяча"), nameTheme = "Тема 6. Числительные", testTheme = "Тест 3"),
            //Тема 7. Продуктовый магазин
            //Тест 7. Продуктовый магазин
            Question(text = "Фрукты",
                    answers = listOf("La fruta", "La verdura", "La naranja", "El melocotón"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "Сахар",
                    answers = listOf("El azúcar", "El arroz", "La manzana", "El plátano"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "Молоко",
                    answers = listOf("La leche", "El agua", "El limón", "El melón"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "Хлеб",
                    answers = listOf("El pan", "El azúcar", "La manzana", "El melocotón"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "Вода",
                    answers = listOf("El agua", "La leche", "La manzana", "El limón"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "Las verduras",
                    answers = listOf("Овощи", "Фрукты", "Масло", "Чай"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "El arroz",
                    answers = listOf("Рис", "Хлеб", "Бутылка", "Мясо"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "El chocolate",
                    answers = listOf("Шоколад", "Овощи", "Конфета", "Чай"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "La sal",
                    answers = listOf("Соль", "Вода", "Сахар", "Перец"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            Question(text = "La carne",
                    answers = listOf("Мясо", "Сахар", "Масло", "Вода"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 1"),
            //Тест 2. Продуктовый магазин
            Question(text = "Паста",
                    answers = listOf("La pasta", "La sal", "La manzana", "El agua"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "Чай",
                    answers = listOf("El té", "El agua", "El chocolate", "El arroz"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "Яблоко",
                    answers = listOf("La manzana", "La carne", "El pan", "El pescado"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "Яйца",
                    answers = listOf("Los huevos", "La naranja", "La patata", "El pollo"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "Рыба",
                    answers = listOf("El pescado", "La pasta", "El jamón", "El limón"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "La naranja",
                    answers = listOf("Апельсин", "Яйца", "Соль", "Перец"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "El jamón",
                    answers = listOf("Ветчина", "Перец", "Овощи", "Чай"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "La patata",
                    answers = listOf("Картофель", "Яйца", "Чай", "Масло"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "El limón",
                    answers = listOf("Лимон", "Соль", "Овощи", "Фрукты"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            Question(text = "El pollo",
                    answers = listOf("Курица", "Фрукты", "Мясо", "Масло"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 2"),
            //Тест 3. Продуктовый магазин
            Question(text = "Мясо",
                    answers = listOf("La carne", "La naranja", "Las verduras", "El agua"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "Картофель",
                    answers = listOf("La patata", "El arroz", "La sal", "Las verduras"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "Шоколад",
                    answers = listOf("El chocolate", "La naranja", "El agua", "La patata"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "Банан",
                    answers = listOf("El plátano", "El arroz", "El agua", "La carne"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "Aрбуз",
                    answers = listOf("La sandía", "La sal", "La patata", "La carne"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "El azúcar",
                    answers = listOf("Сахар", "Aрбуз", "Рыба", "Паста"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "El pan",
                    answers = listOf("Хлеб", "Рыба", "Паста", "Молоко"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "La fruta",
                    answers = listOf("Фрукты", "Aрбуз", "Молоко", "Мясо"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "El melón",
                    answers = listOf("Дыня", "Паста", "Фрукты", "Мясо"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            Question(text = "El yogur",
                    answers = listOf("Йогурт", "Молоко", "Фрукты", "Мясо"), nameTheme = "Тема 7. Продуктовый магазин", testTheme = "Тест 3"),
            //Тема 8. Дни недели и месяцы года
            //Тест 8. Дни недели и месяцы года
            Question(text = "Понедельник",
                    answers = listOf("Lunes", "Enero", "El año", "La semana"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Вторник",
                    answers = listOf("Martes", "Marzo", "Sábado", "Domingo"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Среда",
                    answers = listOf("Miércoles", "El mes", "Lunes", "La semana"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Четверг",
                    answers = listOf("Jueves", "Martes", "El año", "Viernes"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Неделя",
                    answers = listOf("La semana", "El año", "El mes", "El día"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "El día",
                    answers = listOf("День", "Неделя", "Год", "Месяц"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Viernes",
                    answers = listOf("Пятница", "Среда", "Четверг", "День"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Sábado",
                    answers = listOf("Суббота", "Неделя", "Понедельник", "Год"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Domingo",
                    answers = listOf("Воскресенье", "Вторник", "Среда", "Неделя"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            Question(text = "Fin de semana",
                    answers = listOf("Выходные", "Воскресенье", "Неделя", "Год"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 1"),
            //Тест 2. Дни недели и месяцы года
            Question(text = "Январь",
                    answers = listOf("Enero", "Febrero", "Abril", "Mayo"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Февраль",
                    answers = listOf("Febrero", "Enero", "Marzo", "Julio"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Март",
                    answers = listOf("Marzo", "Abril", "Octubre", "Mayo"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Апрель",
                    answers = listOf("Abril", "Marzo", "Julio", "Mayo"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Май",
                    answers = listOf("Mayo", "Abril", "Marzo", "Julio"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Junio",
                    answers = listOf("Июнь", "Май", "Июль", "Август"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Julio",
                    answers = listOf("Июль", "Июнь", "Октябрь", "Март"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Agosto",
                    answers = listOf("Август", "Месяц", "Март", "Ноябрь"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Septiembre",
                    answers = listOf("Сентябрь", "Март", "Декабрь", "Ноябрь"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            Question(text = "Octubre",
                    answers = listOf("Октябрь", "Февраль", "Ноябрь", "Декабрь"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 2"),
            //Тест 3. Дни недели и месяцы года
            Question(text = "Ноябрь",
                    answers = listOf("Noviembre", "Septiembre", "Agosto", "Sábado"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Декабрь",
                    answers = listOf("Diciembre", "Julio", "Septiembre", "Octubre"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Месяц",
                    answers = listOf("El mes", "El día", "Agosto", "La semana"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Суббота",
                    answers = listOf("Sábado", "Viernes", "El mes", "Junio"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Четверг",
                    answers = listOf("Jueves", "Agosto", "Sábado", "El mes"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "El año",
                    answers = listOf("Год", "Месяц", "Май", "День"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Lunes",
                    answers = listOf("Понедельник", "Вторник", "Среда", "Лето"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Abril",
                    answers = listOf("Апрель", "Январь", "Май", "Август"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Marzo",
                    answers = listOf("Март", "Май", "Вторник", "Апрель"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
            Question(text = "Miércoles",
                    answers = listOf("Среда", "Понедельник", "Год", "Ноябрь"), nameTheme = "Тема 8. Дни недели и месяцы года", testTheme = "Тест 3"),
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
