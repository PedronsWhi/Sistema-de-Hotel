package entities;

import java.util.concurrent.Semaphore;

public class Hospede extends Thread {

    private Quarto quarto; 
    private Semaphore acessoQuartos;
    
    
    // Construtores

    public Hospede(Quarto quarto, Semaphore acessoQuartos) {
        this.quarto = quarto;
        this.acessoQuartos = acessoQuartos;
    }
    
    public Hospede(Quarto quarto, Semaphore acessoQuartos, String nome) {
        this.quarto = quarto;
    }

    // Métodos específicos do hospede

    public void saidaPasseio() {
        this.quarto.setPosseChave(EnumPosseChave.HOTEL);
        System.out.println("Hóspede '" + Thread.currentThread().getName() + "' está passeando.");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (quarto != null) {
                // Mostra o hóspede dentro do quarto
                System.out.println("Hóspede " + Thread.currentThread().getName() + " se encontra no quarto: " + quarto);
                
                // Método para o passeio
                saidaPasseio();

                // Mensagem de retorno do passeio
                System.out.println("Hóspede " + Thread.currentThread().getName() + " voltou do passeio");
                
                // Espera 5 segundos para poder sair novamente
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
