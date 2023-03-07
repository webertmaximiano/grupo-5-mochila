public class Objeto {
    int peso = 0;
    double valor = 0.0;

    public Objeto(int peso, double valor){
        this.peso = peso;
        this.valor = valor;
    }

    public int getPeso(){
        return this.peso;
    }

    public double getValor(){
        return this.valor;
    }
}