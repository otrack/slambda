package org.example;

import org.danekja.java.util.function.serializable.SerializableBiFunction;
import org.infinispan.creson.Factory;
import org.infinispan.creson.Shared;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by pedro on 9/21/17.
 */

public class WordCount {

    public static void main(String args[]) throws Exception {
        Factory.get("54.77.4.130:11222"); // CRESON initialization
        // Factory.get("localhost:11222"); // CRESON initialization
        List<String> lines = importFile();
        long time1 = System.currentTimeMillis();
        Map<String,Long> words = countWords(lines);
        long time2 = System.currentTimeMillis();
        System.out.println(words.toString());
        WordCount wordCount = new WordCount();
        long time3 = System.currentTimeMillis();
        Map<String,Long> words2 = wordCount.countWordsParallel(lines);
        long time4 = System.currentTimeMillis();
        System.out.println(words2);


        Thread.sleep(1000);


        if (words.equals(words2)) System.out.println("OK");
        else
            System.out.println("FALSE");

        checkMaps(words,words2);

        System.out.println(time2-time1);
        System.out.println(time4-time3);

    }

    public static Map<String,Long> countWords(List<String> lines){

        Map<String,Long> words = new HashMap<>();

        lines.forEach((String line)->{
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()){
                String word = st.nextToken();
                if (words.containsKey(word)){
                    words.put(word,words.get(word)+1);
                }else
                    words.put(word,1L);
            }
        });


        return words;


    }

    @Shared(forceNew = true) Map<String,Long> words = new HashMap<>();

    public Map<String,Long> countWordsParallel(List<String> lines){

        lines.stream().parallel().forEach((String line)->{
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()){
                String word = st.nextToken();
                words.compute(word,
                        (SerializableBiFunction<String, Long, Long>) (k, v) -> (v==null) ? 1L : v+1L);
            }
        });


        return words;


    }

    public static void checkMaps(Map<String,Long> m1, Map<String,Long> m2){
        m1.keySet().forEach((key)->{
                    if (!m2.containsKey(key))
                        System.out.println("FAIL in key "+ key);
                    else if (!m1.get(key).equals((m2.get(key)))) {
                        System.out.println("FAIL in value of key " + key);
                        System.out.println("v1="+m1.get(key)+"  v2="+m2.get(key));
                        System.out.println(m1.get(key).equals((m2.get(key))));
                    }

                }

        );

    }



    public static List<String> importFile(){
        FileReader fis = null;
        List<String> lines = new LinkedList<String>();
        try {
            //  fis = new FileReader("quijote.txt");
            fis = new FileReader(
                    WordCount.class.getClassLoader().getResource("lipsum.txt").getFile());

            BufferedReader dis = new BufferedReader(fis);
            String line;


            while ((line = dis.readLine()) != null) {
                lines.add(line);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private List<String> getResourceFiles( String path ) throws IOException {
        List<String> filenames = new ArrayList<>();

        try(
                InputStream in = getResourceAsStream( path );
                BufferedReader br = new BufferedReader( new InputStreamReader( in ) ) ) {
            String resource;

            while( (resource = br.readLine()) != null ) {
                filenames.add( resource );
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream( String resource ) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream( resource );

        return in == null ? getClass().getResourceAsStream( resource ) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }





}
