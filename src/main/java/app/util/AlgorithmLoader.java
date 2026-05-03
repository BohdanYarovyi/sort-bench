package app.util;

import app.util.algorithm.SortAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class AlgorithmLoader {

   public Set<SortAlgorithm> loadBuiltinAlgorithms() {
       Set<SortAlgorithm> algorithms = loadFromPackage(Path.of("app", "util", "algorithm", "impl"), SortAlgorithm.class);

       algorithms.forEach(sortAlgorithm -> {
           String oldName = sortAlgorithm.getName();
           String suffix = "(Default)";

           sortAlgorithm.setName(oldName + " " + suffix);
       });

       return algorithms;
   }

   private <T> Set<T> loadFromPackage(Path packageName, Class<T> superClass) {
       try (InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.toString());) {
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

           return bufferedReader.lines()
                   .filter(filename -> filename.endsWith(".class"))
                   .map(packageName::resolve)
                   .map(this::createClass)
                   .filter(clazz -> clazz.getSuperclass().getName().equals(superClass.getName()))
                   .map(clazz -> {
                       try {
                           return superClass.cast(clazz.getDeclaredConstructor().newInstance());
                       } catch (Exception e) {
                           throw new RuntimeException(e);
                       }
                   })
                   .collect(Collectors.toSet());
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

   private Class<?> createClass(Path path) {
       try {
           String name = path.toString().replace(".class", "").replace(File.separatorChar, '.');
           return Class.forName(name);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }

}
