package ru.dusty.testwork

/**
 * Created by vpylin on 27.09.2020.
 */
class Merger: MergerIf {

    /**
     * Соответствие пользователей с email-ами
     */
    private val usersMap: MutableMap<String, Collection<String>> = hashMapOf()

    /**
     * Соответствие email-ов с пользователями
     */
    private val emailsMap: MutableMap<String, MutableCollection<String>> = hashMapOf()

    override fun add(name: String, emails: Collection<String>) {
        usersMap[name] = emails

        emails.forEach {
            emailsMap[it] = emailsMap.getOrDefault(it, linkedSetOf()).with(name)
        }
    }

    override fun merge(): Map<String, Set<String>> {
        val result: HashMap<String, Set<String>> = hashMapOf()

        // поиск в глубину (пользователи и email-ы - вершины графа)
        val usedUsers: MutableSet<String> = linkedSetOf()
        usersMap.keys.forEach {
            if (!usedUsers.contains(it)) {
                val resultEmails: MutableSet<String> = linkedSetOf()
                visitUserNode(it, usedUsers, resultEmails)
                result[it] = resultEmails
            }
        }

        return result
    }

    private fun visitUserNode(user: String, usedUsers: MutableSet<String>, resultEmails: MutableSet<String>) {
        usedUsers.add(user)

        usersMap[user]?.forEach {
            if (!resultEmails.contains(it)) {
                visitEmailNode(it, usedUsers, resultEmails)
            }
        }
    }

    private fun visitEmailNode(email: String, usedUsers: MutableSet<String>, resultEmails: MutableSet<String>) {
        resultEmails.add(email)

        emailsMap[email]?.forEach {
            if (!usedUsers.contains(it)) {
                visitUserNode(it, usedUsers, resultEmails)
            }
        }
    }
}

private fun <E> MutableCollection<E>.with(value: E): MutableCollection<E> {
    add(value)
    return this
}
