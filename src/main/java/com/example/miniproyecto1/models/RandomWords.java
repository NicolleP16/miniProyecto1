package com.example.miniproyecto1.models;

import java.util.Random;

public class RandomWords {
    private String[] easyWords = {
            "casa", "perro", "gato", "mesa", "silla", "libro", "coche", "parque", "escuela", "lápiz",
            "puerta", "cama", "reloj", "taza", "papel", "ventana", "camisa", "zapato", "nube", "río",
            "tren", "mar", "sol", "luna", "lluvia", "flor", "fruta", "calle", "viento", "tierra",
            "pared", "montaña", "puente", "cielo", "brazo", "pierna", "hueso", "color", "sombra", "calor",
            "frío", "niño", "niña", "amigo", "gente", "foto", "leche", "queso", "azúcar", "sal"
    };
    private String[] intermediateWords = {
            "elefante", "escalera", "automóvil", "computadora", "biblioteca", "cocodrilo", "electricidad", "paraguas", "helicóptero", "murciélago",
            "astronauta", "circunferencia", "hipopótamo", "terremoto", "termómetro", "enciclopedia", "estantería", "extraterrestre", "arquitectura", "catástrofe",
            "submarino", "laberinto", "contaminación", "motocicleta", "canguro", "triciclo", "fotografía", "pentágono", "radiografía", "cronómetro",
            "kilogramo", "diccionario", "microondas", "televisión", "escultura", "aeropuerto", "caligrafía", "panadería", "neumonía", "narcisista",
            "psíquico", "hipnosis", "camuflaje", "amnistía", "anacronismo", "cleptomanía", "antropología", "bioquímica", "filantropía", "ideología"
    };

    private String[] difficultWords = {
            "ambigüedad", "exacerbación", "intransigencia", "contemporáneo", "subyugación", "ineludible", "irreverente", "sobresaliente", "intempestivo", "resiliencia",
            "premonición", "transgresión", "efervescencia", "meticuloso", "congruencia", "indescifrable", "paralelepípedo", "introspectivo", "circunspecto", "inconmensurable",
            "aprehensión", "vicisitud", "magnánimo", "consternación", "superfluo", "incongruencia", "idiosincrasia", "obnubilado", "equidistante", "intrincado",
            "indefectible", "perplejidad", "voluptuoso", "antagónico", "circunscribir", "antropomorfismo", "cataclismo", "amalgama", "contingencia", "interjección",
            "proliferación", "transcultural", "reciprocidad", "sobrecogedor", "prerrogativa", "exorbitante", "inquebrantable", "convalecencia", "desarraigo", "flemático"
    };

    public String getRandomWord(int level) {
        if (level < 10) {
            return easyWords[new Random().nextInt(easyWords.length)];
        } else if (level < 25) {
            return intermediateWords[new Random().nextInt(intermediateWords.length)];
        } else {
            return difficultWords[new Random().nextInt(difficultWords.length)];
        }
    }
}
