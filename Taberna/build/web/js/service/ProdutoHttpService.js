class ProdutoHttpService {

    static get URI() {
        return "http://localhost:8080/Taberna/api/produto";
    }

    constructor() {
        console.log("ProdutoHttpService");
    }

    //ok e err sao funcoes de callback
    enviaProduto(produto, ok, err) {
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
        xhttp.open("POST", ProdutoHttpService.URI, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(produto));
    }

    atualizaProduto(prodid, produto, ok, err) {
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
        xhttp.open("PUT", ProdutoHttpService.URI + "/" + prodid, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(produto));
    }

    deletaProduto(prodid, ok, err) {
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
        xhttp.open("DELETE", ProdutoHttpService.URI + "/" + prodid, true);
        xhttp.send();
    }

    buscarProdutos(ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    let listaProdutos = JSON.parse(xhttp.responseText)
                            .map(item => new Produto(item.nome, item.descricao));
                    ok(listaProdutos);
                } else {
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("GET", ProdutoHttpService.URI, true);
        xhttp.send();

    }
}