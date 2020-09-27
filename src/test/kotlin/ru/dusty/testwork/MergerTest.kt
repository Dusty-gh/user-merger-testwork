package ru.dusty.testwork

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * Created by vpylin on 27.09.2020.
 */
internal class MergerTest {

    private lateinit var merger: Merger

    @BeforeEach
    fun setUp() {
        merger = Merger()
    }

    @Test
    fun mergeEmpty() {
        assert(merger.merge().isEmpty())
    }

    @Test
    fun mergeEmptyUsersEmails() {
        merger.add("", emptyList())
        merger.add("user1", emptyList())
        merger.add("user2", emptyList())

        assertFalse(checkIntersections(merger.merge()))
    }

    @Test
    fun mergeDouble() {
        merger.add("user1", listOf("email1", "email2"))
        merger.add("user1", listOf("email3", "email4"))
        merger.add("user2", listOf("email1"))
        merger.add("user3", listOf("email4", "email5", "email4"))
        merger.add("user2", listOf("email2"))

        assertFalse(checkIntersections(merger.merge()))
    }

    @Test
    fun mergeDefaultExample() {
        merger.add("user1", listOf("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"))
        merger.add("user2", listOf("foo@gmail.com", "ups@pisem.net"))
        merger.add("user3", listOf("xyz@pisem.net", "vasya@pupkin.com"))
        merger.add("user4", listOf("ups@pisem.net", "aaa@bbb.ru"))
        merger.add("user5", listOf("xyz@pisem.net"))

        val merge = merger.merge()
        assert(merge.size == 2)
        assertFalse(checkIntersections(merge))
    }

    @Test
    fun mergeRandom() {
        generateUsersAndEmails(200, 1000).forEach { (user, emails) -> merger.add(user, emails) }

        assertFalse(checkIntersections(merger.merge()))
    }

    /**
     * Проверяет наличие пересечения в списках email-ов пользователей
     */
    private fun checkIntersections(map: Map<String, Set<String>>): Boolean {
        for ((currentUser, currentEmails) in map) {
            map.filterKeys { it != currentUser }.forEach {
                val anotherEmails = it.value
                if (currentEmails.intersect(anotherEmails).isNotEmpty()) return true
            }
        }

        return false
    }

    private fun generateUsersAndEmails(usersCount: Int, maxEmailsCount: Int): List<Pair<String, Collection<String>>> {
        return (1..usersCount).map {
            val emails = (1..(1..maxEmailsCount).random()).map { "email${(1..maxEmailsCount).random()}" }.toList()
            Pair("user$it", emails)
        }
    }
}