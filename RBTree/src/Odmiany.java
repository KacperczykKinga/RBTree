import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Odmiany {
	static DrzewoRB2 drzewo;
	Map<String,String[]> dictionary;

	 private static Map<String,String[]> readDictionary() {
         try {
                 Map<String, String[]> dictionary =
                                 Files.readAllLines(Paths.get("odmiany.txt"), Charset.forName("UTF-8"))
                                 .stream()
                                 .collect(Collectors.toMap(
                                 (s) -> s.split(",")[0].trim(),
                                 (s) -> Arrays.stream(s.split(",")).skip(1).map((s2) ->
                                 s2.trim()).toArray(String[]::new),
                                 (oldValue, newValue) -> oldValue)
                                 );
                 return dictionary;
         } catch (IOException e) {
                 e.printStackTrace();
         }
         return null;
 }
	 public void Procenty() {
		 drzewo.procenty(drzewo.root);
	      System.out.println(drzewo.ileProcent());
	 }
	 public void inneProcenty() {
		 drzewo.inneProcenty(drzewo.root);
	      System.out.println(drzewo.inneIleProcent());
	 }
	public void rob(Map<String,String[]> dictionary) {
	
		drzewo=new DrzewoRB2("nic");
		for(Map.Entry<String,String[]> entry:dictionary.entrySet()) {
			String key=entry.getKey();
			String[] value=entry.getValue();
			drzewo.dodajStringa(key,value);
		}
	}
	
	public void odmien(String haslo) {
		String[] znalezisko;
		Element sprawdz=drzewo.wyszukajStringa(haslo);
		if(sprawdz!=null) {znalezisko=sprawdz.getDrugieDno();
		for(int i=0;i<znalezisko.length;i++) {
			System.out.print(znalezisko[i]+" ");
		}
		}
	}
	
	public void dodajHaslo(String haslo,String[]odmiany) {
		drzewo.dodajStringa(haslo,odmiany);
		
	}
	
	public void usunHaslo(String haslo) {
		drzewo.usunStringa(haslo);
	}
	public void inOrde() {
		drzewo.InOrder();
	}
	
	public void drukujDrzewo() {
		drzewo.drukuj();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Odmiany slownik=new Odmiany();
       Map<String,String[]> slowa=slownik.readDictionary();
      slownik.rob(slowa);
     
      slownik.odmien("¿³ób");
      slownik.Procenty();
      slownik.inneProcenty();
      String[] slo ={"maja","maja"};
      slownik.dodajHaslo("¿³ów",slo);
      slownik.usunHaslo("¿³opac");
    //  slownik.drukujDrzewo();
    //  slownik.inOrde();
    
	}

}


