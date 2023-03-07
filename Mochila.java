import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Mochila {

    public static void main(String args[]) {
        //leitor de dados que vem do "Stdin Inputs"
        Scanner leitorConsole = new Scanner(System.in);

        //Lista de objetos que a serem analisados para armazenamento na mochila
        //*****AQUI ESTAMOS USANDO A ESTRUTURA DE DADOS -> LISTA *****
        List listaObjetos = new ArrayList();

        //Definicao do peso maximo da mochila (em Kg)
        int pesoMaximoMochila = 15;

        //Representacao de cada objeto que foi indicado no console como parametro.
        //Foi definido aqui que o primeiro numero da linha eh o peso e o segundo numero eh o valor do objeto -> 10,5 (numeros estao separados por ",")
        Objeto objeto = null;

        //Vetor de String com 2 POSICOES que armazena cada linha de entrada digitada no console
        //*****AQUI ESTAMOS USANDO A ESTRUTURA DE DADOS -> ARRAY OU VETOR *****
        String[] linhaConsole = new String[2];

        try {
            //Loop/Repeticao para percorrer todos os objetos que foram digitados no console
            while (leitorConsole.hasNext()){
                //captura dos valores digitados que estao separados por ","
                linhaConsole = leitorConsole.nextLine().split(",");
                //criacao de cada objeto com seu peso e valor removendo eventuais espacos em branco (-> metodo trim)
                objeto = new Objeto(new Integer(linhaConsole[0].trim()).intValue(), new Double(linhaConsole[1].trim()).doubleValue());
                //Inclusao do objeto recem criado numa lista de objetos que sera usada para desenvolver a solucao
                listaObjetos.add(objeto);
            }
            System.out.println("Quantidade de objetos que serao analisados: " + listaObjetos.size());


            /*A PARTIR DAQUI E O RECHEIO COM A LOGICA PARA DEFINIR QUAIS OBJETOS
            DEVEM SER COLOCADOS DENTRO DA MOCHILA PARA MAXIMIZAR O VALOR
            POREM CONSIDERANDO O PESO LIMITE QUE A MOCHILA SUPORTA!*/

            //DICAS
            //Ja temos a lista de objetos pronta -> listaObjetos
            //E podemos acessar o peso e valor de cada objeto dessa forma -> objeto.getPeso(), objeto.getValor()
             /*
             Fazer um for[i] percorrendo a listaObjetos
             se a soma dos pesos[i] é menor ou igual a  pesoMaximoMochila.
             adiciona na mochila
             faz um outro for pegando o valor dos intens na mochila e 
             retorna a soma dos valores[j]
             retornar a soma dos pesos[j];
             Objeto obj = null;
            for (int i=0;i<listaObjetos.size();i++){
                obj = (Objeto) listaObjetos.get(i);
                System.out.println(obj.getPeso());
                
            }
             
              */
             Objeto obj = null;
             int[] peso;
             int[] valor;
             
            peso = new int[listaObjetos.size()];
            valor = new int[listaObjetos.size()];
            
            //cria os subgrupos
            for(int i = 0; i < listaObjetos.size(); i++) {
                obj = (Objeto) listaObjetos.get(i);
              if (obj.getPeso() < pesoMaximoMochila){
                  //adiciona na mochila 
                   peso[i] = obj.getPeso();
                     System.out.println( peso[i]);
                    valor[i] = (int)obj.getValor(); 
                     System.out.println( valor[i]);
              } else {
                    System.out.println("Objeto n adicionado dentro da mochila: ");
                }
            }
            
            int[][] vmax;
            int n = peso.length;
            // monta a mochila com o valor máximo
             vmax = new int[n + 1][pesoMaximoMochila + 1];
            
                for (int i = 1; i <= n; i++) {
                    for (int j = 0; j <= pesoMaximoMochila; j++) { 
                        if (peso[i - 1] <= j) {
                            vmax[i][j] = Math.max(vmax[i - 1][j], vmax[i - 1][j - peso[i - 1]] + valor[i - 1]);
                            
                        } else {
                            vmax[i][j] = vmax[i - 1][j];
                        }
                    }
                }
            
            // calcula o peso da mochila
            
            int i = n;
            int j = pesoMaximoMochila;
            int pesoatual = 0;
                while (i > 0 && j > 0) {
                    if (vmax[i][j] != vmax[i - 1][j]) {
                        System.out.println("Objeto " + i + " Com Peso " + peso[i - 1] + " e Valor " + valor[i - 1] + " Adicionado.");
                        j = j - peso[i - 1];
                        pesoatual += peso[i - 1];
                    }
                    i--;
                }
           

            System.out.println("Valor total dos objetos dentro da mochila: " + vmax[n][pesoMaximoMochila] );
            System.out.println("Peso maximo suportado pela mochila: " + pesoMaximoMochila + " kg");
            System.out.println("Peso total dos objetos dentro da mochila: " + pesoatual + "kg" );

        } catch (Exception e){
            System.out.println(e);
        } finally {
            leitorConsole.close();
        }
    }  
}