var elementoBody = document.querySelector("body");
elementoBody.onload = carregarProdutos;

function carregarProdutos(){
    buscarProdutos();
    setInterval(buscarProdutos,15000);    
}
var i=0;
function buscarProdutos(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
            var main = document.querySelector("main");
            
            var listaProdutos = JSON.parse(this.responseText);
            
            montarHTML(listaProdutos);
            console.log(this.responseText);
        }
    }
    
    xhttp.open("GET","http://localhost:8080/Taberna/api/produto",true);
    xhttp.send();
}

function montarHTML(listaProdutos){
    var str = "";
    
    for(var ind in listaProdutos){
        if( ind % 3 === 1)
            str += "<dl class='central'>";
        else
            str += "<dl>";
        
        str+= "<dd>";
        str+= "<figure>";
        str+= "<img src='"+ "TabernaEstilos/acess1.png"+
                "' alt='"+listaProdutos[ind].nome+" width=117 height=155' />";
        str+= "</figure>";
        str+= "</dd>";
        str+= "<dt>";
        str+= "<a href='#'>"+listaProdutos[ind].nome+"</a>";
        str+= "</dt>";
        str+= "<dd>" +listaProdutos[ind].descricao+"</dd>";
        str+= "</dl>";
        
        if (ind %3 === 2)
            str+= "<div class='clear'></div>";
        
    }

    var elementoMain = document.querySelector("main");
    elementoMain.innerHTML = str;
}
        


