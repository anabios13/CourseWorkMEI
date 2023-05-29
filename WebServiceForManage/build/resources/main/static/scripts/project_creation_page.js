function addNewAnswer(event){
    let html = '<div class="answer"><' +
        'input type="checkbox">\n' +
        '<p contentEditable></p>' +
       ' <img onclick="deleteAnswer(event);" src="/static/cross-circle.svg" class="icon">'+
        '</div>';
    let questionAreaId = "";
    if (event.target.parentElement.parentElement.id != ""){
        questionAreaId = event.target.parentElement.parentElement.id;
    }else{
        questionAreaId = event.target.parentElement.parentElement.parentElement.id;
    }
    let addAnswer = document.querySelector("#" + questionAreaId + " .add-answer");
    addAnswer.insertAdjacentHTML("beforebegin", html);

    addAnswer.scrollIntoView(false);
}

function deleteAnswer(event){
    event.target.parentElement.remove();
}

var questionId = 0;
function getNextQuestionId(){
    questionId += 1;
    return questionId;
}

function setPreviousQuestionId(){
    if(questionId > 0){
        questionId -= 1;
    }
}

function removeQuestion(event) {
    event.target.parentElement.remove();
    setPreviousQuestionId();

    let lastId = event.target.parentElement.id.split("question")[1];
    console.log(lastId);
    document.querySelector("#q" + lastId).remove();

    let newId = 0;
    document.querySelectorAll(".question").forEach(element => {
        newId += 1;

        element.id = "question" + newId;
        element.querySelector("p").textContent = "Вопрос " + newId;
    })

    newId = 0;
    document.querySelectorAll(".question-area").forEach(element => {
        newId += 1;

        element.id = "q" + newId;
    })
}

function addNewQuestion(){
    let questionId = getNextQuestionId();
    let html = '<div onclick="selectQuestion(event)" id="question' + questionId + '" class="question">\n' +
        '           <p onclick="selectQuestion(event)">Вопрос ' + questionId +'</p>\n' +
        '            <img onclick="removeQuestion(event)" src="/static/cross-circle.svg" class="icon">\n' +
        '        </div>'
    let addQuestion = document.querySelector("#add-question");
    addQuestion.insertAdjacentHTML("beforebegin", html);

    addQuestion.scrollIntoView(false);

    let questionArea = '<div id="q' + questionId + '" class="question-area disabled">\n' +
        '                        <div class="geogebra-link">\n' +
        '                            <p>Сылка на график в GeoGebra</p>\n' +
        '                            <input type="text">\n' +
        '                        </div>\n' +
        '                        <p class="question-text" contentEditable></p>\n' +
        '                        <div class="answer-buttons-list">\n' +
        '                            <div onclick="addNewAnswer(event)" class="add-answer">\n' +
        '                                <img src="/static/add.svg" alt="" class="icon">\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>'
    document.querySelector("#question-window").insertAdjacentHTML("beforeend", questionArea);
}

var selectedQuestionId = ""

function selectQuestion(event){
    let questionAreaId = "";
    if(selectedQuestionId != "" && selectedQuestionId != event.target.parentElement.id && selectedQuestionId != event.target.id){
        document.querySelector("#" + selectedQuestionId).className = "question";
        questionAreaId =selectedQuestionId.split("question")[1];
        document.querySelector("#q" + questionAreaId).className = "question-area disabled";
    }
    if(event.target.id == ""){
        event.target.parentElement.className = "question selected-question";
        selectedQuestionId = event.target.parentElement.id;
        questionAreaId = event.target.parentElement.id.split("question")[1];

    }else{
        event.target.className = "question selected-question";
        selectedQuestionId = event.target.id;
        questionAreaId = event.target.id.split("question")[1];
    }
    document.querySelector("#q" + questionAreaId).className = "question-area";
}

function backToProjectsPage() {
    document.querySelector("#projects_project_creation_window").classList.toggle("disabled");
    document.querySelector("#projects_page").classList.toggle("disabled");
}

function saveProject(){
    let count = document.querySelectorAll(".question").length;

    let jsonString = '{ "questions" : [';
    let jsonQuestionString = ""
    document.querySelectorAll(".question-area").forEach(element => {
        jsonQuestionString += '{"geogebraLink" : "' + element.querySelector(".geogebra-link input").value + '",';
        jsonQuestionString += '"text" : "' + element.querySelector(".question-text").textContent + '",';
        jsonQuestionString += '"answers" : [';
        answers = element.querySelectorAll(".answer");
        if(answers.length > 0){
            answers.forEach(answer => {
                jsonQuestionString += '{"text" : "' + answer.querySelector("p").textContent + '",';
                jsonQuestionString += '"isRight" : ' + answer.querySelector("input").checked + '},'
            })
            jsonQuestionString = jsonQuestionString.slice(0, -1);
        }
        jsonQuestionString += ']},';
    })
    jsonQuestionString = jsonQuestionString.slice(0, -1);
    jsonString += jsonQuestionString;
    jsonString += ']}';
    console.log(jsonString);
}

