package GUI;


public enum Imagen {
    
    OTIMO("otimo.png"),REGULAR("regular.png"),PESSIMO("pessimo.png"),RUIM("ruim.png");
   
    private final String nome;
    
    Imagen(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    
}
