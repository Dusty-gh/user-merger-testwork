package ru.dusty.testwork

/**
 * Created by vpylin on 27.09.2020.
 */
interface MergerIf {

    /**
     * Обработать следующую строку: user -> [email1,email2]
     */
    fun add(name: String, emails: Collection<String>)

    /**
     * Получить результат работы алгоритма
     */
    fun merge(): Map<String, Set<String>>
}