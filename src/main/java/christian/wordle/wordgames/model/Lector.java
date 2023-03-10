package christian.wordle.wordgames.model;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Lector {

    public List<String> leer (String archivo) throws Exception{
        List<String> palabras = new ArrayList<>();

        File file = ResourceUtils.getFile("classpath:"+archivo);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String linea;

        while ((linea = reader.readLine()) != null){

            String palabra = linea.trim();
            palabras.add(palabra);

        }

        reader.close();

        return palabras;
    }

}
