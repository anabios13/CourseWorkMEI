function getProjects(userId){
    //  Выполняется запрос

    let result = '{"favoritProjects" : [ {"progectName": "Имя проекта 1","shared" : false},{"progectName": "Имя проекта 2","shared" : true}], "otherProjects" : [{"progectName": "Имя проекта 3","shared" : false},{"progectName": "Имя проекта 4","shared" : true}]}'
    let json = JSON.parse(result);
    console.log(json);
    
    json["favoritProjects"].forEach(element => appendProject(element, "projects_favorit-projects"));
    json["otherProjects"].forEach(element => appendProject(element, "projects_other-projects"));

}

var projectid = 0;

function getId(){
    projectid += 1;
    return projectid;
}

function appendProject(element, parentId){
    let parent = document.querySelector("#" + parentId);
    let projectId = getId();

    let html ='<div id="p' + projectId + '" onclick="openProject(event);" class="project">'
    + '<p class="catalog-name">' + element['progectName'] + '</p>'
     + '<div class="catalog-actions">'
         + '<img src="/static/' + ((element['shared']) ? "eye-on.svg" : "eye-off.svg") + '" class="icon">'
         + '<img  src="/static/' + ((parentId == "projects_favorit-projects") ? "yellow-star.svg" : "star.svg") + '" class="icon">'
         + '<img src="/static/pencil.svg" class="icon">'
         + '<img  onclick="deleteCatalog(event);" src="/static/cross-circle.svg" class="icon">'
     + '</div>'
 + '</div>'
    parent.insertAdjacentHTML('beforeend', html)

   if(parentId == "projects_favorit-projects"){
    document.querySelector("#p" + projectId + "> div:nth-child(2) > img:nth-child(2)").addEventListener("click", removeFavoritProject);
   }else{
    document.querySelector("#p" + projectId + "> div:nth-child(2) > img:nth-child(2)").addEventListener("click", addFavoritProject);
   }

   if(element['shared']){
    document.querySelector("#p" + projectId + "> div:nth-child(2) > img:nth-child(1)").addEventListener("click", stopProjectSharing);
   }else{
    document.querySelector("#p" + projectId + "> div:nth-child(2) > img:nth-child(1)").addEventListener("click", startProjectSharing);
   }

   document.querySelector("#p" + projectId + "> div:nth-child(2) > img:nth-child(3)").addEventListener("click", editProject);
}

getProjects(1);

function addFavoritProject(event){
    let id = event.target.parentElement.parentElement.id;
    let catalog = document.querySelector("#" + id);

    catalog.querySelector("img.icon:nth-child(2)").src="/static/yellow-star.svg";
    event.target.removeEventListener("click", addFavoritProject);
    event.target.addEventListener('click', removeFavoritProject);

    document.querySelector("#projects_favorit-projects").prepend(catalog);
    return;
}

function removeFavoritProject(event){
    let id = event.target.parentElement.parentElement.id;
    let catalog = document.querySelector("#" + id);

    catalog.querySelector("img.icon:nth-child(2)").src="/static/star.svg";

    event.target.removeEventListener("click", removeFavoritProject);
    event.target.addEventListener('click', addFavoritProject);

    document.querySelector("#projects_other-projects").after(catalog);
    return;
}

function startProjectSharing(event){
    let projectid = event.target.parentElement.parentElement.id;
    console.log('Проект стал доступен всем ' + projectid);
    event.target.src = "/static/eye-on.svg";

    event.target.removeEventListener("click", startProjectSharing);
    event.target.addEventListener('click', stopProjectSharing);
}

function stopProjectSharing(event){
    let projectid = event.target.parentElement.parentElement.id;
    console.log('Проект стал приватным ' + projectid);
    event.target.src = "/static/eye-off.svg";

    event.target.removeEventListener("click", stopProjectSharing);
    event.target.addEventListener('click', startProjectSharing);
}

function backToStartPage(event){
    document.querySelector("#main_page").classList.toggle("disabled");
    document.querySelector("#projects_page").classList.toggle("disabled");
}

function editProject(event){
    let id = event.target.parentElement.parentElement.id;

    document.querySelector("#" + id + " p").classList.toggle("disabled");

    event.target.src="/static/disk.svg";
    event.target.removeEventListener("click", editProject);
    event.target.addEventListener("click", saveProject);


    let textArea  = document.createElement("textarea");
    textArea.className = "catalog-editor catalog-name";
    textArea.value = document.querySelector("#" + event.target.parentElement.parentElement.id + " p").textContent;

    document.querySelector("#" + id).prepend(textArea);
}

function saveProject(event){
    console.log("Сохранение пока не работает");
}

function createNewProject(event){
    document.querySelector("#projects_page").classList.toggle("disabled");
    document.querySelector("#projects_project_creation_window").classList.toggle("disabled");
}

function openProject(event){
    console.log("Открытие проекта пока не работает");
}