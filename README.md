# AirportApp
Мой очередной pet-проект, который реализует локальное хранение данных о пользователе при помощи Room DAO. Также реализована модель MVVM. При введении поискового запроса выдаются все возможные аэропотры, подходящие под введённый код или описание. При нажатии на аэропорт, открывается список доступных из этого аэропорта рейсов. Можно добавить рейс в избранные и он будет храниться на главном экране пользователя. Все запросы к базе данных выполняются при помощи корутин во viewModelScope. Далее результат запроса попдает во Flow, а впоследствии инвертируется в StateFlow, UI элементы "подписываются" на обновление потока, что помогает приложению вовремя отображать текущие данные.

Структуры базы данных:

Entity "airport_items": --> id: Int --> iata_description: String, --> iata_departure: String, --> name_departure: String, --> name_destination: String, --> passengers: Int

Entity "favourites_items": --> id: Int, --> iata_description: String, --> iata_departure: String,
