## Вспомогательная информация для работы над ДЗ

### Порядок сдачи
1. Работайте в ветке main, Pull Request создан автоматически
2. Для сдачи нужно запушить изменения в ветку main.
3. Заполните гугл-форму и приложите ссылку на Pull Request

## JDK

Для выполнения задания требуется [JDK 21+](https://www.oracle.com/java/technologies/downloads/)

Для проверки установки воспользуйтесь
```
java -version
```
Также убедитесь, что Java добавлена в системную переменную среды *PATH*

## Сборка проекта
### Windows
```
mvnw.cmd clean package
```
### Linux / MacOS
```
./mvnw clean package
```
После выполнения зависимости будут автоматически установлены, результат появится в консоли. Собранный jar-файл будет находиться в папке target

## Запуск программы без аргументов
### Windows:
```
mvnw.cmd exec:java -Dexec.mainClass=ru.dstu.labyrinths.Main
```
### Linux / MacOS:
```
./mvnw exec:java -Dexec.mainClass=ru.dstu.labyrinths.Main
```
Также можно запускать через jar:
```
java -jar target/labyrinths-1.0-SNAPSHOT.jar
```
(название файла может отличаться)

## Запуск программы с аргументами
### Формат запуска:
```
<algorithm> <input_file> <output_file> <height> <width>
```
### Пример:
```
java -jar target/labyrinths-1.0-SNAPSHOT.jar astar maze.txt solved.txt 20 30
```
Где:
* _algorithm_ — алгоритм (dijkstra или astar)
* _input_file_ — путь к входному файлу
* _output_file_ — путь к выходному файлу
* _height_ — высота лабиринта
* _width_ — ширина лабиринта

## Запуск тестов
### Windows
```
mvnw.cmd test
```
### Linux / MacOS
```
./mvnw test
```
После выполнения результат будет выведен в консоль

## Важно

Редактировать тесты запрещено — в этом случае решение не будет засчитано

Файлы mvnw, mvnw.cmd и папку .mvn удалять нельзя

Допускается добавление собственных файлов в .gitignore, но существующие записи лучше не изменять