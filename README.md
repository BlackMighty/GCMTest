# GCMTest
generate java code Создать приложение (java 8, spring boot, maven/gradle)
generate java code Создать приложение (java 8, spring boot, maven/gradle), отвечающее на два REST запроса:
1. Анализ строки (/api/analyze/{input_string})
На вход анализатора поступает строка для анализа
На выходе (JSON) — информация по каждому встреченному в анализируемой строке символу. По два числа на символ:
количество символов в анализируемой строке
длина максимальной непрерывной последовательности из этого символа.
2. Статистика по запросам на анализ, обработанным приложением с момента его запуска (/api/statistics)
На выходе (JSON) — информация по каждому символу, который встречался в запросах на анализ. По три числа на символ:
в скольких запросах встретился
среднее количество символов в строке (для строк, в которых символ встречался)
средняя длина максимальной непрерывной последовательности из этого символа (для строк, в которых символ встречался).
Сбор статистики осуществляется в период жизни приложения, то есть никакого хранения данных на диске реализовать не нужно.
При сборе статистики надо учитывать, что запросы на анализ будут приходить одновременно.
