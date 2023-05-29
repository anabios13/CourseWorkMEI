var jsonString = '{ "questions" : [{"geogebraLink" : "https://www.geogebra.org/classic/qd2retxb", "text" : "Какая фигура изображена на рисунке?","answers" : [{"text" : "Прямоугольник","isRight" : true},{"text" : "Квадарат","isRight" : false}]},{"geogebraLink" : "https://www.geogebra.org/classic/rhp5vcx4", "text" : "График какой функции изображён на рисунке?","answers" : [{"text" : "x^(2)+3x+2","isRight" : true},{"text" : "x^(2)+2x+3","isRight" : false},{"text" : "x^(3)+2x+3","isRight" : false}]}]}';
var ajaxTest = JSON.parse(jsonString);
console.log(ajaxTest);
appendQuestionsList();
appendQuestion();

var questionId = 0;
function getNextQuestionId(){
    questionId += 1;
    return questionId;
}

function appendQuestion(){
    let questionArea = document.querySelector("#testing_page-list-questions")

    console.log(ajaxTest["questions"]);
    ajaxTest["questions"].forEach(element => {
        let html = '<div id="question-area-'+ getNextQuestionId() +'" class="testing_page-question-area'+ ((questionId > 1) ? " disabled" : "") + '">' +
            '<div id="testing_page-attachment">\n' +
            '                   <iframe src="/static/' + element["geogebraLink"] + '"></iframe>\n' +
            '               </div>\n' +
            '               <div class="question-text" id="testing_page-question-text">' + element["text"] + '</div>\n' +
            '               <div class="answer-buttons-list" id="testing_page-list-answers">' + appendAnswers(element) + '</div>' +
            '</div>';
        questionArea.insertAdjacentHTML("beforebegin", html);

    })

}

function appendAnswers(jsonString){
    let answersHtml = "";
    jsonString["answers"].forEach(answer => {
        let answerHtml = '<div onclick="selectAnswer(event);" class="testing_page-answer">' +
            '<p>' + answer["text"] + '</p> ' +
            '</div>';
        answersHtml += answerHtml;
    });
    return answersHtml;
}

function appendQuestionsList(){
    let html ='<div class="testing_page-row-questions">\n';

    let countQuestions = ajaxTest["questions"].length;

    for( let i = 0; i < countQuestions; i++){
        html += '<div class="testing_page-question' + ((i == 0) ? " testing_page-selected-question" : "") + '" id="test-question-'+ (i + 1) +'" onClick="selectQuestion(event);">' + (i + 1) +'</div>'
    }
    html += '</div>';

    document.querySelector("#testing_page-list-questions").insertAdjacentHTML("afterbegin", html);
}

var selectedQuestionId = "test-question-1"

function selectQuestion(event){
    let lastSelectedQuestionNumber = "";

    if(selectedQuestionId != ""){
        lastSelectedQuestionNumber = selectedQuestionId.split("-")[2];
    }

    let newSelectedQuestionNumber = event.target.id.split("-")[2];
    if(selectedQuestionId != event.target.id){
        event.target.className = "testing_page-question testing_page-selected-question";
        document.querySelector("#question-area-" + newSelectedQuestionNumber).className = "testing_page-question-area";

        document.querySelector("#" + selectedQuestionId).className = "testing_page-question";
        document.querySelector("#question-area-" + lastSelectedQuestionNumber).className = "testing_page-question-area disabled";

        selectedQuestionId = event.target.id;
    }
}

function selectAnswer(event){
    if(event.target.className != ""){
        event.target.classList.toggle("testing_page-selected-answer");
    }else {
        event.target.parentElement.classList.toggle("testing_page-selected-answer");
    }
}