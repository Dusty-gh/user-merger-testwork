## **Тестовое задание**
Требуется написать работающий код, решающий задачу, и приложить
инструкцию, как код собрать и запустить.
Также надо написать unittest-ы и сделать возможность получать входные
данные для задачи из stdin в формате как в примере ниже.
Задачу реализовать на Kotlin(Java).

Имеется n пользователей, каждому из них соответствует список email-ов
(всего у всех пользователей m email-ов).
Например:
> user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru <br/>
> user2 -> foo@gmail.com, ups@pisem.net <br/>
> user3 -> xyz@pisem.net, vasya@pupkin.com <br/>
> user4 -> ups@pisem.net, aaa@bbb.ru <br/>
> user5 -> xyz@pisem.net

Считается, что если у двух пользователей есть общий email, значит это один
и тот же пользователь. Требуется построить
и реализовать алгоритм, выполняющий слияние пользователей. На выходе
должен быть список пользователей с их email-ами (такой же как на входе).
В качестве имени объединенного пользователя можно брать любое из исходных
имен. Список email-ов пользователя должен содержать только уникальные
email-ы. Параметры n и m произвольные, длина конкретного списка email-ов никак не
ограничена.

Требуется, чтобы асимптотическое время работы полученного решения было
линейным, или близким к линейному.

Возможный ответ на задачу в указанном примере:
> user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru, ups@pisem.net, aaa@bbb.ru <br/>
> user3 -> xyz@pisem.net, vasya@pupkin.com

Алгоритм следует реализовать в виде реализации следующего Java интерфейса:
```java
package ru.intterra.test;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
public interface MergerIf {
/**
* Обработать следующую строку: user -> [email1,email2]
*/
void add(String name, Collection<String> emails);
/**
* Получить результат работы алгоритма
*/
Map<String, Set<String>> merge();
}
```

## **Сборка и запуск**
```sh
mvn clean package exec:java < exampleData.txt
```
Для запуска самого jar-файла:
```sh
java -jar target/userMerger-1.0-jar-with-dependencies.jar
```

Для завершения ручного ввода данных потребуется ввести пустую строку.