let controller = new ProdutoController();
//let controller2 = new EventoController();
console.log("Mapeando");
//bind: associa o controller como this. Se nao tiver, ele vai achar que a referencia é o document e não o controller
//Ou seja,quando chamarmos o carregaMotores e este chamar this._service, ele vai pensar que o this é referente ao controller e não ao document.
window.addEventListener('load',controller.carregaProdutos.bind(controller));
//window.addEventListener('load',controller2.carregaEventos.bind(controller2));

//bind: associa o controller como this. Se nao tiver, ele vai achar que a referencia é o document e não o controller
//Ou seja,quando chamarmos o salvar e este chamar this._service, ele vai pensar que o this é referente ao controller e não ao document.
document.querySelector("#formulario").addEventListener('submit',controller.salvar.bind(controller));
document.querySelector("#formulario_deleta").addEventListener('submit',controller.deletar.bind(controller));
//document.querySelector("#formulario_eve").addEventListener('submit',controller2.salvar.bind(controller2));
//document.querySelector("#formularioatualiza")
//        .addEventListener('submit',controller.atualizar.bind(controller));











