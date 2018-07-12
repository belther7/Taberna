class ProdutoView{
    constructor(seletor){
        this.elemento = document.querySelector(seletor);
    }
    
    atualiza(listaProdutos){
        console.log(this.template(listaProdutos));
        this.elemento.innerHTML = this.template(listaProdutos);
    }
    
    template(listaProdutos){
        return `<table border='1px'>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Descrição</th> 
                <tr>
             </thead>
             <tbody>
                ${listaProdutos.map(produto =>
                    `<tr>
                        <td>${produto.nome}</td>
                        <td>${produto.descricao}</td>
                    </tr>
                    `).join('')}
             </tbody>
        </table>`;
    }
}