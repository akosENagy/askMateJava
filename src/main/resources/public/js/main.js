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

var validateRegform = function() {
    var password = document.getElementById('password').value;
    var pwconfirm = document.getElementById('pw-confirm').value;

    if (password === pwconfirm) {
        return true;
    } else {
        alert("Passwords don't match!");
        return false;
    }
};