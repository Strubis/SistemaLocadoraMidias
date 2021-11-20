$( () => {
    
    // itens para locação
    let itensLocacao = [];
    
    // formatadores
    let fmtMoeda = new Intl.NumberFormat(
            "pt-BR", {
                style: "currency",
                currency: "BRL"
            }
    );
    
    // Formatando para a data atual para ser a mínima
    let dataAtual = new Date().toLocaleString("en-CA");
    let dataFmt = dataAtual.substring(0, 10);
    document.getElementById("dataDevolucao").min = dataFmt;
    
    // nova locação
    $( "#btnInserir" ).on( "click", event => {
        
        let $selectProduto = $( "#selectExemplar" );
        let $dataDevolucao = $( "#dataDevolucao" );
        
        let data = $dataDevolucao.val();
        let idExemplar = $selectProduto.val();
        let valorAluguel = $selectProduto.find( ":selected" ).data( "valor" );
        let titulo = $selectProduto.find( ":selected" ).data( "titulo" );
        
        if ( idExemplar !== null && data !== "" ) {
            
            let existe = null;
            itensLocacao.some( it => {
               if( it.titulo === titulo ){
                   existe = it;
                   return true;
               } 
            });
            
            if( existe === null ){
                itensLocacao.push({
                    idExemplar: idExemplar,
                    valorAluguel: valorAluguel,
                    titulo: titulo,
                    dataDevolucao: data
                });
            }else{
                alert( 'Midia já foi adicionada!' );
                return false;
            }
            
            $selectProduto.find( ":selected" ).prop('disabled', true);
            
            atualizarGUI();
            
        } else {
            alert( "Preencha todos os campos!" );
        }
        
    });
    
    // remover item
    $( "#btnRemover" ).on( "click", event => {
        
        // retorna um array com os values de todos os itens
        // (option) selecionados
        let selecao = $( "#selectItensLocacao" ).val();
        
        // se não selecionou nenhum
        if ( selecao.length === 0 ) {
            alert( "Selecione um item da venda para remover!" );
            
            //se há seleção
        } else if ( confirm( "Deseja remover o(s) item(ns) de locação selecionado(s)?" ) ) {

            // itera pela seleção
            for ( let i = 0; i < selecao.length; i++ ) {
                
                // busca sequencial nos itens de venda
                for ( let j = 0; j < itensLocacao.length; j++ ) {
                    
                    let item = itensLocacao[j];
                    
                    // encontrou?
                    if ( selecao[i] === item.idExemplar ) {
                        $( "#selectExemplar option[value=" + item.idExemplar + "]" )
                                .prop('disabled', false);
                        
                        // remove da posição j
                        itensLocacao.splice( j, 1 );
                        break;
                        
                    }
                    
                }
                
            }

            // remonta a lista
            atualizarGUI();
            
        }
        
    });
    
    // limpar itens
    $( "#btnLimpar" ).on( "click", event => {
        if ( confirm( "Deseja remover todas as locações?" ) ) {
            $( "#selectExemplar option" ).prop('disabled', false);
            
            itensLocacao = [];
            atualizarGUI();
        }
    });
    
    // evita que ao teclar enter dentro do campo
    // de texto o formulário seja submetido
    $( "#selectItensLocacao" ).on( "keydown", event => {
        if ( event.keyCode === 13 ) {
            event.preventDefault();
        }
    });
    
    $( "#dataDevolucao" ).on( "keydown", event => {
        if ( event.keyCode === 13 ) {
            event.preventDefault();
        }
    });
    
    // submissão da venda
    $( "#novaLocacao" ).on( "submit", event => {
        
        if ( $( "#selectItensLocacao > option" ).length === 0 ) {
            alert( "Uma locação precisa conter pelo menos uma mídia!" );
            return false;
        }
        
        return true;
        
    });
    
    // constrói as opções do <select> (lista) de itens de locação;
    // atualiza o valor total da locação;
    // e prepara os dados para envio
    let atualizarGUI = () => {
        
        let $select = $( "#selectItensLocacao" );
        let total = new Decimal( 0 );
        
        $select.html( "" );
        
        itensLocacao.forEach( item => {
            
            item.valorAluguel = item.valorAluguel.toString().replace(',', '.');
            let valorItem = new Decimal( item.valorAluguel );
            
            let data = new Date(item.dataDevolucao);
            data.setDate( data.getDate() + 1 );
            
            $opt = $( "<option></option>" ).
                    html( `${item.titulo} - ` + 
                    `${fmtMoeda.format( valorItem )} - ` + 
                    `Devolução: ${new Date(data).toLocaleDateString("pt-BR") }` ).
                    val( `${item.idExemplar}` );
            
            $select.append( $opt );
            total = total.plus( item.valorAluguel );
            
        });
        
        $( "#divTotal" ).html( "Total: " + fmtMoeda.format( total ) );
        $( "#hiddenItensLocacao" ).val( JSON.stringify( itensLocacao ) );
        
    };
    
});