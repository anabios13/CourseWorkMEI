function addNewAnswer(event){
    let html = '<div id="' + getNextAnswerId() + '" class="answer"><' +
        'input type="checkbox">\n' +
        '<input type="text">' +
        '</div>';
    document.querySelector("#add-answer").insertAdjacentHTML("beforebegin", html);
}

var questionId = 0;
function getNextQuestionId(){
    questionId += 1;
    return questionId;
}

var answerId = 0;
function getNextAnswerId(){
    answerId += 1;
    return answerId;
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
    let html = '<div id="question' + questionId + '" class="question">\n' +
        '           <p onclick="selectQuestion(event)">Вопрос ' + questionId +'</p>\n' +
        '            <img onclick="removeQuestion(event)" src="cross-circle.svg" class="icon">\n' +
        '        </div>'
    document.querySelector("#add-question").insertAdjacentHTML("beforebegin", html);

    let questionArea = '<div id="q' + questionId + '" class="question-area">\n' +
        '                        <div class="geogebra-link">\n' +
        '                            <p>Сылка на график в GeoGebra</p>\n' +
        '                            <input type="text">\n' +
        '                        </div>\n' +
        '                        <textarea class="question-text">Текст</textarea>\n' +
        '                        <div class="answer-buttons-list">\n' +
        '                            <div onclick="addNewAnswer(event)" class="add-answer answer">\n' +
        '                                <img src="add.svg" alt="" class="icon">\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>'
    document.querySelector("#question-window").insertAdjacentHTML("beforeend", questionArea);
}

var selectedQuestionId = ""

function selectQuestion(event){
    if(event.target.id == ""){
        event.target.parentElement.className = "question selected-question";
        selectedQuestionId = event.target.parentElement.id
    }else{
        event.target.classList.toggle("selected-question");
        selectedQuestionId = event.target.id
    }
}

