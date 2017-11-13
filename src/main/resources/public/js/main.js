var main = function() {
    $('#new-question-button').click(addNewQuestionBox);
};

window.onload = main;


var addNewQuestionBox = function() {
    document.getElementById('new-question-button').style.display = 'none';
    document.getElementById('new-question-submission-box').style.display = 'block';
};

