package br.com.linuxgames.controller.filtros.conversores;

import java.util.Map;

import org.mentawai.converter.Converter;
import org.mentawai.converter.DateConverter;
import org.mentawai.core.Action;
import org.mentawai.filter.ConversionFilter;
   public class HelloConversionFilter extends ConversionFilter {  
             
        public void prepareConverters(Map<String, Converter> converters, Action action, String innerAction) {  
    
          converters.put("date", new DateConverter());  
     
     }  
 }  