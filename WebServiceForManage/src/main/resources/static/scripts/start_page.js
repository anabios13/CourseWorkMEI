// функция для создания диалогового окна (заголовок окна, сообщение окна, показывать ли поле ввода, текст на кнопке с положительным ответом, текст на кнопке с отрицательным ответом)
function openDilogueWindow(headText, messageText, inputShow, agreeButtonText, disagreeButtonText){
    let dilogueBackground = document.createElement("div");
    dilogueBackground.className = "dialogue-background"
    
    let dialogueWindow = document.createElement("div");
    dialogueWindow.className = "dialogue-window";

    let head = document.createElement("h2");
    head.className = "head";
    head.textContent = headText;


    let message = document.createElement("p");
    message.className = "message";
    message.textContent = messageText;

    let buttons = document.createElement("div");
    buttons.className = "buttons";

    let disagreeButton = document.createElement("button");
    disagreeButton.className = "disagree";
    disagreeButton.textContent = disagreeButtonText;
    disagreeButton.addEventListener('click',() => closeDialogueWindow());

    let agreeButton = document.createElement("button");
    agreeButton.className = "agree";
    agreeButton.textContent = agreeButtonText;

    buttons.appendChild(disagreeButton);
    buttons.appendChild(agreeButton);

    dialogueWindow.appendChild(head);
    dialogueWindow.appendChild(message);
    dialogueWindow.appendChild(buttons);

    dilogueBackground.appendChild(dialogueWindow);

    document.body.append(dilogueBackground);


}

var id = 0;

function getId(){
    id += 1;
    return id;
}

function setPreventId(){
    id -= 1;
    return id;
}

document.querySelector('.exit-icon').addEventListener('click', () => openDilogueWindow("Выход", "Вы хотите выйти?", false, "Ага", "Не-а"))

function closeDialogueWindow() {
    document.querySelector('.dialogue-background').remove();
}

function editNote() {
    document.querySelector("#note-view-head").className = "search-block";
    document.querySelector("#notes").className = "notes";

    document.querySelector("#" + editedNoteId).innerText = document.querySelector(".note-editor").value;
}

function toggleNoteViewWindow(){
    document.querySelector("#main_page-block-list-notes").classList.toggle("disabled");
}

function toggleNoteAddingWindow(){
    document.querySelector("#note-add-block").classList.toggle("disabled");
}

var lastNoteText = "";
var editedNoteId = "";

function showNoteEditingWindow(event) {
    console.log(event);
if(event["target"]["localName"] != "img"){
    toggleNoteViewWindow();
    toggleNoteAddingWindow();

    editedNoteId = event["target"]["id"];
    console.log(event)
    let note = document.querySelector("#" + editedNoteId);

    document.querySelector(".note-editor").value = note.innerText;
    lastNoteText = note.innerText;
}
}

function showNoteAddingWindow(){
    toggleNoteViewWindow();
    toggleNoteAddingWindow();

    document.querySelector(".note-editor").value = "";
}

function addNote(){
    if(lastNoteText != document.querySelector(".note-editor").value){
        try {
            document.querySelector("#" + editedNoteId).parentElement.remove();
        } catch (error) {
            console.log("Ничего");
        }

        let text = document.createElement("p");
        text.innerText = document.querySelector(".note-editor").value;
        text.id = "n" + getId();

        let deleteImgButton = document.createElement("img");
        deleteImgButton.src = "cross-circle.svg";
        deleteImgButton.className = "round-button";
        deleteImgButton.addEventListener('click',() => deleteNote(event));

        let note = document.createElement("div");

        note.append(text);
        note.append(deleteImgButton)

        note.addEventListener('click',() => showNoteEditingWindow(event));

        document.querySelector(".list-notes").prepend(note);

        document.querySelector(".note-editor").value = "";
    }

    toggleNoteViewWindow();
    toggleNoteAddingWindow();
}

function deleteNote(event){
    event["target"].parentElement.remove();
}


// КАТАЛОГИ

function openCatalog(event){
    let catalogName = "";
    if(event.target.id != ""){
        console.log(event.target.id);
        catalogName = event.target.querySelector(".catalog-name").textContent;
    }

    if(event.target.className == "catalog-name"){
        catalogName = event.target.textContent;
        console.log(catalogName);
    }

    if(catalogName != ""){
        document.querySelector("#start_page").classList.toggle("disabled");
        document.querySelector("#projects_page").classList.toggle("disabled");
        document.querySelector("#catalog-name").textContent = catalogName;
    }
}

function deleteCatalog(event){
    console.log(event["target"].parentElement.parentElement.id);
    document.querySelector("#" + event["target"].parentElement.parentElement.id).remove();
}

function editCatalog(event){
    let id = event.target.parentElement.parentElement.id;

    document.querySelector("#" + id + " p").classList.toggle("disabled");
    event.target.src="disk.svg";

    let textArea  = document.createElement("textarea");
    textArea.className = "catalog-editor catalog-name";
    textArea.value = document.querySelector("#" + id + " p").textContent;

    event.target.removeEventListener("click", editCatalog);
    event.target.addEventListener('click', saveCatalogName);

    document.querySelector("#" + id).prepend(textArea);
    document.querySelector("#" + id + " img.icon:nth-child(3)").before(saveCatalogName);
}

function saveCatalogName(event){
    let id = event.target.parentElement.parentElement.id;

    let catalogName = document.querySelector("#" + id + " p");
    let textArea = document.querySelector("#" + id + " textarea");

    catalogName.classList.toggle("disabled");
    catalogName.textContent = textArea.value;
    textArea.remove();

    event.target.src="pencil.svg";
    event.target.removeEventListener("click", saveCatalogName);
    event.target.addEventListener('click', editCatalog);
}

function addFavoritCatalog(event){
    let id = event.target.parentElement.parentElement.id;
    let catalog = document.querySelector("#" + id);

    event.target.src="yellow-star.svg";
    event.target.removeEventListener("click", addFavoritCatalog);
    event.target.addEventListener('click', removeFavoritCatalog);

    document.querySelector("#favorit-catalogs").prepend(catalog);
}

function removeFavoritCatalog(event){
    let id = event.target.parentElement.parentElement.id;
    let catalog = document.querySelector("#" + id);

    event.target.src="star.svg";
    event.target.removeEventListener("click", removeFavoritCatalog);
    event.target.addEventListener('click', addFavoritCatalog);

    document.querySelector("#other-catalogs").prepend(catalog);
}


function startCatalogSharing(event){
    let projectid = event.target.parentElement.parentElement.id;
    console.log('Каталог стал доступен всем ' + projectid);
    event.target.src = "eye-on.svg";

    event.target.removeEventListener("click", startCatalogSharing);
    event.target.addEventListener('click', stopCatalogSharing);
}

function stopCatalogSharing(event){
    let projectid = event.target.parentElement.parentElement.id;
    console.log('Каталог стал приватным ' + projectid);
    event.target.src = "eye-off.svg";

    event.target.removeEventListener("click", stopCatalogSharing);
    event.target.addEventListener('click', startCatalogSharing);
}

function getCatalogs(userId){
    //Выполняется запрос

    let result = '{"catalogs": [ {"catalogName": "Имя проекта 1", "isFavorit": false, "isShared": true},{"catalogName": "Имя проекта 2", "isFavorit": true, "isShared": true}, {"catalogName": "Имя проекта 3", "isFavorit": false, "isShared": false},{"catalogName": "Имя проекта 4", "isFavorit": false, "isShared": false}]}';
    let json = JSON.parse(result);
    console.log(json);

    json['catalogs'].forEach(element => appendCatalog(element));

}

function appendCatalog(element){
    let id = "c" + getId();
    let html = '<div id="' + id +'" onclick="openCatalog(event);" class="catalog">'
    + '<p class="catalog-name">' + element["catalogName"] + '</p>'
    + '<div class="catalog-actions">'
        + '<img src="' + ((element['shared']) ? "eye-on.svg" : "eye-off.svg") + '" class="icon">'
        + '<img src="' + ((element["isFavorit"]) ? "yellow-star.svg": "star.svg") + '" class="icon">'
        + '<img src="pencil.svg" class="icon">'
        + '<img  onclick="deleteCatalog(event);" src="cross-circle.svg" class="icon">'
    + '</div>'
+ '</div>';

if(element["isFavorit"]){
    document.querySelector("#favorit-catalogs").insertAdjacentHTML("beforeend", html);
    document.querySelector("#" + id + "> div:nth-child(2) > img:nth-child(2)").addEventListener("click", removeFavoritCatalog);
}else{
    document.querySelector("#other-catalogs").insertAdjacentHTML("beforeend", html);
    document.querySelector("#" + id + "> div:nth-child(2) > img:nth-child(2)").addEventListener("click", addFavoritCatalog);
}

if(element['shared']){
    document.querySelector("#" + id + "> div:nth-child(2) > img:nth-child(1)").addEventListener("click", stopCatalogSharing);
   }else{
    document.querySelector("#" + id + "> div:nth-child(2) > img:nth-child(1)").addEventListener("click", startCatalogSharing);
}

    document.querySelector("#" + id + "> div:nth-child(2) > img:nth-child(3)").addEventListener("click", editCatalog);
}

getCatalogs(1);

function addCatalog(event){
    let id = "c" + getId()
    let html = '<div id="' + id + '" onclick="openCatalog(event);" class="catalog new-catalog">'
    + '<textarea class="catalog-editor catalog-name">Новый каталог</textarea>'
    + '<div class="catalog-actions">'
        + '<img src="disk.svg" class="icon">'
        + '<img  onclick="deleteCatalog(event);" src="cross-circle.svg" class="icon">'
    + '</div>'
    + '</div>';

    document.querySelector(".catalogs-list").insertAdjacentHTML("beforeBegin",html);

    document.querySelector("#" + id + "> div:nth-child(2) > img:nth-child(1)").addEventListener("click", saveNewCatalog);

}

function saveNewCatalog(event){
    let newCatalog = event.target.parentElement.parentElement;
    let name = document.querySelector("#" + newCatalog.id + " textarea").value;
    console.log(name);

    newCatalog.remove();

    setPreventId()
    appendCatalog('{"catalogName": "' + name + '", "isFavorit": false, "isShared": false}');
}