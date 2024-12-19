import java.util.Random;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.lang.Character;

public class JogoVelha_nivel2 {
    // Estes caracteres são aceitos como caracteres para representarem
    // os jogadores. Utizado para evitar caracteres que não combinem com
    // o desenho do tabuleiro.
    final static String CARACTERES_IDENTIFICADORES_ACEITOS = "XO0UC";

    // Tamanho do tabuleiro 3x3. Para o primeiro nivel de dificuldade
    // considere que este valor não será alterado.
    final static int TAMANHO_TABULEIRO = 3;

    static int selecionaNivel(Scanner teclado){
        int nivel = 1;

        try{
            // Loop até o usuário enviar um caractere aceito
            do{
                // Recebe o valor do caractere que o usuario deseja competir com computador
                System.out.println("Qual nível do computador deseja competir? (Opções 1, 2)");
                nivel = teclado.nextInt();

                // Checa se o caractere digitado é válido
                if(nivel == 1 || nivel == 2){
                    System.out.println("Nível válido!");
                    System.out.println();
                    break;
                }
                else{
                    System.out.println("Nível inválido, digite novamente o número do nível");
                    System.out.println();
                }

            }while(true);
        }
        catch(Exception e){
            System.out.println("Erro na funçao 'selecionaNivel', erro: " + e);
            System.out.println();
        }
        
        return nivel;
    }

   static char[][] inicializarTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
        return tabuleiro;
   }

   static char obterCaractereUsuario(Scanner teclado) {
       // Inicializa variáveis
       char caractere = ' ';
       int counter;
       boolean isValid;

       try{
           // Loop até o usuário enviar um caractere aceito
           do{
               counter = 0;
               isValid = false;

               // Recebe o valor do caractere que o usuario deseja ser
               System.out.print("Escolha o caractere do Usuário. (Opções: " +  CARACTERES_IDENTIFICADORES_ACEITOS + "): ");
               caractere = teclado.next().charAt(0);

               // Converte caractere para letra maiuscula
               caractere = Character.toUpperCase(caractere);

               do{
                   // Checa se caractere é um dos CARACTERES_IDENTIFICADORES_ACEITOS
                   if(CARACTERES_IDENTIFICADORES_ACEITOS.charAt(counter) == caractere) {
                       isValid = true;
                       break;
                   }
                   else{
                       counter++;
                   }

               }while(counter < CARACTERES_IDENTIFICADORES_ACEITOS.length());

               // Checa se o caractere digitado é válido
               if(isValid){
                   System.out.println("Caractere válido!");
                   System.out.println();
                   break;
               }
               else{
                   System.out.println("Caractere inválido, digite novamente o caractere");
                   System.out.println();
               }

           }while(true);
       }
       catch(Exception e){
           System.out.println("Erro na funçao 'obterCaractereUsuario', erro: " + e);
           System.out.println();
       }

       return caractere;
   }
   
   static char obterCaractereComputador(Scanner teclado, char caractereUsuario) {
       // Inicializa variáveis
       char caractere = ' ';
       int counter;
       boolean isValid;

       int posicao;
       String CaracteresRestantes;
       StringBuilder sb;

       try{
           // Remove o caractere escolhido do usuário
           posicao = CARACTERES_IDENTIFICADORES_ACEITOS.indexOf(caractereUsuario);

           sb = new StringBuilder();
           sb.append(CARACTERES_IDENTIFICADORES_ACEITOS);
           sb.deleteCharAt(posicao);

           CaracteresRestantes = sb.toString();

           do{
               counter = 0;
               isValid = false;

               System.out.print("Escolha o caractere do Computador. (Opções: " +  CaracteresRestantes + "): ");
               // Recebe o valor do caractere que o computador será
               caractere = teclado.next().charAt(0);

               // Converte caractere para letra maiuscula
               caractere = Character.toUpperCase(caractere);

               do{
                   // Checa se caractere é um dos CaracteresRestantes
                   if(CaracteresRestantes.charAt(counter) == caractere) {
                       isValid = true;
                       break;
                   }
                   else{
                       counter++;
                   }

               }while(counter < CaracteresRestantes.length());

               // Checa se o caractere digitado é válido
               if(isValid){
                   System.out.println("Caractere válido!");
                   System.out.println();
                   break;
               }
               else{
                   System.out.println("Caractere inválido, digite novamente o caractere");
                   System.out.println();
               }

           }while(true);
       }
       catch(Exception e){
           System.out.println("Erro na funçao 'obterCaractereComputador', erro: " + e);
           System.out.println();
       }

       return caractere;
   }

   static boolean jogadaValida(String posicoesLivres, int linha, int coluna) {
        boolean jogadaValida;
        String jogada = linha +""+ coluna;

        if (posicoesLivres.contains(jogada)) {
            jogadaValida = true;

        }else{
            jogadaValida = false;
        }

        return jogadaValida;
   }

   static String checaSeVencera(String posicoesLivres, char[][] tabuleiro, char caractere){
        int tamanho = tabuleiro.length;
        // Da para fazer com outro for sendo J so que limitamos para -2, 
        // vai funcionar normalmente para 3x3, mas tbm ira funcionar para grades maiores de jogo da velha NxN

        for (int i = 0; i < tamanho; i++) {
            for(int j = 0; j < (tamanho - 2); j++){
                // Checa LINHAS ( O | O |   )
                if(tabuleiro[i][j] == caractere && tabuleiro[i][j+1] == caractere 
                && posicoesLivres.contains(i + "" + (j+2))){ 
                    return (i + "" + (j+2));
                }
                
                //               ( O )
                //               (---)
                // Checa COLUNAS ( O )  
                //               (---)
                //               (   )
                if(tabuleiro[j][i] == caractere && tabuleiro[j+1][i] == caractere 
                && posicoesLivres.contains((j+2) + "" + i)){ 
                    return ((j+2) + "" + i);
                }

                // Checa LINHAS meio ( O |   | O )
                if(tabuleiro[i][j] == caractere && tabuleiro[i][j+2] == caractere 
                && posicoesLivres.contains(i + "" + (j+1))){ 
                    return (i + "" + (j+1));
                }

                //                    ( O )
                //                    (---)
                // Checa COLUNAS meio (   )  
                //                    (---)
                //                    ( O )
                if(tabuleiro[j][i] == caractere && tabuleiro[j+2][i] == caractere 
                && posicoesLivres.contains((j+1) + "" + i)){ 
                    return ((j+1) + "" + i);
                }

            }
            // Checa a ultima posiçao LINHA, inversamente (  | O | O )
            if(tabuleiro[i][tamanho - 1] == caractere && tabuleiro[i][tamanho - 2] == caractere 
                && posicoesLivres.contains(i + "" + (tamanho - 3))){ 
                    return (i + "" + (tamanho - 3));
            }

            //                                             (   )
            //                                             (---)
            // Checa a ultima posiçao COLUNA, inversamente ( O )
            //                                             (---)
            //                                             ( O )
            if(tabuleiro[tamanho - 1][i] == caractere && tabuleiro[tamanho - 2][i] == caractere 
                && posicoesLivres.contains((tamanho - 3) + "" + i)){ 
                    return ((tamanho - 3) + "" + i);
            }

            //                      ( O |   |   )
            //                      (---|---|---)
            // Checa DIAGONAIS meio (   |   |   )
            //                      (---|---|---)
            //                      (   |   | O )
            if(tabuleiro[0][0] == caractere && tabuleiro[2][2] == caractere 
                && posicoesLivres.contains(1 + "" + 1) || 
                tabuleiro[0][2] == caractere && tabuleiro[2][0] == caractere 
                && posicoesLivres.contains(1 + "" + 1)){ 
            
                return (1 + "" + 1);
            }
        }

        // Checando para todas as DIAGONAIS se o meio delas está livre e os opostos posssuem jogada computador
        // Precisa ser fora do for por causa que o numero de repetiçoes é diferente
        for(int i = 0; i < (tamanho- 2); i++){
            for(int j = 0; j < (tamanho - 2); j++){
                //                 ( O |   |   )
                //                 (---|---|---)
                // Checa DIAGONAIS (   | O |   )
                //                 (---|---|---)
                //                 (   |   |   )
                if(tabuleiro[i][j] == caractere && tabuleiro[i+1][j+1] == caractere 
                && posicoesLivres.contains((i+2) + "" + (j+2))){ 
                    return ((i+2) + "" + (j+2));
                }

                //                 (   |   | O )
                //                 (---|---|---)
                // Checa DIAGONAIS (   | O |   )
                //                 (---|---|---)
                //                 (   |   |   )
                if(tabuleiro[i][j + 2] == caractere && tabuleiro[i+1][j+1] == caractere 
                && posicoesLivres.contains((i+2) + "" + j)){ 
                    return ((i+2) + "" + j);
                }

                //                      ( O |   |   )
                //                      (---|---|---)
                // Checa DIAGONAIS meio (   |   |   )
                //                      (---|---|---)
                //                      (   |   | O )
                if(tabuleiro[i][j] == caractere && tabuleiro[i+2][j+2] == caractere 
                    && posicoesLivres.contains((i+1) + "" + (j+1))){ 
                
                    return ((i+1) + "" + (j+1));
                }

                //                      (   |   | O )
                //                      (---|---|---)
                // Checa DIAGONAIS meio (   |   |   )
                //                      (---|---|---)
                //                      ( O |   |   )
                if(tabuleiro[i][j+2] == caractere && tabuleiro[i+2][j] == caractere 
                    && posicoesLivres.contains((i+1) + "" + (j+1))){ 
                
                    return ((i+1) + "" + (j+1));
                }
            }
            //                 (   |   |   )
            //                 (---|---|---)
            // Checa DIAGONAIS (   | O |   )
            //                 (---|---|---)
            //                 (   |   | O )
            if(tabuleiro[i+2][i+2] == caractere && tabuleiro[i+1][i+1] == caractere 
            && posicoesLivres.contains((i) + "" + (i))){ 
                return (i + "" + i);
            }

            //                 (   |   |   )
            //                 (---|---|---)
            // Checa DIAGONAIS (   | O |   )
            //                 (---|---|---)
            //                 ( O |   |   )
            if(tabuleiro[i+2][i] == caractere && tabuleiro[i+1][i+1] == caractere 
            && posicoesLivres.contains(i + "" + (i+2))){ 
                return (i + "" + (i+2));
            }
        }
        // Se nenhum dos casos foi valido retornara uma string vazia (""), que ser'a checada apos

        return "";
    }

   static int[] obterJogadaUsuario(String posicoesLivres, Scanner teclado) {

        while (true) {
            System.out.println("Digite a linha e a coluna separados por um espaço (ex: 1 2):");
            String input = teclado.nextLine();
            String[] valores = input.split(" ");

            if (valores.length != 2) {
                System.out.println("Entrada inválida. Por favor, digite dois valores separados por um espaço.");
                continue;
            }

            try {
                int linha = Integer.parseInt(valores[0]) - 1;
                int coluna = Integer.parseInt(valores[1]) - 1;

                if (jogadaValida(posicoesLivres, linha, coluna)) {
                    return new int[]{linha, coluna};
                } else {
                    System.out.println("Jogada não permitida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite números válidos.");
            }
        }
   }

   static int[] obterJogadaComputador(String posicoesLivres, char[][] tabuleiro, char caractereComputador, char caractereUsuario, int nivel) {
        String jogada;
        if(nivel == 2){
            // Checagens para ver se há alguma maneira do computador ganhar
            jogada = checaSeVencera(posicoesLivres, tabuleiro, caractereComputador);
            if(!jogada.isEmpty()){
                return converterJogadaStringParaVetorInt(jogada);
            }
            
            // Checagens para ver se há alguma maneira do usuario ganhar, se sim, faça a jogada naquela posição
            jogada = checaSeVencera(posicoesLivres, tabuleiro, caractereUsuario);
            if(!jogada.isEmpty()){
                return converterJogadaStringParaVetorInt(jogada);
            }
        }
    
        // Jogada randomica, caso nivel = 1 ou nao achou jogada inteligente
        String[] posicoesArray = posicoesLivres.split(";");
        Random random = new Random();
        int indiceSorteado = random.nextInt(posicoesArray.length);
        String jogadaSorteada = posicoesArray[indiceSorteado];
        return converterJogadaStringParaVetorInt(jogadaSorteada);
   }

   static int[] converterJogadaStringParaVetorInt(String jogada) {
       // Inicializa variaveis
       char charAuxiliar;
       int counter;

       // Cria o vetor com o tamanho da string
       int [] jogadaInt = new int[jogada.length()];    // Que será 2, serão valores de xy em string

       try {
           // Converte cada caractere da string para um vetor inteiro
           for(counter = 0; counter < jogada.length(); counter++){
               charAuxiliar = jogada.charAt(counter);
               jogadaInt[counter] = Integer.parseInt(String.valueOf(charAuxiliar));
           }

       } catch (Exception e) {
           System.out.println("Erro na funçao 'converterJogadaStringParaVetorInt', erro: " + e);
           System.out.println();
       }

       return jogadaInt;
   }

   static char[][] processarVezUsuario(Scanner teclado, char[][] tabuleiro, char caractereUsuario) {
        System.out.println("Usuário" + caractereUsuario + " faça sua jogada.");
        String posicoesLivres = retornarPosicoesLivres(tabuleiro);

        int [] jogada = obterJogadaUsuario(posicoesLivres, teclado);

        tabuleiro [jogada[0]][jogada[1]] = caractereUsuario;

        return tabuleiro;
   }

   static char[][] processarVezComputador(char[][] tabuleiro, char caractereComputador, char caractereUsuario, int nivel) {
        String posicoesLivres = retornarPosicoesLivres(tabuleiro);

        int [] jogada = obterJogadaComputador(posicoesLivres, tabuleiro, caractereComputador, caractereUsuario, nivel);

        tabuleiro [jogada[0]][jogada[1]] = caractereComputador;

        return tabuleiro;
   }

   static String retornarPosicoesLivres(char[][] tabuleiro) {
        String posicoesLivres = "";
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro[i].length; j++) {
                    if(tabuleiro[i][j] == ' '){
                        posicoesLivres += i + "" + j + ";";
                    }
                }
            }
        
            return posicoesLivres;
   }

   static boolean teveGanhador(char[][] tabuleiro, char caractereJogador) {
        if (teveGanhadorLinha(tabuleiro, caractereJogador) ||
            teveGanhadorColuna(tabuleiro, caractereJogador) ||
            teveGanhadorDiagonalPrincipal(tabuleiro, caractereJogador) ||
            teveGanhadorDiagonalSecundaria(tabuleiro, caractereJogador)) {
            return true;
        }
        return false;
   }

   static boolean teveGanhadorLinha(char[][] tabuleiro, char caractereJogador) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == caractereJogador &&
                tabuleiro[i][1] == caractereJogador &&
                tabuleiro[i][2] == caractereJogador) {
                return true;
            }
        }
        return false;
   }

   static boolean teveGanhadorColuna(char[][] tabuleiro, char caractereJogador) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == caractereJogador &&
                tabuleiro[1][i] == caractereJogador &&
                tabuleiro[2][i] == caractereJogador) {
                return true;
            }
        }
        return false;
   }

   static boolean teveGanhadorDiagonalPrincipal(char[][] tabuleiro, char caractereJogador) {
        if (tabuleiro[0][0] == caractereJogador &&
            tabuleiro[1][1] == caractereJogador &&
            tabuleiro[2][2] == caractereJogador) {
            return true;
        }
        return false;
   }

   static boolean teveGanhadorDiagonalSecundaria(char[][] tabuleiro, char caractereJogador) {
        if (tabuleiro[0][2] == caractereJogador &&
            tabuleiro[1][1] == caractereJogador &&
            tabuleiro[2][0] == caractereJogador) {
            return true;
        }
        return false;
   }

   static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ou limpar dessa forma
        // for (int i = 0; i < 50; ++i) {
        //     System.out.println();
        // }
   }

   static void exibirTabuleiro(char[][] tabuleiro) {
        limparTela();

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(" " + tabuleiro[i][j] + " ");
                if (j < tabuleiro[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < tabuleiro.length - 1) {
                System.out.println("---+---+---");
            }
        }
   }

   static char[][] retornarTabuleiroAtualizado(char[][] tabuleiro, int[] jogada, char caractereJogador) {
        tabuleiro[jogada[0]][jogada[1]] = caractereJogador;
    	return tabuleiro;
   }

   static void exibirVitoriaComputador() {
        System.out.println("\nO computador venceu !\n");
        System.out.println("                         +-------------------+");
        System.out.println("                         | +---------------+ |");
        System.out.println("                         | |               | |");
        System.out.println("                         | |     0   0     | |");
        System.out.println("                         | |       -       | |");
        System.out.println("                         | |     \\___/     | |");
        System.out.println("                         | |               | |");
        System.out.println("                         | +---------------+ |");
        System.out.println("                         +-------+---+-------+");
        System.out.println("                         ______|/     \\|______");
        System.out.println("                        /                     \\");
        System.out.println("                       /  *******************  \\");
        System.out.println("                      /   *******************   \\");
        System.out.println("                     +---------------------------+");

   }

   static void exibirVitoriaUsuario() {
        System.out.println(" ++++ O usuário venceu! ++++");
        System.out.println("""
               _ _ _
               \\\\\\\\\\\\---¬
               | ------- \\
               \\| ¬   ¬ |/
                | @   @ |
               (|   ¬   |)	
                |  __,  |	
                 \\     /	
                  '___'
                __/ ' \\__								
        """);
   }

   static void exibirEmpate() {
        System.out.println("Ocorreu empate!");
            System.out.println("_______             _______   \n" +
                              "\\   _  \\   ___  ___ \\   _  \\  \n" +
                               "/  /_\\  \\  \\  \\/  / /  /_\\  \\ \n" +
                              "\\  \\_/   \\  >    <  \\  \\_/   \\\n" +
                             " \\_____  / /__/\\_ \\  \\_____  /\n" +
                             "       \\/        \\/        \\/");
   }

   static boolean teveEmpate(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Ainda há posições livres, não é empate
                }
            }
        }
        return true; // Caso todas as posições sejam preenchidas é empate
   }

   static boolean sortearValorBooleano() {
        Random random = new Random();
        return random.nextBoolean();
   }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        char[][] tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        
        int nivel = selecionaNivel(teclado);

        //ToDo: Faça a inicialização do tabuleiro aqui
        tabuleiro = inicializarTabuleiro(tabuleiro);

        // Definimos aqui qual é o caractere que cada jogador irá utilizar no jogo.
        // ToDo: chame as funções obterCaractereUsuario() e obterCaractereComputador()
        //para definir quais caracteres da lista de caracteres aceitos que o jogador
        //quer configurar para ele e para o computador.
        char caractereUsuario = obterCaractereUsuario(teclado);
        char caractereComputador = obterCaractereComputador(teclado, caractereUsuario);

        teclado.nextLine();

        // Esta variavel é utilizada para definir se o usuário começa a jogar ou não.
        // Valor true, usuario começa jogando, valor false computador começa.
        //ToDo: obtenha o valor booleano sorteado
        boolean vezUsuarioJogar = sortearValorBooleano();

        boolean jogoContinua;

        do {
            // controla se o jogo terminou
            jogoContinua = true;
            //ToDo: Exiba o tabuleiro aqui
            exibirTabuleiro(tabuleiro);
            
            if (vezUsuarioJogar){
                //ToDo: Execute processar vez do usuario
                tabuleiro = processarVezUsuario(teclado, tabuleiro, caractereUsuario);
                
                // Verifica se o usuario venceu
                //ToDo: Este if deve executar apenas se teve ganhador 
                if (teveGanhador(tabuleiro, caractereUsuario)) {
                    //ToDo: Exiba que o usuario ganhou
                    exibirTabuleiro(tabuleiro);
                    exibirVitoriaUsuario();
                    jogoContinua = false;
                }

                //ToDo: defina qual o vaor a variavel abaixo deve possuir
                vezUsuarioJogar = false;
            } else {

                //ToDo: Execute processar vez do computador
                tabuleiro = processarVezComputador(tabuleiro, caractereComputador, caractereUsuario, nivel);

                // Verifica se o computador venceu
                //ToDo: Este if deve executar apenas se teve ganhador
                if (teveGanhador(tabuleiro, caractereComputador)) {
                    //ToDo: Exiba que o computador ganhou
                    exibirTabuleiro(tabuleiro);
                    exibirVitoriaComputador();
                    jogoContinua = false;
                }

                //ToDo: defina qual o valor a variavel abaixo deve possuir
                vezUsuarioJogar = true;
            }
        
            //ToDo: Este if deve executar apenas se o jogo continua E 
            //ocorreu tempate. Utilize o metodo teveEmpate()
            if (teveEmpate(tabuleiro) && jogoContinua) {
                //ToDo: Exiba que ocorreu empate
                exibirTabuleiro(tabuleiro);
                exibirEmpate();
                jogoContinua = false;
            }
        } while (jogoContinua);

        teclado.close();
    }
}
