var elementoBody = document.querySelector("body");
elementoBody.onload = carregarEventos;

function carregarEventos(){
    buscarEventos();
    setInterval(buscarEventos,15000);    
}
var i=0;
function buscarEventos(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState===4 && this.status===200){
            var main = document.querySelector("main");
            
            var listaEventos = JSON.parse(this.responseText);
            
            montarHTML(listaEventos);
            console.log(this.responseText);
        }
    }
    
    xhttp.open("GET","http://localhost:8080/Taberna/api/evento",true);
    xhttp.send();
}

function montarHTML(listaEventos){
    var str = "";
    
    for(var ind in listaEventos){
        if( ind % 3 === 1)
            str += "<dl class='central'>";
        else
            str += "<dl>";
        
        str+= "<dd>";
        str+= "<figure>";
        str+= "<img src='"+ "TabernaEstilos/acess1.png"+
                "' alt='"+listaEventos[ind].nome+" width=117 height=155' />";
        str+= "</figure>";
        str+= "</dd>";
        str+= "<dt>";
        str+= "<a href='#'>"+listaEventos[ind].nome+"</a>";
        str+= "</dt>";
        str+= "<dd>" +listaEventos[ind].descricao+"</dd>";
        str+= "</dl>";
        
        if (ind %3 === 2)
            str+= "<div class='clear'></div>";
        
    }

    var elementoMain = document.querySelector("main");
    elementoMain.innerHTML = str;
}
        


