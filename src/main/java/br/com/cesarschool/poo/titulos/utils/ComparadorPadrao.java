package br.com.cesarschool.poo.titulos.utils;

// Classe base para comparadores
public abstract class ComparadorPadrao implements Comparador {
    @Override
    public int comparar(Comparavel c1, Comparavel c2) {
        // Implementação padrão: 0 (igualdade)
        return 0; // Deve ser sobrescrito por subclasses
    }
}
