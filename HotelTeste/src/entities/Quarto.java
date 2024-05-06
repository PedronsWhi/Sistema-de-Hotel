package entities;

import java.util.ArrayList;

public class Quarto {
    private DisponibilidadeEnum disponibilidadeQuarto; // Estado de disponibilidade do quarto
    private EnumPosseChave posseChave; // Quem possui a chave do quarto
    private ArrayList<Hospede> hospedes; // Lista de hóspedes no quarto
    
    // Construtor padrão do quarto
    public Quarto(){
        this.setDisponibilidade(DisponibilidadeEnum.VAGO); // Define o quarto como vago por padrão
        this.setHospedes(new ArrayList<Hospede>()); // Inicializa a lista de hóspedes como vazia
        this.posseChave = EnumPosseChave.HOTEL; // Inicializa a posse da chave como do hotel
    }

    // Método para obter a disponibilidade do quarto
    public DisponibilidadeEnum getDisponibilidade() {
        return disponibilidadeQuarto;
    }

    // Método para definir a disponibilidade do quarto
    public void setDisponibilidade(DisponibilidadeEnum disponibilidade) {
        this.disponibilidadeQuarto = disponibilidade;
    }

    // Método para obter a lista de hóspedes do quarto
    public ArrayList<Hospede> getHospedes() {
        return hospedes;
    }

    // Método para definir a lista de hóspedes do quarto
    public void setHospedes(ArrayList<Hospede> hospedes) {
        this.hospedes = hospedes;
    }
    
    // Método para adicionar um hóspede à lista de hóspedes do quarto
    public void setHospedes(Hospede hospede) {
        this.hospedes.add(hospede);
    }
    
    // Método para obter quem possui a chave do quarto
    public EnumPosseChave getPosseChave() {
        return posseChave;
    }

    // Método protegido para definir quem possui a chave do quarto
    protected void setPosseChave(EnumPosseChave posseChave) {
        this.posseChave = posseChave;
    }

    // Método para obter a quantidade de hóspedes no quarto
    public int getQuantidade() {
        return this.getHospedes().size();
    }
}