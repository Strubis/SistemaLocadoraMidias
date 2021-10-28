package locacaomidias.entidades;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *
 * @author EmersonPC
 */
public class ItemLocacao {
    
    @NotNull
    private Locacao idLocacao;
    
    @NotNull
    private Exemplar idExemplar;
    
    @NotNull
    @Positive
    private BigDecimal valor;

    public Locacao getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Locacao idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Exemplar getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Exemplar idExemplar) {
        this.idExemplar = idExemplar;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}
