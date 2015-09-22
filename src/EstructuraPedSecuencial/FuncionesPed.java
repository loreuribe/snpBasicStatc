package EstructuraPedSecuencial;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import snpBasicStatc.Gen;
import snpBasicStatc.SNP;

/**
 * Programa encargado de leer los datos de los archivos planos 
 * con formato:
 */

/** 
 * ID	group	age	sex	snp1	snp2	snp3	snp4
 */

/** 
 * 1	Co	[68,76)	Female	G/G	T/T	A/C	C/T
 */
/** 
 * 2	Ca	[23,59)	Male	G/G	NA	A/C	C/C
 * /
/** 
* Co = Control
*/
/** 
 * Ca = Caso
 */
/** 
 * snp = G/G - G/C - T/T - C/T - C/C - G/T ....
 */


public class FuncionesPed {     
    private ArrayList<Gen>[] snp;
    
    private ArrayList<SNP> snpGEn;
    
    private int contains(ArrayList<Gen> arraySNP, String string){
        for(int i=0; i<arraySNP.size(); i++){
            Gen temp=arraySNP.get(i);
            if(temp.getTipo().equals(string)){return i;}
        }
        return -1;
    }
    
    public ArrayList<String> leer_Archivo(String direccion){
        ArrayList<String> textoArchivo=new ArrayList<>();
        Thread Hilo = new Thread(()->{      
            try {
                BufferedReader archivo = new BufferedReader(new FileReader(new File(direccion)));
                String linea;
                while((linea=archivo.readLine())!=null){
                    textoArchivo.add(linea+"\n");
                }
            }catch(Exception ex){System.out.println("Error: F1 "+ex.getLocalizedMessage());}             
        });
        Hilo.start();
        try {
            Hilo.join();
        }catch(Exception ex){System.out.println("Error: F1 "+ex.getLocalizedMessage());} 
        return textoArchivo;
    }
    
    public ArrayList<Gen>[] llenar_Estructura(ArrayList<String> arrayS){ 
        long tInicio = System.currentTimeMillis();
        Thread Hilo;            
       // Hilo = new Thread(()->{            
            int i, j, cantSNP=1;
            String textoTemp_1;
            String textoTemp_2;
            StringTokenizer  token;
            for(i=0; i<arrayS.size(); i++){
                
                textoTemp_2 = new String();
                token = new StringTokenizer(arrayS.get(i));
                int cont=0;
                while(token.hasMoreTokens()){
                    textoTemp_1 = token.nextToken();
                    if(cont<=3){
                        textoTemp_2 = textoTemp_2.concat(" "+textoTemp_1);
                    }else{
                        if(cont>3 && cont<6 ){
                            switch(cont){
                                case 4:
                                    //Sex
                                    switch(Integer.parseInt(textoTemp_1)){
                                        case 1:
                                            //Male  
                                            textoTemp_2 = textoTemp_2.concat(" Male");
                                            break;
                                        case 2:
                                            //Female    
                                            textoTemp_2 = textoTemp_2.concat(" Female");
                                            break;
                                    } 
                                    break;
                                case 5:
                                    //Phenotype
                                    switch(Integer.parseInt(textoTemp_1)){
                                        case 1:
                                            //Control  
                                            textoTemp_2 = textoTemp_2.concat(" Control");
                                            break;
                                        case 2:
                                            //Case 
                                            textoTemp_2 = textoTemp_2.concat(" Case");
                                            break;
                                    } 
                                    break;
                                default:
                                    break;
                                    //SNP                                                               
                            }
                        }else{
                            textoTemp_2 = textoTemp_2.concat(" "+textoTemp_1+"/"+token.nextToken());
                        }                   
                    }
                    cont++;
                    
                }      
                arrayS.set(i, textoTemp_2);
                //System.out.println(textoTemp_2);
            }
            System.out.println("procesado");
            token = new StringTokenizer(arrayS.get(0));
            while(token.hasMoreTokens()){
                textoTemp_1 = token.nextToken();
                if(textoTemp_1.contains("/")){
                    cantSNP++;
                }
            }            
            snp=new ArrayList[cantSNP];
            for(i=0; i<cantSNP; i++){
                snp[i]=new ArrayList<>();
            }
            for(i=0; i<arrayS.size(); i++){
                int pos=0;
                token=new StringTokenizer(arrayS.get(i));    
                while(token.hasMoreTokens()){
                    textoTemp_1=token.nextToken();
                    if(textoTemp_1.contains("/")){   
                        int posicionEncontrada=contains(snp[pos], textoTemp_1);
                        Gen tempGen;
                        if(posicionEncontrada==-1){
                            tempGen=new Gen();
                            tempGen.setTipo(textoTemp_1);
                            tempGen.setCantidad(1);
                            tempGen.setCantidadCasoHombre((arrayS.get(i).contains("1-Case")||arrayS.get(i).contains("Other cancers")||arrayS.get(i).contains("Colon")||arrayS.get(i).contains("Ca"))&&(arrayS.get(i).contains("Male"))?1:0);
                            tempGen.setCantidadCasoMujer((arrayS.get(i).contains("1-Case")||arrayS.get(i).contains("Other cancers")||arrayS.get(i).contains("Colon")||arrayS.get(i).contains("Ca"))&&(arrayS.get(i).contains("Female"))?1:0);
                            tempGen.setCantidadControlHombre((arrayS.get(i).contains("0-Control")||arrayS.get(i).contains("No cancer")||arrayS.get(i).contains("Co"))&&(arrayS.get(i).contains("Male"))?1:0);
                            tempGen.setCantidadControlMujer((arrayS.get(i).contains("0-Control")||arrayS.get(i).contains("No cancer")||arrayS.get(i).contains("Co"))&&(arrayS.get(i).contains("Female"))?1:0);
                            snp[pos].add(tempGen);
                        }else{
                            tempGen=snp[pos].get(posicionEncontrada);
                            tempGen.setCantidad(tempGen.getCantidad()+1);                       
                            if(arrayS.get(i).contains("0-Control")||arrayS.get(i).contains("No cancer")||arrayS.get(i).contains("Co")){
                                if(arrayS.get(i).contains("Male")){
                                    tempGen.setCantidadControlHombre(tempGen.getCantidadControlHombre()+1);
                                }else{
                                    if(arrayS.get(i).contains("Female")){
                                        tempGen.setCantidadControlMujer(tempGen.getCantidadControlMujer()+1);
                                    }
                                }
                            }else{
                                if(arrayS.get(i).contains("1-Case")||arrayS.get(i).contains("Other cancers")||arrayS.get(i).contains("Colon")||arrayS.get(i).contains("Ca")){
                                    if(arrayS.get(i).contains("Male")){
                                        tempGen.setCantidadCasoHombre(tempGen.getCantidadCasoHombre()+1);
                                    }else{
                                        if(arrayS.get(i).contains("Female")){
                                            tempGen.setCantidadCasoMujer(tempGen.getCantidadCasoMujer()+1);
                                        }
                                    }
                                }
                            }
                            snp[pos].set(posicionEncontrada,tempGen);
                        }  
                        pos++;
                    }                    
                }
            } 
            for(i=0; i<cantSNP; i++){
                for(j=0; j<snp[i].size(); j++){
                Gen tempGen=snp[i].get(j);
                System.out.println("SNP"+(i+1)+":  Tipo:"+tempGen.getTipo()+"  Cant:"+tempGen.getCantidad()+"  CantCasHom:"+tempGen.getCantidadCasoHombre()+"  CantCasMuj"+tempGen.getCantidadCasoMujer()+"  CantContHom:"+tempGen.getCantidadControlHombre()+"  CantContMuj:"+tempGen.getCantidadControlMujer());
                }
                System.out.println("");
            }
            System.out.println("Tiempo consumido en funcion 'Llenar_Estructura':"+((System.currentTimeMillis()-tInicio)/1000.0)+" segundos.\n");
   /*     });
        Hilo.start();
        try {
            Hilo.join();
        }catch(Exception ex){System.out.println("Error: F1 "+ex.getLocalizedMessage());} */
        return snp;
    }        
}