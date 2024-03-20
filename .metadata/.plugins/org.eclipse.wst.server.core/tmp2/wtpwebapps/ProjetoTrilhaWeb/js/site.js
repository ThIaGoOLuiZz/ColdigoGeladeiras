function validaFaleConosco(){
    var nome = document.frmfaleconosco.txtnome.value;
    var expRegNome = new RegExp("^[A-zÀ-ü]{3,}([ ]{1}[A-zÀ-ü]{2,})+$")

    if (!expRegNome.test(nome)){
        alert("Prencha o campo Nome corretamente!");
        document.frmfaleconosco.txtnome.focus();
        return false;
    }

    var fone = frmfaleconosco.txtfone.value;
    var expRegNome = new RegExp("^[(]{1}[1-9]{2}[)]{1}[0-9]{4,5}[-]{1}[0-9]{4}$")

    if(!expRegNome.test(fone)){
        alert("Prencha o campo Telefone corretamente!");
        document.frmfaleconosco.txtfone.focus();
        return false;
    }
    if(document.frmfaleconosco.txtfone.value==""){
        alert("Preencha o campo Telefone.");
        document.frmfaleconosco.txtfone.focus();
        return false
    }
    if(document.frmfaleconosco.txtemail.value==""){
        alert("Preencha o campo Email.");
        document.frmfaleconosco.txtemail.focus();
        return false
    }
    if(document.frmfaleconosco.selmotivo.value==""){
        alert("Escolha um motivo!.");
        document.frmfaleconosco.selmotivo.focus();
        return false
    }
    if(document.frmfaleconosco.selproduto.value==""){
        alert("Escolha um motivo!.");
        document.frmfaleconosco.selproduto.focus();
        return false
    }
    if(document.frmfaleconosco.txtcomentario.value==""){
        alert("Preencha o campo Comentario.");
        document.frmfaleconosco.txtcomentario.focus();
        return false
    }
    return true;
}

function verificaMotivo(motivo){
    var elemento = document.getElementById("opcaoProduto");

    if(motivo=="PR"){
        var select = document.createElement("select");
        select.setAttribute("name", "selproduto");

        var option = document.createElement("option");
        option.setAttribute("value", "");

        var texto = document.createTextNode("Escolha")
        option.appendChild(texto);
        select.appendChild(option)

        var option = document.createElement("option")
        option.setAttribute("value", "FR");

        var texto = document.createTextNode("freezer");
        option.appendChild(texto);
        select.appendChild(option);

        var option = document.createElement("option")
        option.setAttribute("value", "GE");

        var texto = document.createTextNode("Geladeira");
        option.appendChild(texto);
        select.appendChild(option);

        elemento.appendChild(select);
    }else{
        if(elemento.firstChild)
            elemento.removeChild(elemento.firstChild);
    }
}

$(document).ready(function() {
    $("header").load("http://localhost:8085/ProjetoTrilhaWeb/pages/site/general/cabecalho.html");
    $("nav").load("http://localhost:8085/ProjetoTrilhaWeb/pages/site/general/menu.html");
    $("footer").load("http://localhost:8085/ProjetoTrilhaWeb/pages/site/general/rodape.html");
});