# Book-of-Recipes-on-Spring-Boot
<h3>Описание</h3>
<div>
Данная программа предстваляет из себя приложение-книга на Java Spring Boot для автоматизации учета рецептов.
</div>
    <h3>Пользователь имеет возможность:</h3>
  <ul>
  <li>Зарегистрироваться(Это первое действие которое он должен сделать чтобы получить доступ ко всем остальным)</li>
  <li>добавить и удалить рецепты из книги;</li>
  <li>обновить конкретный рецепт по его id;</li>
  <li>получить рецепт по id;</li>
 </ul>
    Внешний интерфейс приложения представлен в виде HTTP API (REST).
    <h3>Список URL HTTP-методов:</h3>
    <h4>    POST /api/register</h4>
    Получает объект JSON с двумя полями: email(строка) и password(строка). Если пользователя с указанным адресом электронной почты не существует, программа сохраняет (регистрирует) пользователя в базе данных и отправляет ответ 200 (Ok). Если пользователь уже находится в базе данных, отвечает 400 (Bad Request)кодом состояния. Оба поля необходимы и должны быть действительными : email должен содержать "@" и "." символы, password должен содержать не менее 8 символов. Они не должны быть пустыми. Если поля не соответствуют этим ограничениям, программа  отвечает 400 (Bad Request).
<h4>    POST /api/recipe/new</h4>
    Добавляет новый рецепт. Получать рецепт, как объект JSON и возвращает объект JSON с одним id полем; Пример рецепта:
    <p><code>
    {<Br>
   "name": "Fresh Mia Tea",<Br>
   "category": "beverage",<Br>
   "description": "Light, aromatic and refreshing beverage, ...",<Br>
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],<Br>
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]<Br>
}<Br>
    </code></p>
