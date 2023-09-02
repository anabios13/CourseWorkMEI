Тестовый билд сервиса
Раздача статики по  url localhost:8080/resources/start_page.html
умеет принимать get запросы и возвращать ответ в виде json
пример запроса:
GET http://localhost:8080/profile/show/1  - здесь возвращается информация о профиле пользователя с id=1
пример ответа:

<ProfileDTO>
<personDTO>
<personName>testName</personName>
<personAvatar/>
<personRole>USER_ROLE</personRole>
</personDTO>
<noteDTOList>
<noteDTOList>
<noteText>test note text</noteText>
<timeOfNoteCreation>2023-04-14T21:04:55.000+00:00</timeOfNoteCreation>
</noteDTOList>
</noteDTOList>
<directoryDTOList>
<directoryDTOList>
<directoryName>testDirectoryName</directoryName>
<directoryIsVisible/>
<directoryIsFavorite/>
</directoryDTOList>
</directoryDTOList>
</ProfileDTO>


Конфигурация для создания бд находится по пути:
WebServiceForManage\src\main\resources\application.properties

конфигурация для заполнени бд находится по пути:
WebServiceForManage\DBSQLRequests.txt


