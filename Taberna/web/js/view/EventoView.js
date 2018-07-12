class EventoView{
    constructor(seletor){
        this.elemento = document.querySelector(seletor);
    }
    
    atualiza(listaEventos){
        console.log(this.template(listaEventos));
        this.elemento.innerHTML = this.template(listaEventos);
    }
    
    template(listaEventos){
        return `<table border='1px'>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Descrição</th> 
                <tr>
             </thead>
             <tbody>
                ${listaEventos.map(evento =>
                    `<tr>
                        <td>${evento.horario}</td>
                        <td>${evento.ministrante}</td>
                        <td>${evento.local}</td>
                        <td>${evento.valor}</td>
                    </tr>
                    `).join('')}
             </tbody>
        </table>`;
    }
}