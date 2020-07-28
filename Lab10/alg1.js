
function nextStep(x){
    
    kolumny = kValues(x);
    wierszy = wValues(x);
    diagonal1 = calcDiagonal1(x);
    diagonal2 = calcDiagonal2(x);
    
    maxValue = 0;
    bestSteps = [];
    for (var i = 0; i < 5 ; i++){
        for(var j = 0; j < 5; j++){
            if(x[i * 5 + j] == 0){
                var value = wierszy[i] + kolumny[j];
                value += diagonal1[4 - i + j] + diagonal2[i + j];
                if(value > maxValue){
                    maxValue = value;
                    bestSteps = [];
                    bestSteps.push(i * 5 + j);
                } else if (value == maxValue){
                    bestSteps.push(i * 5 + j);
                }
            }
        }
    }
    bestSteps.sort(middleSort)
    return bestSteps[0];
}

function middleSort(first, second){
    var x = 12.5;
    if(Math.abs(first - x) < Math.abs(second - x)){
        return -1;
    }else {
        return 1;
    }
}

function calcValue(my, enemy){
    return my * my  + enemy * enemy * enemy;
}

function kValues(x){
    var kolumny = [];
    for(var i = 0; i < 5; i++){
        var gracz = 0;
        var komputer = 0;
        for(var j = 0; j < 5; j++){
            if(x[j*5+i] == 2){
                gracz++;
            }
            else if (x[j*5+i] == 1){
                komputer++;
            }
        }
        kolumny.push(calcValue(gracz, komputer));
    }
    return kolumny;
}

function wValues(x){
    var wierzy = [];
    for(var i = 0; i < 5; i++){
        var gracz = 0;
        var komputer = 0;
        for(var j = 0; j < 5; j++){
            if(x[i*5+j] == 2){
                gracz++;
            }
            else if(x[i*5+j] == 1){
                komputer++;
            }
        }
        wierzy.push(calcValue(gracz, komputer));
    }
    return wierzy;
}

function calcDiagonal1(x){
    var d1 = [];

    for(var i = 4; i >= 0; i--){
        var tmpW = i;
        var tmpK = 0;
        var gracz = 0;
        var komputer = 0;
        while(tmpW < 5 && tmpK < 5){
            if(x[tmpW * 5 + tmpK] == 2){
                gracz++
            }
            else if (x[tmpW * 5 + tmpK] == 1){
                komputer++;
            }
            tmpW++;
            tmpK++;
        }
        d1.push(calcValue(gracz, komputer));
    }

    for(var i = 1; i < 5; i++){
        var tmpW = 0;
        var tmpK = i;
        var gracz = 0;
        var komputer = 0;
        while(tmpW < 5 && tmpK < 5){
            if(x[tmpW * 5 + tmpK] == 2){
                gracz++
            }
            else if (x[tmpW * 5 + tmpK] == 1){
                komputer++;
            }
            tmpW++;
            tmpK++;
        }
        d1.push(calcValue(gracz, komputer));
    }

    return d1;
}

function calcDiagonal2(x){
    var d2 = [];

    for(var i = 0; i < 5; i++){
        var tmpW = 0;
        var tmpK = i;
        var gracz = 0;
        var komputer = 0;
        while(tmpW < 5 && tmpK >= 0){
            if(x[tmpW * 5 + tmpK] == 2){
                gracz++
            }
            else if (x[tmpW * 5 + tmpK] == 1){
                komputer++;
            }
            tmpW++;
            tmpK--;
        }
        d2.push(calcValue(gracz, komputer));
    }

    for(var i = 1; i < 5; i++){
        var tmpW = i;
        var tmpK = 4;
        var gracz = 0;
        var komputer = 0;
        while(tmpW < 5 && tmpK >= 0){
            if(x[tmpW * 5 + tmpK] == 2){
                gracz++
            }
            else if (x[tmpW * 5 + tmpK] == 1){
                komputer++;
            }
            tmpW++;
            tmpK--;
        }
        d2.push(calcValue(gracz, komputer));
    }


    return d2
}