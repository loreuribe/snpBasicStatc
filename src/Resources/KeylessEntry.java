/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Loena
 */
class KeylessEntry {
 
   static class Key {
      Integer id;
 
      Key(Integer id) {
         this.id = id;
      }
 
      @Override
      public int hashCode() {
         return id.hashCode();
      }
   }
 
   public static void main(String[] args) {
      Map m = new HashMap();
      while (true)
         for (int i = 0; i < 10000; i++)
            if (!m.containsKey(new Key(i)))
               m.put(new Key(i), "Number:" + i);
    
   }
}