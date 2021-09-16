window.onload = function () {

    $("li[id='replace-msg-icon']").load("/ajax/content/mensagem-icon");         //mensagem icon navbar
    $("div[id='replace-msg-modal']").load("/ajax/content/mensagem-modal");      //mensagens (caixa de entrada)

}

function confirmarSolicit(user){
    $.ajax({
        type: "POST",
        url: "/principalUser/respSolicitacao",
        data: {username: user, resposta: true},
        success: function (data) {
        console.log('Submission was successful.');
        $("div[class*='class-solicit-"+user+"']").remove();
        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        },
    });
}

function negarSolicit(user){
    $.ajax({
        type: "POST",
        url: "/principalUser/respSolicitacao",
        data: {username: user, resposta: false},
        success: function (data) {
            console.log('Submission was successful.');
            $("div[class*='class-solicit-"+user+"']").remove();
        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        },
    });
}
