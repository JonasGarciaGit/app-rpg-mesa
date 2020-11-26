package model;

import java.io.Serializable;
import java.util.List;

public class Ficha implements Serializable {

    private String id;
    private String idJogador;

    private String nomeDoPersonagem;
    private String Antecedente;
    private String classe;
    private String nivel;
    private String raca;
    private String subRaca;
    private String tendencia;
    private String pontosDeExperiencia;
    private String nomeDoJogador;
    private String classeArmadura;
    private String iniciativa;
    private String deslocamento;


    //Atributos
    private String ptsForça;
    private String ptsDestreza;
    private String ptsConstituicao;
    private String ptsInteligencia;
    private String ptsSabedoria;
    private String ptsCarisma;

    private String pvTotais;
    private String pvAtuais;
    private String pvTemporario;
    private String dadosDeVida;
    private String total;

    private String extraForca;
    private String extraDestreza;
    private String extraConstituicao;
    private String extraInteligencia;
    private String extraSabedoria;
    private String extraCarisma;

    //teste de resistência a morte
    private List<Boolean> sucesso;
    private List<Boolean> fracasso;

    //Ataque e conjuração
    private List<String> nomeAtaqueConjuracao;
    private List<String> bonusAtaque;
    private List<String> danoTipo;

    private String ataqueCojuracaoDescricao;

    //Equipamentos
    private String pc;
    private String pp;
    private String pe;
    private String po;
    private String pl;
    private String equipamentoDescricao;

    //Caracteristicas
    private String tracosPersonalidades;
    private String ideias;
    private String vinculos;
    private String defeitos;
    private String caracteristicasTracosRaciais;

    //Pericias e salva guardas
    private String inspiracao;
    private String bonusDeProficiencia;
    private String sabedoriaPassiva;

    //testes de resistencia
    private String txtForca;
    private boolean forca;

    private String txtDestreza;
    private boolean destreza;

    private String txtCostituicao;
    private boolean costituicao;

    private String txtCarisma;
    private boolean carisma;

    private String txtSabedoria;
    private boolean sabedoria;

    private String txtInteligencia;
    private boolean inteligencia;

    private String txtAcrobacia;
    private boolean acrobacia;

    private String txtAdestrarAnimais;
    private boolean adestrarAnimais;

    private String txtArcanismo;
    private boolean arcanismo;

    private String txtAtletismo;
    private boolean atletismo;

    private String txtAtuacao;
    private boolean atuacao;

    private String txtEnganacao;
    private boolean enganacao;

    private String txtFurtividade;
    private boolean furtividade;

    private String txtHistoria;
    private boolean historia;

    private String txtIntimidacao;
    private boolean intimidacao;

    private String txtInvestigacao;
    private boolean investigacao;

    private String txtMedicina;
    private boolean medicina;

    private String txtNatureza;
    private boolean natureza;

    private String txtPercepcao;
    private boolean percepcao;

    private String txtPersuasao;
    private boolean persuasao;

    private String txtPrestigitacao;
    private boolean prestigitacao;

    private String txtReligiao;
    private boolean religiao;

    private String txtSobrevivencia;
    private boolean sobrevivencia;

    private String txtIntuicao;
    private boolean intuicao;

    public Ficha(){

    }

    public String getTxtIntuicao() {
        return txtIntuicao;
    }

    public void setTxtIntuicao(String txtIntuicao) {
        this.txtIntuicao = txtIntuicao;
    }

    public boolean isIntuicao() {
        return intuicao;
    }

    public void setIntuicao(boolean intuicao) {
        this.intuicao = intuicao;
    }

    public String getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(String iniciativa) {
        this.iniciativa = iniciativa;
    }

    public String getDeslocamento() {
        return deslocamento;
    }

    public void setDeslocamento(String deslocamento) {
        this.deslocamento = deslocamento;
    }

    public String getClasseArmadura() {
        return classeArmadura;
    }

    public void setClasseArmadura(String classeArmadura) {
        this.classeArmadura = classeArmadura;
    }

    public String getNomeDoPersonagem() {
        return nomeDoPersonagem;
    }

    public void setNomeDoPersonagem(String nomeDoPersonagem) {
        this.nomeDoPersonagem = nomeDoPersonagem;
    }

    public String getSubRaca() {
        return subRaca;
    }

    public void setSubRaca(String subRaca) {
        this.subRaca = subRaca;
    }

    public String getAntecedente() {
        return Antecedente;
    }

    public void setAntecedente(String antecedente) {
        Antecedente = antecedente;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTendencia() {
        return tendencia;
    }

    public void setTendencia(String tendencia) {
        this.tendencia = tendencia;
    }

    public String getPontosDeExperiencia() {
        return pontosDeExperiencia;
    }

    public void setPontosDeExperiencia(String pontosDeExperiencia) {
        this.pontosDeExperiencia = pontosDeExperiencia;
    }

    public String getNomeDoJogador() {
        return nomeDoJogador;
    }

    public void setNomeDoJogador(String nomeDoJogador) {
        this.nomeDoJogador = nomeDoJogador;
    }

    public String getPtsForça() {
        return ptsForça;
    }

    public void setPtsForça(String ptsForça) {
        this.ptsForça = ptsForça;
    }

    public String getPtsDestreza() {
        return ptsDestreza;
    }

    public void setPtsDestreza(String ptsDestreza) {
        this.ptsDestreza = ptsDestreza;
    }

    public String getPtsConstituicao() {
        return ptsConstituicao;
    }

    public void setPtsConstituicao(String ptsConstituicao) {
        this.ptsConstituicao = ptsConstituicao;
    }

    public String getPtsInteligencia() {
        return ptsInteligencia;
    }

    public void setPtsInteligencia(String ptsInteligencia) {
        this.ptsInteligencia = ptsInteligencia;
    }

    public String getPtsSabedoria() {
        return ptsSabedoria;
    }

    public void setPtsSabedoria(String ptsSabedoria) {
        this.ptsSabedoria = ptsSabedoria;
    }

    public String getPvTotais() {
        return pvTotais;
    }

    public void setPvTotais(String pvTotais) {
        this.pvTotais = pvTotais;
    }

    public String getPvAtuais() {
        return pvAtuais;
    }

    public void setPvAtuais(String pvAtuais) {
        this.pvAtuais = pvAtuais;
    }

    public String getPvTemporario() {
        return pvTemporario;
    }

    public void setPvTemporario(String pvTemporario) {
        this.pvTemporario = pvTemporario;
    }

    public String getDadosDeVida() {
        return dadosDeVida;
    }

    public void setDadosDeVida(String dadosDeVida) {
        this.dadosDeVida = dadosDeVida;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Boolean> getSucesso() {
        return sucesso;
    }

    public void setSucesso(List<Boolean> sucesso) {
        this.sucesso = sucesso;
    }

    public List<Boolean> getFracasso() {
        return fracasso;
    }

    public void setFracasso(List<Boolean> fracasso) {
        this.fracasso = fracasso;
    }

    public List<String> getNomeAtaqueConjuracao() {
        return nomeAtaqueConjuracao;
    }

    public void setNomeAtaqueConjuracao(List<String> nomeAtaqueConjuracao) {
        this.nomeAtaqueConjuracao = nomeAtaqueConjuracao;
    }

    public List<String> getBonusAtaque() {
        return bonusAtaque;
    }

    public void setBonusAtaque(List<String> bonusAtaque) {
        this.bonusAtaque = bonusAtaque;
    }

    public List<String> getDanoTipo() {
        return danoTipo;
    }

    public void setDanoTipo(List<String> danoTipo) {
        this.danoTipo = danoTipo;
    }

    public String getAtaqueCojuracaoDescricao() {
        return ataqueCojuracaoDescricao;
    }

    public void setAtaqueCojuracaoDescricao(String ataqueCojuracaoDescricao) {
        this.ataqueCojuracaoDescricao = ataqueCojuracaoDescricao;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getEquipamentoDescricao() {
        return equipamentoDescricao;
    }

    public void setEquipamentoDescricao(String equipamentoDescricao) {
        this.equipamentoDescricao = equipamentoDescricao;
    }

    public String getTracosPersonalidades() {
        return tracosPersonalidades;
    }

    public void setTracosPersonalidades(String tracosPersonalidades) {
        this.tracosPersonalidades = tracosPersonalidades;
    }

    public String getIdeias() {
        return ideias;
    }

    public void setIdeias(String ideias) {
        this.ideias = ideias;
    }

    public String getVinculos() {
        return vinculos;
    }

    public void setVinculos(String vinculos) {
        this.vinculos = vinculos;
    }

    public String getDefeitos() {
        return defeitos;
    }

    public void setDefeitos(String defeitos) {
        this.defeitos = defeitos;
    }

    public String getCaracteristicasTracosRaciais() {
        return caracteristicasTracosRaciais;
    }

    public void setCaracteristicasTracosRaciais(String caracteristicasTracosRaciais) {
        this.caracteristicasTracosRaciais = caracteristicasTracosRaciais;
    }

    public String getInspiracao() {
        return inspiracao;
    }

    public void setInspiracao(String inspiracao) {
        this.inspiracao = inspiracao;
    }

    public String getBonusDeProficiencia() {
        return bonusDeProficiencia;
    }

    public void setBonusDeProficiencia(String bonusDeProficiencia) {
        this.bonusDeProficiencia = bonusDeProficiencia;
    }

    public String getSabedoriaPassiva() {
        return sabedoriaPassiva;
    }

    public void setSabedoriaPassiva(String sabedoriaPassiva) {
        this.sabedoriaPassiva = sabedoriaPassiva;
    }

    public String getTxtForca() {
        return txtForca;
    }

    public void setTxtForca(String txtForca) {
        this.txtForca = txtForca;
    }

    public boolean isForca() {
        return forca;
    }

    public void setForca(boolean forca) {
        this.forca = forca;
    }

    public String getTxtDestreza() {
        return txtDestreza;
    }

    public void setTxtDestreza(String txtDestreza) {
        this.txtDestreza = txtDestreza;
    }

    public boolean isDestreza() {
        return destreza;
    }

    public void setDestreza(boolean destreza) {
        this.destreza = destreza;
    }

    public String getTxtCostituicao() {
        return txtCostituicao;
    }

    public void setTxtCostituicao(String txtCostituicao) {
        this.txtCostituicao = txtCostituicao;
    }

    public boolean isCostituicao() {
        return costituicao;
    }

    public void setCostituicao(boolean costituicao) {
        this.costituicao = costituicao;
    }

    public String getTxtCarisma() {
        return txtCarisma;
    }

    public void setTxtCarisma(String txtCarisma) {
        this.txtCarisma = txtCarisma;
    }

    public boolean isCarisma() {
        return carisma;
    }

    public void setCarisma(boolean carisma) {
        this.carisma = carisma;
    }

    public String getTxtSabedoria() {
        return txtSabedoria;
    }

    public void setTxtSabedoria(String txtSabedoria) {
        this.txtSabedoria = txtSabedoria;
    }

    public boolean isSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(boolean sabedoria) {
        this.sabedoria = sabedoria;
    }

    public String getTxtInteligencia() {
        return txtInteligencia;
    }

    public void setTxtInteligencia(String txtInteligencia) {
        this.txtInteligencia = txtInteligencia;
    }

    public boolean isInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(boolean inteligencia) {
        this.inteligencia = inteligencia;
    }

    public String getTxtAcrobacia() {
        return txtAcrobacia;
    }

    public void setTxtAcrobacia(String txtAcrobacia) {
        this.txtAcrobacia = txtAcrobacia;
    }

    public boolean isAcrobacia() {
        return acrobacia;
    }

    public void setAcrobacia(boolean acrobacia) {
        this.acrobacia = acrobacia;
    }

    public String getTxtAdestrarAnimais() {
        return txtAdestrarAnimais;
    }

    public void setTxtAdestrarAnimais(String txtAdestrarAnimais) {
        this.txtAdestrarAnimais = txtAdestrarAnimais;
    }

    public boolean isAdestrarAnimais() {
        return adestrarAnimais;
    }

    public void setAdestrarAnimais(boolean adestrarAnimais) {
        this.adestrarAnimais = adestrarAnimais;
    }

    public String getTxtArcanismo() {
        return txtArcanismo;
    }

    public void setTxtArcanismo(String txtArcanismo) {
        this.txtArcanismo = txtArcanismo;
    }

    public boolean isArcanismo() {
        return arcanismo;
    }

    public void setArcanismo(boolean arcanismo) {
        this.arcanismo = arcanismo;
    }

    public String getTxtAtletismo() {
        return txtAtletismo;
    }

    public void setTxtAtletismo(String txtAtletismo) {
        this.txtAtletismo = txtAtletismo;
    }

    public boolean isAtletismo() {
        return atletismo;
    }

    public void setAtletismo(boolean atletismo) {
        this.atletismo = atletismo;
    }

    public String getTxtAtuacao() {
        return txtAtuacao;
    }

    public void setTxtAtuacao(String txtAtuacao) {
        this.txtAtuacao = txtAtuacao;
    }

    public boolean isAtuacao() {
        return atuacao;
    }

    public void setAtuacao(boolean atuacao) {
        this.atuacao = atuacao;
    }

    public String getTxtEnganacao() {
        return txtEnganacao;
    }

    public void setTxtEnganacao(String txtEnganacao) {
        this.txtEnganacao = txtEnganacao;
    }

    public boolean isEnganacao() {
        return enganacao;
    }

    public void setEnganacao(boolean enganacao) {
        this.enganacao = enganacao;
    }

    public String getTxtFurtividade() {
        return txtFurtividade;
    }

    public void setTxtFurtividade(String txtFurtividade) {
        this.txtFurtividade = txtFurtividade;
    }

    public boolean isFurtividade() {
        return furtividade;
    }

    public void setFurtividade(boolean furtividade) {
        this.furtividade = furtividade;
    }

    public String getTxtHistoria() {
        return txtHistoria;
    }

    public void setTxtHistoria(String txtHistoria) {
        this.txtHistoria = txtHistoria;
    }

    public boolean isHistoria() {
        return historia;
    }

    public void setHistoria(boolean historia) {
        this.historia = historia;
    }

    public String getTxtIntimidacao() {
        return txtIntimidacao;
    }

    public void setTxtIntimidacao(String txtIntimidacao) {
        this.txtIntimidacao = txtIntimidacao;
    }

    public boolean isIntimidacao() {
        return intimidacao;
    }

    public void setIntimidacao(boolean intimidacao) {
        this.intimidacao = intimidacao;
    }

    public String getTxtInvestigacao() {
        return txtInvestigacao;
    }

    public void setTxtInvestigacao(String txtInvestigacao) {
        this.txtInvestigacao = txtInvestigacao;
    }

    public boolean isInvestigacao() {
        return investigacao;
    }

    public void setInvestigacao(boolean investigacao) {
        this.investigacao = investigacao;
    }

    public String getTxtMedicina() {
        return txtMedicina;
    }

    public void setTxtMedicina(String txtMedicina) {
        this.txtMedicina = txtMedicina;
    }

    public boolean isMedicina() {
        return medicina;
    }

    public void setMedicina(boolean medicina) {
        this.medicina = medicina;
    }

    public String getTxtNatureza() {
        return txtNatureza;
    }

    public void setTxtNatureza(String txtNatureza) {
        this.txtNatureza = txtNatureza;
    }

    public boolean isNatureza() {
        return natureza;
    }

    public void setNatureza(boolean natureza) {
        this.natureza = natureza;
    }

    public String getTxtPercepcao() {
        return txtPercepcao;
    }

    public void setTxtPercepcao(String txtPercepcao) {
        this.txtPercepcao = txtPercepcao;
    }

    public boolean isPercepcao() {
        return percepcao;
    }

    public void setPercepcao(boolean percepcao) {
        this.percepcao = percepcao;
    }

    public String getTxtPersuasao() {
        return txtPersuasao;
    }

    public void setTxtPersuasao(String txtPersuasao) {
        this.txtPersuasao = txtPersuasao;
    }

    public boolean isPersuasao() {
        return persuasao;
    }

    public void setPersuasao(boolean persuasao) {
        this.persuasao = persuasao;
    }

    public String getTxtPrestigitacao() {
        return txtPrestigitacao;
    }

    public void setTxtPrestigitacao(String txtPrestigitacao) {
        this.txtPrestigitacao = txtPrestigitacao;
    }

    public boolean isPrestigitacao() {
        return prestigitacao;
    }

    public void setPrestigitacao(boolean prestigitacao) {
        this.prestigitacao = prestigitacao;
    }

    public String getTxtReligiao() {
        return txtReligiao;
    }

    public void setTxtReligiao(String txtReligiao) {
        this.txtReligiao = txtReligiao;
    }

    public boolean isReligiao() {
        return religiao;
    }

    public void setReligiao(boolean religiao) {
        this.religiao = religiao;
    }

    public String getTxtSobrevivencia() {
        return txtSobrevivencia;
    }

    public void setTxtSobrevivencia(String txtSobrevivencia) {
        this.txtSobrevivencia = txtSobrevivencia;
    }

    public boolean isSobrevivencia() {
        return sobrevivencia;
    }

    public void setSobrevivencia(boolean sobrevivencia) {
        this.sobrevivencia = sobrevivencia;
    }

    public String getPtsCarisma() {
        return ptsCarisma;
    }

    public void setPtsCarisma(String ptsCarisma) {
        this.ptsCarisma = ptsCarisma;
    }

    public String getExtraForca() {
        return extraForca;
    }

    public void setExtraForca(String extraForca) {
        this.extraForca = extraForca;
    }

    public String getExtraDestreza() {
        return extraDestreza;
    }

    public void setExtraDestreza(String extraDestreza) {
        this.extraDestreza = extraDestreza;
    }

    public String getExtraConstituicao() {
        return extraConstituicao;
    }

    public void setExtraConstituicao(String extraConstituicao) {
        this.extraConstituicao = extraConstituicao;
    }

    public String getExtraInteligencia() {
        return extraInteligencia;
    }

    public void setExtraInteligencia(String extraInteligencia) {
        this.extraInteligencia = extraInteligencia;
    }

    public String getExtraSabedoria() {
        return extraSabedoria;
    }

    public void setExtraSabedoria(String extraSabedoria) {
        this.extraSabedoria = extraSabedoria;
    }

    public String getExtraCarisma() {
        return extraCarisma;
    }

    public void setExtraCarisma(String extraCarisma) {
        this.extraCarisma = extraCarisma;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }
}
