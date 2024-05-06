package entities;
import java.util.concurrent.Semaphore;

public class Recepcionista extends Thread {
    private Semaphore acessoQuartos;
    private Quarto[] quartos;
    private int maxHospedesPorQuarto;

    public Recepcionista(Semaphore acessoQuartos, Quarto[] quartos, int maxHospedesPorQuarto) {
        this.acessoQuartos = acessoQuartos;
        this.quartos = quartos;
        this.maxHospedesPorQuarto = maxHospedesPorQuarto;
    }

    @Override
    public void run() {
        while (true) {
            try {
                acessoQuartos.acquire(); // Recepcionista aguarda acesso aos quartos
                alocaHospede();
                acessoQuartos.release(); // Libera o acesso aos quartos
                Thread.sleep(1000); // Espera um segundo antes de atender o próximo hóspede
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void alocaHospede() {
        for (Quarto quarto : quartos) {
            if (quarto.getDisponibilidade() == DisponibilidadeEnum.VAGO) { // Verifica se o quarto está vago
                if (quarto.getQuantidade() < maxHospedesPorQuarto) { // Verifica se o quarto não está cheio
                    quarto.setDisponibilidade(DisponibilidadeEnum.OCUPADO); // Marca o quarto como ocupado
                    System.out.println("Hóspede alocado no quarto " + quarto);
                    return; // Sai do método após alocar o hóspede
                }
            }
        }
        // Se nenhum quarto estiver disponível, esperar até que um quarto esteja livre
        System.out.println("Todos os quartos estão ocupados. Aguardando disponibilidade.");
    }
}
