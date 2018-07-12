class EventoHttpService {

    static get URI() {
        return "http://localhost:8080/Taberna/api/evento";
    }

    constructor() {
        console.log("EventoHttpService");
    }

    //ok e err sao funcoes de callback
    enviaEvento(evento, ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 201) {
                    ok();
                } else {
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("POST", EventoHttpService.URI, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(evento));
    }
    
    atualizaEvento(eveid,evento, ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 201) {
                    ok();
                } else {
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("PUT", EventoHttpService.URI+"/"+eveid, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(evento));
    }

    buscarEventos(ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    let listaEventos = JSON.parse(xhttp.responseText)
                            .map(item => new Evento(item.horario, item.ministrante, item.local, item.valor));
                    ok(listaEventos);
                } else {
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("GET", EventoHttpService.URI, true);
        xhttp.send();

    }
}