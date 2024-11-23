package br.com.cesarschool.poo.titulos.utils;

public class Ordenador {

    public static void ordenar(Comparavel[] comps) {
        for (int i = 0; i < comps.length - 1; i++) {
            for (int j = 0; j < comps.length - i - 1; j++) {
                if (comps[j].comparar(comps[j + 1]) > 0) {
                    Comparavel temp = comps[j];
                    comps[j] = comps[j + 1];
                    comps[j + 1] = temp;
                }
            }
        }
    }

    public static void ordenar(Comparavel[] comps, Comparador comp) {
        for (int i = 0; i < comps.length - 1; i++) {
            for (int j = 0; j < comps.length - i - 1; j++) {
                if (comp.comparar(comps[j], comps[j + 1]) > 0) {
                    Comparavel temp = comps[j];
                    comps[j] = comps[j + 1];
                    comps[j + 1] = temp;
                }
            }
        }
    }
}
