function validarCkeck(){

    let checkGen = false;
    document.querySelectorAll("input[class*='check-gen']").forEach(e=> {
        if (e.checked){
            checkGen = true;
        }
    });

    let checkCor = false;
    document.querySelectorAll("input[class*='check-cor']").forEach(e=> {
        if (e.checked){
            checkCor = true;
        }
    });

    let checkMusica = false;
    document.querySelectorAll("input[class*='check-musica']").forEach(e=> {
        if (e.checked){
            checkMusica = true;
        }
    });

    let checkAnimal = false;
    document.querySelectorAll("input[class*='check-animal']").forEach(e=> {
        if (e.checked){
            checkAnimal = true;
        }
    });

    return (checkGen && checkCor && checkMusica && checkAnimal);
}