var main = function() {
    $('#new-question-button').click(addNewQuestionBox);
    $('#submit-new-question-button').click(submitNewQuestion);
};

window.onload = main;


var addNewQuestionBox = function() {
    document.getElementById('new-question-button').style.display = 'none';
    document.getElementById('new-question-submission-box').style.display = 'block';
};

var submitNewQuestion = function() {
  // TODO
};

