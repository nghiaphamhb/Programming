<H1>Лабораторная работа #7</H1>
Введите вариант: 78171

<H2>Внимание! У разных вариантов разный текст задания!

Доработать программу из лабораторной работы №6 следующим образом:</H2>

1)Организовать хранение коллекции в реляционной СУБД (PostgresQL). Убрать хранение коллекции в файле.

2)Для генерации поля id использовать средства базы данных (sequence).

3)Обновлять состояние коллекции в памяти только при успешном добавлении объекта в БД

4)Все команды получения данных должны работать с коллекцией в памяти, а не в БД

5)Организовать возможность регистрации и авторизации пользователей. У пользователя есть возможность указать пароль.

6)Пароли при хранении хэшировать алгоритмом SHA-512

7)Запретить выполнение команд не авторизованным пользователям.

8)При хранении объектов сохранять информацию о пользователе, который создал этот объект.

9)Пользователи должны иметь возможность просмотра всех объектов коллекции, но модифицировать могут только принадлежащие им.

10)Для идентификации пользователя отправлять логин и пароль с каждым запросом.


<H2>Необходимо реализовать многопоточную обработку запросов.</H2>

1)Для многопоточного чтения запросов использовать Fixed thread pool

2)Для многопотчной обработки полученного запроса использовать Fixed thread pool

3)Для многопоточной отправки ответа использовать Cached thread pool

4)Для синхронизации доступа к коллекции использовать потокобезопасные аналоги коллекции из java.util.concurrent

<H2>Порядок выполнения работы:</H2>

1)В качестве базы данных использовать PostgreSQL.

2)Для подключения к БД на кафедральном сервере использовать хост pg, имя базы данных - studs, имя пользователя/пароль совпадают с таковыми для подключения к серверу.

<H3>Дополнительные задания:</H3>

Доп Модель ролей

Design role permissions

https://www.nikhilajain.com/post/user-role-permission-model

1) Есть несколько ролей - одна из них админ

2) При регистрации  по дефолту пользователь имеет самую низкую роль(то есть там где меньше всего функциональности) 

3) пользователь, который имеет роль администратора, имеет доступ ко всем пользователям, и может им менять роли

4) У администратора отдельные возможности - он способен только работать с функциональность и ролями других пользователей - то есть по дефотлу изначально в базе данных существует минимум один администратор, когда он заходит от клиентской части у него должна быть доступна команды: 

1. посмотреть всех пользователей и их роли

2. изменить роль пользователя

3. добавить к роли новые функциональности

5) есть роли и есть функциональности 

Под функциональностями можно подразумевать например создание, удаление, просмотр, обновление и тд. У роли может быть набор функциональностей 

Допустим вы можете сделать роль юзер-джуниор и прикрепить к ней функциональность только на просмотра юзер-тимлид будет иметь все функциональности