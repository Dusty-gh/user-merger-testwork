package ru.dusty.testwork

const val USER_EMAIL_DELIMITER = "->"

fun main() {
    val merger: MergerIf = Merger()

    val inputLines = generateSequence(::readLine).takeWhile(String::isNotEmpty)
    inputLines.filter { it.contains(USER_EMAIL_DELIMITER) }.forEach {
        val (name, emails) = it.split(USER_EMAIL_DELIMITER).map(String::trim)
        merger.add(name, emails.split(",").map(String::trim))
    }

    merger.merge().forEach {
        println("${it.key} -> ${it.value.joinToString()}")
    }
}