/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class Filtro extends FileFilter {
    public boolean accept(File f) {
           if (f.isDirectory()) {
               return true;
           }
       String filename = f.getName();
    if (filename.endsWith(".FDK")) {
                  return true;
               } else {
                  return false;
               }
           }
    public String getDescription() {
                 return ".FDK";
               }
}
