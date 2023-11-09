package one.digitalinovation.laboojava.entidade.constantes;


public enum Genero {

    CASA(15),

    CONSTRUCAO(10),
	
	DESIGN(5);

    //ROMANCE(5);

    private double fator;

    /**
     * Construtor.
     * @param fator Valor por tipo que influencia no cálculo do frete.
     */
    Genero(double fator) {
        this.fator = fator / 100;
    }

    public double getFator() {
        return fator;
    }
}
