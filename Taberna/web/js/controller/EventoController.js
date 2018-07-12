class EventoController {
    constructor() {
        this._service = new EventoHttpService();
        this.eventoView = new EventoView("#eventos");
        
    }

    salvar(event) {
        event.preventDefault();
        let horario = document.querySelector("#txthorario").value;
        let ministrante = document.querySelector("#txtministrante").value;
        let local = document.querySelector("#txtlocal").value;
        let valor = document.querySelector("#txtvalor").value;
        let evento = new Evento(horario, ministrante, local, valor);
        
        const self = this;
        
        this._service.enviaEvento(evento,
            function(){
                self.limparCamposFormulario();
                self.carregaEventos();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }
    
    atualizar(event) {
        event.preventDefault();
        let horario = document.querySelector("#txthorario").value;
        let ministrante = document.querySelector("#txtministrante").value;
        let local = document.querySelector("#txtlocal").value;
        let valor = document.querySelector("#txtvalor").value;
        let evento = new Evento(horario, ministrante, local, valor);
        
        const self = this;
        
        this._service.atualizaEvento(id,evento,
            function(){
                self.limparCamposFormulario();
                self.carregaEventos();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }

    carregaEventos() {
        console.log("Carrega Eventos");
        const self = this;
        this._service.buscarEventos(
            function(listaEventos){
                console.log(listaEventos);
                self.produtoView.atualiza(listaEventos);
            },
            function(msg){
                console.log(msg);
            }
        );
    }
    
    montarHTML(listaEventos) {
        document.querySelector("table").innerHTML =
                `<tr>
                <th>Horario</th>
                <th>Ministrante</th>
                <th>Local</th>
                <th>Valor</th> 
             </tr> `;
        for (let ind in listaEventos) {
            let tr = document.createElement("tr");
            let linha =
                    `   <td>${listaEventos[ind].horario}</td>
                    <td>${listaEventos[ind].ministrante}</td>
                    <td>${listaEventos[ind].local}</td>
                    <td>${listaEventos[ind].valor}</td>
                `;
            tr.innerHTML = linha;
            document.querySelector("table").appendChild(tr);
        }
    }

    limparCamposFormulario() {
        document.querySelector("#txthorario").value = "";
        document.querySelector("#txtministrante").value = "";
        document.querySelector("#txtlocal").value = "";
        document.querySelector("#txtvalor").value = "";
    }
}