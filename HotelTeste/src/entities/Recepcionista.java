package entities;

import java.util.concurrent.Semaphore;

public class Recepcionista extends Thread {
    private Semaphore acessoQuartos;
    private Quarto[] quartos;
    private int maxHospedesPorQuarto;
    private static final int MAX_TENTATIVAS = 2; // Número máximo de tentativas antes de fazer uma reclamação
    private int tentativas;

    public Recepcionista(Semaphore acessoQuartos, Quarto[] quartos, int maxHospedesPorQuarto) {
        this.acessoQuartos = acessoQuartos;
        this.quartos = quartos;
        this.maxHospedesPorQuarto = maxHospedesPorQuarto;
        this.tentativas = 0;
    }

    @Override
    public void run() {
    	while (true) {
            try {
                acessoQuartos.acquire(); // Recepcionista aguarda acesso aos quartos
                if (alocaHospede()) {
                    tentativas = 0; // Reinicia o contador de tentativas se a alocação foi bem-sucedida
                } else {
                    tentativas++;
                    if (tentativas >= MAX_TENTATIVAS) {
                    	System.out.println("Hóspede " + this.getName() + " fez reclamação após " + MAX_TENTATIVAS + " tentativas sem sucesso.");
                        // Aqui você pode adicionar lógica adicional para lidar com a reclamação, se necessário
                        // Por exemplo, você pode chamar um método para registrar a reclamação em algum lugar
                        tentativas = 0; // Reinicia o contador de tentativas após a reclamação
                    }
                }
                acessoQuartos.release(); // Libera o acesso aos quartos
                Thread.sleep(1000); // Espera um segundo antes de atender o próximo hóspede
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean alocaHospede() {
        for (Quarto quarto : quartos) {
            if (quarto.getDisponibilidade() == DisponibilidadeEnum.VAGO) { // Verifica se o quarto está vago
                if (quarto.getQuantidade() < maxHospedesPorQuarto) { // Verifica se o quarto não está cheio
                    quarto.setDisponibilidade(DisponibilidadeEnum.OCUPADO); // Marca o quarto como ocupado
                    System.out.println("Hóspede alocado no quarto " + quarto);
                    return true; // Sai do método após alocar o hóspede
                }
            }
        }
        // Se nenhum quarto estiver disponível, esperar até que um quarto esteja livre
        System.out.println("Todos os quartos estão ocupados. Aguardando disponibilidade.");
        return false;
    }
}

