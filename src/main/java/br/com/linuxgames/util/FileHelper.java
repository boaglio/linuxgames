package br.com.linuxgames.util;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.linuxgames.controller.crud.TelaDeJogoAction;

 public class FileHelper {


     public static Collection<String> getAllFiles(String path) {

    	 Logger logger = Logger.getLogger(TelaDeJogoAction.class);

    	 logger.info("path=["+path+"]");
         Collection<String> listaDeNomes = new ArrayList<String>();
         File dir = new File(path);
         for (File file : dir.listFiles()) {
             listaDeNomes.add(file.getName());
         }
         return listaDeNomes;
     }
 }
