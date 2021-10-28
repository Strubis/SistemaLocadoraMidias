package locacaomidias.entidades;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author EmersonPC
 */
public class Midia {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size( min = 1, max = 100 )
    private String titulo;
    
    @NotNull
    @Size( min = 1, max = 100 )
    private String anoLancamento;
    
    @NotNull
    @Pattern( regexp = "^\\d{13}$",
              message = "Deve corresponder à 9999999999999" )
    private String codigoBarras;
    
    @NotNull
    private Long duracaoMin;
    
    @NotNull
    private AtorAtriz idAtorAtrizPrincipal;
    
    @NotNull
    private AtorAtriz idAtorAtrizCoadjuvante;
    
    @NotNull
    private Genero idGenero;
    
    @NotNull
    private ClassificacaoEtaria idClassEtaria;
    
    @NotNull
    private Tipo idTipo;
    
    @NotNull
    private ClassificacaoInterna idClassInterna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(Long duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public AtorAtriz getIdAtorAtrizPrincipal() {
        return idAtorAtrizPrincipal;
    }

    public void setIdAtorAtrizPrincipal(AtorAtriz idAtorAtrizPrincipal) {
        this.idAtorAtrizPrincipal = idAtorAtrizPrincipal;
    }

    public AtorAtriz getIdAtorAtrizCoadjuvante() {
        return idAtorAtrizCoadjuvante;
    }

    public void setIdAtorAtrizCoadjuvante(AtorAtriz idAtorAtrizCoadjuvante) {
        this.idAtorAtrizCoadjuvante = idAtorAtrizCoadjuvante;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public ClassificacaoEtaria getIdClassEtaria() {
        return idClassEtaria;
    }

    public void setIdClassEtaria(ClassificacaoEtaria idClassEtaria) {
        this.idClassEtaria = idClassEtaria;
    }

    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    public ClassificacaoInterna getIdClassInterna() {
        return idClassInterna;
    }

    public void setIdClassInterna(ClassificacaoInterna idClassInterna) {
        this.idClassInterna = idClassInterna;
    }
    
}
