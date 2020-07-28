
function nextStep(x){

    var solution;
    var value = Math.floor(Math.random() * 24);
    while (x[value] != 0){
        value = Math.floor(Math.random() * 24);
    }
    if(x[value] == 0) {
        solution = value;
    }

    return solution;
}

