package christian.wordle.wordgames.controller;

import christian.wordle.wordgames.model.Lector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class PalabraController {


    @GetMapping("/palabras")
    public ResponseEntity<?> getAllWords() throws Exception{

        Lector lector = new Lector();

        List<String> allWords = lector.leer("static/palabras.txt");

        if (allWords.isEmpty()){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allWords);

    }


    @GetMapping("/palabras/aleatoria")
    public ResponseEntity<?> getRandomWord() throws Exception {
        Lector lector = new Lector();

        List<String> allWords = lector.leer("static/palabras.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Random random = new Random();

        int randomIndex = random.nextInt(allWords.size()); // Generar índice aleatorio

        return ResponseEntity.ok(allWords.get(randomIndex));
    }


    @GetMapping("/palabras/aleatoria/{cantidad}")
    public ResponseEntity<?> getMultipleRandomWords(@PathVariable int cantidad) throws Exception{

        List<String> randomWords = new ArrayList<>();

        Lector lector = new Lector();

        List<String> allWords = lector.leer("static/palabras.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Random random = new Random();

        for(int i = 0; i < cantidad; i++){
            int randomIndex = random.nextInt(allWords.size());
            randomWords.add(allWords.get(randomIndex));
        }

        return ResponseEntity.ok(randomWords);

    }


    @GetMapping("/palabras/empiezan/{cadena}")
    public ResponseEntity<?> getWordsStartingWith(@PathVariable String cadena) throws Exception {
        Lector lector = new Lector();
        List<String> allWords = lector.leer("static/palabras.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> wordsStartingWith = new ArrayList<>();

        for (String word : allWords) {
            if (word.startsWith(cadena)) {
                wordsStartingWith.add(word);
            }
        }

        return ResponseEntity.ok(wordsStartingWith);
    }


    @GetMapping("/palabras/terminan/{cadena}")
    public ResponseEntity<?> getWordsEndingWith(@PathVariable String cadena) throws Exception {
        Lector lector = new Lector();
        List<String> allWords = lector.leer("static/palabras.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> wordsEndingWith = new ArrayList<>();

        for (String word : allWords) {
            if (word.endsWith(cadena)) {
                wordsEndingWith.add(word);
            }
        }

        return ResponseEntity.ok(wordsEndingWith);
    }


    @GetMapping("/palabras/contienen/{cadena}")
    public ResponseEntity<?> getWordsContaining(@PathVariable String cadena) throws Exception {
        Lector lector = new Lector();
        List<String> allWords = lector.leer("static/palabras.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> wordsContaining = new ArrayList<>();


        for (String word : allWords) {
            if (word.contains(cadena)) {
                wordsContaining.add(word);
            }
        }

        return ResponseEntity.ok(wordsContaining);
    }

}
