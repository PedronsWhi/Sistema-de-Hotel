package entities;

import java.util.concurrent.Semaphore;

public class Camareira extends Thread {

    private Quarto[] quartos; // Array de quartos que a camareira vai limpar
    private Semaphore acessoQuartos; // Semáforo para controle de acesso aos quartos

    public Camareira(Quarto[] quartos, Semaphore acessoQuartos) {
        this.quartos = quartos; // Inicializa o array de quartos
        this.acessoQuartos = acessoQuartos; // Inicializa o semáforo de acesso aos quartos
    }

    private void limparQuarto(Quarto quarto) {
        if (quarto.getPosseChave().equals(EnumPosseChave.HOTEL)) { // Verifica se a camareira tem a chave do quarto
            quarto.setPosseChave(EnumPosseChave.CAMAREIRA); // Define que a camareira está com a chave do quarto
            System.out.println("A Camareira está limpando o quarto"); // Mensagem indicando que a camareira está limpando o quarto
            
            try {
                Thread.sleep(5000); // Simula o tempo de limpeza do quarto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            quarto.setPosseChave(EnumPosseChave.HOTEL); // Devolve a chave do quarto após a limpeza
            System.out.println("A Camareira terminou de limpar");
        }
    }
    
    @Override
    public void run() {
        while (true) { // Loop infinito para verificar continuamente os quartos
            for (Quarto quarto : quartos) { // Percorre todos os quartos
                if (quarto.getDisponibilidade() == DisponibilidadeEnum.OCUPADO) { // Verifica se o quarto está ocupado
                    limparQuarto(quarto); // Limpa o quarto se estiver ocupado
                }
            }
        }
    }
}
