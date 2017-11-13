var main = function() {
    $('#new-question-button').click(addNewQuestionBox);
    $('#new-answer-button').click(addNewAnswerBox);
};

window.onload = main;


var addNewQuestionBox = function() {
    document.getElementById('new-question-button').style.display = 'none';
    document.getElementById('new-question-submission-box').style.display = 'block';
};


var addNewAnswerBox = function() {
    document.getElementById('new-answer-button').style.display = 'none';
    document.getElementById('new-answer-submission-box').style.display = 'block';
};