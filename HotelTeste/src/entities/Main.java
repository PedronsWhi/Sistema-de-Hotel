package entities;

import java.util.concurrent.Semaphore;

public class Main {

    private static final int NUM_RECEPCIONISTAS = 5;
    private static final int NUM_CAMAREIRAS = 10;
    private static final int NUM_QUARTOS = 10;
    private static final int MAX_HOSPEDES_POR_QUARTO = 4;
    private static final int NUM_HOSPEDES = 50; // Defina o número total de hóspedes

    public static void main(String[] args) {
        // Inicialização dos quartos
        Quarto[] quartos = new Quarto[NUM_QUARTOS];
        for (int i = 0; i < NUM_QUARTOS; i++) {
            quartos[i] = new Quarto();
            quartos[i].setNumero(i + 1); // Atribuindo o número do quarto corretamente
        }

        // Semáforo para controlar o acesso aos quartos
        Semaphore acessoQuartos = new Semaphore(NUM_QUARTOS, true);

        // Inicialização dos recepcionistas
        Recepcionista[] recepcionistas = new Recepcionista[NUM_RECEPCIONISTAS];
        for (int i = 0; i < NUM_RECEPCIONISTAS; i++) {
            recepcionistas[i] = new Recepcionista(acessoQuartos, quartos, MAX_HOSPEDES_POR_QUARTO);
            recepcionistas[i].setName("" + (i + 1));
            recepcionistas[i].start();
        }

        // Inicialização das camareiras
        Camareira[] camareiras = new Camareira[NUM_CAMAREIRAS];
        for (int i = 0; i < NUM_CAMAREIRAS; i++) {
            camareiras[i] = new Camareira(quartos, acessoQuartos);
            camareiras[i].setName("" + (i + 1));
            camareiras[i].start();
        }

        // Inicialização dos hóspedes
        Hospede[] hospedes = new Hospede[NUM_HOSPEDES];
        for (int i = 0; i < NUM_HOSPEDES; i++) {
            int quartoIndex = i % NUM_QUARTOS; // Determina o índice do quarto para o hóspede
            hospedes[i] = new Hospede(quartos[quartoIndex], acessoQuartos);
            hospedes[i].setName("" + (i + 1));
            hospedes[i].start();
        }
    }
}