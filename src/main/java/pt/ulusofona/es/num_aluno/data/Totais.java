package pt.ulusofona.es.num_aluno.data;

import java.io.Serializable;


/**
 * Created by Miguel Soeiro on 02/12/2016.
 */
public class Totais implements Serializable {

    private double transportesValue, propinasValue, alimentacaoValue, redaValue, outroValue, totalValue, variacao = 1337;

    public double getTransportesValue() { return transportesValue; }
    public void setTransportesValue(double transportesValue) { this.transportesValue = transportesValue;}
    public double getPropinasValue() {
        return propinasValue;
    }
    public void setPropinasValue(double propinasValue) {
        this.propinasValue = propinasValue;
    }
    public double getAlimentacaoValue() {
        return alimentacaoValue;
    }
    public double getTotalValue() { return totalValue; }
    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }
    public void setAlimentacaoValue(double alimentacaoValue) {
        this.alimentacaoValue = alimentacaoValue;
    }
    public double getRedaValue() {
        return redaValue;
    }
    public void setRedaValue(double redaValue) {
        this.redaValue = redaValue;
    }
    public double getOutroValue() {
        return outroValue;
    }
    public void setOutroValue(double outroValue) {
        this.outroValue = outroValue;
    }
    public double getVariacao() { return variacao; }
    public void setVariacao(double variacao) { this.variacao = variacao; }
    public void somaPropinas(double valor){
        this.propinasValue = this.propinasValue + valor;
    }
    public void somaTransportes(double valor){
        this.transportesValue = this.transportesValue + valor;
    }
    public void somaAlimentacao(double valor){
        this.alimentacaoValue = this.alimentacaoValue + valor;
    }
    public void somaReda(double valor){
        this.redaValue = this.redaValue + valor;
    }
    public void somaOutro(double valor){
        this.outroValue = this.outroValue + valor;
    }
    public double somaTotal(){
        totalValue = transportesValue +propinasValue + alimentacaoValue + redaValue +outroValue;
        return totalValue;
    }
}

