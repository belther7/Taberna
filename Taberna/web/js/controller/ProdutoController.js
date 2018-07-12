class ProdutoController {
    constructor() {
        this._service = new ProdutoHttpService();
        this.eventoView = new ProdutoView("#produtos");
        
    }

    salvar(event) {
        event.preventDefault();
        let nome = document.querySelector("#txtnome").value;
        let descricao = document.querySelector("#txtdescricao").value;
        let produto = new Produto(nome, descricao);
        
        const self = this;
        
        this._service.enviaProduto(produto,
            function(){
                self.limparCamposFormulario();
                self.carregaProdutos();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }
    
    atualizar(event) {
        event.preventDefault();
        let nome = document.querySelector("#txtnome").value;
        let descricao = document.querySelector("#txtdescricao").value;
        let id = document.querySelector("#txtid").value;
        let produto = new Produto(nome, descricao);
        
        const self = this;
        
        this._service.atualizaProduto(id,produto,
            function(){
                self.limparCamposFormulario();
                self.carregaProdutos();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }

    carregaProdutos() {
        console.log("Carrega Produtos");
        const self = this;
        this._service.buscarProdutos(
            function(listaProdutos){
                console.log(listaProdutos);
                self.produtoView.atualiza(listaProdutos);
            },
            function(msg){
                console.log(msg);
            }
        );
    }
    
    montarHTML(listaProdutos) {
        document.querySelector("table").innerHTML =
                `<tr>
                <th>Nome</th>
                <th>Descricao</th> 
             </tr> `;
        for (let ind in listaProdutos) {
            let tr = document.createElement("tr");
            let linha =
                    `   <td>${listaProdutos[ind].nome}</td>
                    <td>${listaProdutos[ind].descricao}</td>
                `;
            tr.innerHTML = linha;
            document.querySelector("table").appendChild(tr);
        }
    }

    limparCamposFormulario() {
        document.querySelector("#txtnome").value = "";
        document.querySelector("#txtdescricao").value = "";
    }
}