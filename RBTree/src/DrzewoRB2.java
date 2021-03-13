
public class DrzewoRB2 {

Element root;
Element wartownik;
double licznik;
double mianownik;
double licznik2;
double mianownik2;
 
public DrzewoRB2(String value) {
	root=new Element(value);
	wartownik=new Element(null);
	wartownik.setColour("BLACK");
	root.setColour("BLACK");
	root.setParent(wartownik);
	root.setLeftSon(wartownik);
	root.setRightSon(wartownik);
	wartownik.setParent(null);
	wartownik.setRightSon(null);
	wartownik.setLeftSon(null);
	licznik=0;
	mianownik=0;
	licznik2=0;
	mianownik2=0;
}


public void dodajStringa(String value,String[] drugie) {
	Element nowy=new Element(value);
	nowy.setLeftSon(wartownik);
	nowy.setRightSon(wartownik);
	Element rodzicObecnego=null;
	Element obecny=root;
	Komparator porownaj=new Komparator();
	nowy.setDrugieDno(drugie);
	while(obecny!=wartownik) {
		if(porownaj.compare((String)nowy.getValue(),(String)obecny.getValue())<0) {
			rodzicObecnego=obecny;
			obecny=obecny.getLeftSon();
			
		}
		else 
		{   rodzicObecnego=obecny;
			obecny=obecny.getRightSon();
		}
	}
	if(porownaj.compare((String)nowy.getValue(),(String)rodzicObecnego.getValue())<0)
	rodzicObecnego.setLeftSon(nowy);
	else rodzicObecnego.setRightSon(nowy);
	nowy.setParent(rodzicObecnego);
//	System.out.println(nowy.getValue()+" "+rodzicObecnego.getValue());
	naprawDodanie(nowy);
}

public void naprawDodanie(Element nowy) {
	Element rodzic=nowy.getParent();
	Element dziadek=rodzic.getParent();
	Element stryjek=wartownik;
	if(rodzic==wartownik) {return;}
	else
	{
	if(dziadek==wartownik) stryjek=wartownik;
	else if(rodzic==dziadek.getLeftSon())  
	{if(dziadek.getRightSon()==null) stryjek=wartownik; 
	else stryjek=dziadek.getRightSon(); }
	
	else stryjek=dziadek.getLeftSon();
	//System.out.println(wartownik+" "+root+" "+rodzic.getValue()+" "+root.getValue()+" "+root.getParent()+" "+rodzic.getParent());
	//System.out.println(dziadek.getParent()+" "+dziadek +" "+stryjek);
	if(rodzic.getColour().equals("RED") && stryjek.getColour().equals("RED")) {
		rodzic.setColour("BLACK");
		stryjek.setColour("BLACK");
		dziadek.setColour("RED");
		naprawDodanie(dziadek);
	}
	else if(rodzic.getColour().equals("RED") && stryjek.getColour().equals("BLACK") && rodzic==dziadek.getLeftSon()) {
		if(nowy==rodzic.getRightSon()) {
			lewaRotacja(rodzic);
			dziadek.setColour("RED");
			nowy.setColour("BLACK");
			prawaRotacja(dziadek);
		}
		else {
		dziadek.setColour("RED");
		rodzic.setColour("BLACK");
		prawaRotacja(dziadek);
		}
	}
	else if(rodzic.getColour().equals("RED") && stryjek.getColour().equals("BLACK") && rodzic==dziadek.getRightSon()) {
		if(nowy==rodzic.getLeftSon()) {
			prawaRotacja(rodzic);
			dziadek.setColour("RED");
			nowy.setColour("BLACK");
			lewaRotacja(dziadek);
		}
		else {
		dziadek.setColour("RED");
		rodzic.setColour("BLACK");
		lewaRotacja(dziadek);
		}
	}
	root.setColour("BLACK");
	}
}

   
public Element wyszukajStringa(String value) {
	Element obecny=root;
	Komparator porownaj=new Komparator();
	while(obecny!=wartownik && value!=(String)obecny.getValue() && obecny!=null) {
		if(porownaj.compare(value,(String)obecny.getValue())<0) {
			obecny=obecny.getLeftSon();
		}
		else if(obecny!=null && porownaj.compare(value,(String)obecny.getValue())==0  ) break;
		else obecny=obecny.getRightSon();
	}
	if(obecny==wartownik) {System.out.println("Nie ma takiego elementu");return null;}
	return obecny;
}

public Element minimum(Element odTegoMomentu) {
	Element minimalny=odTegoMomentu;
	while(minimalny.getLeftSon()!=wartownik) minimalny=minimalny.getLeftSon();
	return minimalny;
}

public Element maksimum(Element odTegoMomentu) {
	Element maksymalny=odTegoMomentu;
	while(maksymalny.getRightSon()!=wartownik) maksymalny=maksymalny.getRightSon();
	return maksymalny;
}
public Element successor(Element obecny) {
	if(obecny.getRightSon()!=wartownik) return minimum(obecny.getRightSon());
	else {
		Element nastepnik=obecny.getParent();
		if(obecny==nastepnik.getLeftSon()) return nastepnik;
		else {
			while(nastepnik!=wartownik && obecny==nastepnik.getRightSon()) {
				obecny=nastepnik;
				nastepnik=nastepnik.getParent();
			}
			return nastepnik;
		}
	}
}
public void usunStringa(String value) {
	Element doUsuniecia=null;
	Element usun=wyszukajStringa(value);
	if(usun==null) {
		System.out.println("Nie ma takiego elementu");
		return;
	}
	if(usun!=wartownik) {
	Element doZamiany=wartownik;
	if(usun.getLeftSon()!=wartownik && usun.getRightSon()!=wartownik)  doUsuniecia=successor(usun);
	else doUsuniecia=usun;
	if(doUsuniecia.getLeftSon()==wartownik && doUsuniecia.getRightSon()==wartownik) {
		Element rodzic=doUsuniecia.getParent();
		if(doUsuniecia==rodzic.getLeftSon()) rodzic.setLeftSon(wartownik);
		else rodzic.setRightSon(wartownik);
	}
	else if(doUsuniecia.getLeftSon()==wartownik && doUsuniecia.getRightSon()!=wartownik) {
		Element rodzic=doUsuniecia.getParent();
	    Element noweDziecko=doUsuniecia.getRightSon();
	    noweDziecko.setParent(rodzic);
	    if(doUsuniecia==rodzic.getLeftSon()) rodzic.setLeftSon(noweDziecko);
	    else rodzic.setRightSon(noweDziecko);
	}
	else if(doUsuniecia.getLeftSon()!=wartownik && doUsuniecia.getRightSon()==wartownik) {
		Element rodzic=doUsuniecia.getParent();
	    Element noweDziecko=doUsuniecia.getLeftSon();
	    noweDziecko.setParent(rodzic);
	    if(doUsuniecia==rodzic.getLeftSon()) rodzic.setLeftSon(noweDziecko);
	    else rodzic.setRightSon(noweDziecko);
	}
	if(doUsuniecia!=usun) {
		usun.setValue(doUsuniecia.getValue());
		usun.setDrugieDno(doUsuniecia.getDrugieDno());
	}
	}
	if(doUsuniecia.getColour().equals("BLACK")) {
		if(doUsuniecia.getLeftSon()!=wartownik)
	naprawUsuniecie(doUsuniecia.getLeftSon());
		else if(doUsuniecia.getRightSon()!=wartownik) naprawUsuniecie(doUsuniecia.getRightSon());
		else {
			wartownik.setParent(doUsuniecia.getParent());
			naprawUsuniecie(doUsuniecia.getLeftSon());
			
		}
	}
}

public void naprawUsuniecie( Element poczatek) {
	
	while(poczatek!=root && poczatek.getColour().equals("BLACK")) {
	//	System.out.println("POCZATEK"+poczatek.getValue());
		Element rodzic=poczatek.getParent();
	
		if(poczatek==rodzic.getLeftSon()) {
			Element brat=rodzic.getRightSon();
			Element lewySyn=brat.getLeftSon();
			Element prawySyn=brat.getRightSon();
			if(brat.getColour().equals("RED")) {
				brat.setColour("BLACK");
				rodzic.setColour("RED");
				lewaRotacja(rodzic);
				rodzic=poczatek.getParent();
				brat=rodzic.getRightSon();
				lewySyn=brat.getLeftSon();
			    prawySyn=brat.getRightSon();
			}
			if(brat.getColour().equals("BLACK") && prawySyn.getColour().equals("BLACK") && lewySyn.getColour().equals("BLACK")){
			brat.setColour("RED");
		    poczatek=rodzic;
			}
			else if(brat.getColour().equals("BLACK") && prawySyn.getColour().equals("BLACK") && lewySyn.getColour().equals("RED")) {
				lewySyn.setColour("BLACK");
				brat.setColour("RED");
				prawaRotacja(brat);
				rodzic=poczatek.getParent();
				brat=rodzic.getRightSon();
				lewySyn=brat.getLeftSon();
			    prawySyn=brat.getRightSon();
			}
			else  if(brat.getColour().equals("BLACK") && prawySyn.getColour().equals("RED")) {
			   brat.setColour(rodzic.getColour());
				prawySyn.setColour("BLACK");
				rodzic.setColour("BLACK");
				poczatek=root;
			}

			
			
		}
		if(poczatek==rodzic.getRightSon()) {
			Element brat=rodzic.getLeftSon();
			Element lewySyn=brat.getLeftSon();
			Element prawySyn=brat.getRightSon();
			if(brat.getColour().equals("RED")) {
				brat.setColour("BLACK");
				rodzic.setColour("RED");
				prawaRotacja(rodzic);
				rodzic=poczatek.getParent();
				brat=rodzic.getLeftSon();
				lewySyn=brat.getLeftSon();
			    prawySyn=brat.getRightSon();
			}
			if(brat.getColour().equals("BLACK") && prawySyn.getColour().equals("BLACK") && lewySyn.getColour().equals("BLACK")){
			brat.setColour("RED");
		poczatek=rodzic;
			}
			else if(brat.getColour().equals("BLACK") && lewySyn.getColour().equals("BLACK") && prawySyn.getColour().equals("RED")) {
				prawySyn.setColour("BLACK");
				brat.setColour("RED");
				lewaRotacja(brat);
				rodzic=poczatek.getParent();
				brat=rodzic.getRightSon();
				lewySyn=brat.getLeftSon();
			    prawySyn=brat.getRightSon();
			}
			else  if(brat.getColour().equals("BLACK") && lewySyn.getColour().equals("RED")) {
			   brat.setColour(rodzic.getColour());
			   lewySyn.setColour("BLACK");
				rodzic.setColour("BLACK");
				poczatek=root;
			}	
		}
	}
	poczatek.setColour("BLACK");
}

public void pokoloruj(Element poczatek) {
	poczatek.setColour("BLACK");
}

public void lewaRotacja(Element doRotacji){
	doRotacji=doRotacji.getRightSon();
	Element rodzic=doRotacji.getParent();
	Element leftSon=doRotacji.getLeftSon();
	Element dziadek=rodzic.getParent();
	rodzic.setRightSon(leftSon);
	leftSon.setParent(rodzic);
	doRotacji.setLeftSon(rodzic);
	rodzic.setParent(doRotacji);
	doRotacji.setParent(dziadek);
	if(dziadek==wartownik) root=doRotacji;
	else if(rodzic==dziadek.getLeftSon()) {
		dziadek.setLeftSon(doRotacji);}
		else dziadek.setRightSon(doRotacji);
	Element zaskoczenia=doRotacji.getParent();
}

public void prawaRotacja(Element doRotacji) {
doRotacji=doRotacji.getLeftSon();

	Element rodzic=doRotacji.getParent();
	Element dziadek=rodzic.getParent();
	Element prawySyn=doRotacji.getRightSon();
	prawySyn.setParent(rodzic);
	rodzic.setLeftSon(prawySyn);
	doRotacji.setRightSon(rodzic);
	rodzic.setParent(doRotacji);
	doRotacji.setParent(dziadek);
	if(dziadek==wartownik) root=doRotacji;
	else if(rodzic==dziadek.getLeftSon()) {
		dziadek.setLeftSon(doRotacji);
	}
	else dziadek.setRightSon(doRotacji);
	Element zaskoczenia=doRotacji.getParent();
}
public void InOrder() {
	Element poczatek=root;
	inOrder(poczatek);
}
public void inOrder(Element poczatek){
	System.out.println(" ");
	if(poczatek!=wartownik && poczatek!=null) {
		inOrder(poczatek.getLeftSon());
		System.out.print(poczatek.getValue()+" "+poczatek.getColour());
		inOrder(poczatek.getRightSon());
		
}
}

public void procenty(Element poczatek) {
//	System.out.println(poczatek.getValue());
	if(poczatek!=wartownik && poczatek!=null) {
		procenty(poczatek.getLeftSon());
		if(poczatek!=wartownik) mianownik++;
		if(poczatek!= wartownik && poczatek.getColour().equals("BLACK")) licznik++;
		procenty(poczatek.getRightSon());
	}
}

public void inneProcenty(Element poczatek) {
	//System.out.println(poczatek.getValue());
	//if(poczatek==wartownik || poczatek==null)  System.out.println("TADADADADM");
	//if(poczatek.getValue()!=null) { mianownik2++;}
	if(poczatek!=wartownik && poczatek!=null) {
		inneProcenty(poczatek.getLeftSon());
		if(poczatek!=wartownik && poczatek.getRightSon()==wartownik && poczatek.getLeftSon()==wartownik) {mianownik2++; System.out.println(poczatek.getValue());};
		if(poczatek!= wartownik && poczatek.getColour().equals("BLACK") && poczatek.getRightSon()==wartownik && poczatek.getLeftSon()==wartownik) {System.out.println(" czarne "+poczatek.getValue()); licznik2++;}
		inneProcenty(poczatek.getRightSon());
	}
	//else mianownik2++;
}
public String ileProcent() {
	double percentage=(double)((int)((licznik/mianownik)*10000))/100;
	return Double.toString(percentage)+"%";
}

public String inneIleProcent() {
	System.out.println(mianownik2);
	System.out.println(licznik2);
	double percentage=(double)((int)((licznik2/mianownik2)*10000))/100;
	return Double.toString(percentage)+"%";
	
}

public void drukuj() {
	Kolejka k1=new Kolejka();
	Kolejka k2=new Kolejka();
	k1.enqueue(root);
	System.out.println(root.getValue());
	k2.enqueue(root.getLeftSon());
	k2.enqueue(root.getRightSon());
	while(k2.size()!=0) {
		k1=k2;
		k2=new Kolejka();
		//int i=0;
		while(k1.size()>0) {
			//i++;
			//System.out.println(i+ " "+k1.size());
			Element nowy=(Element)k1.dequeue();
			if(nowy.getValue()==null) System.out.print("      ");
			else { System.out.print(nowy.getValue()+" ");
			//System.out.println(i+ " "+k1.size());
			//System.out.println("dzieciaki "+nowy.getLeftSon().getValue()+ " "+nowy.getRightSon().getValue());
		//	if(nowy.getLeftSon().getValue()!=null)
			k2.enqueue(nowy.getLeftSon());
			//if(nowy.getRightSon().getValue()!=null)
			k2.enqueue(nowy.getRightSon());}
		}
		System.out.println(" ");
		
	}
	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tablica= {"hej","ala","avengers","kolacja","wlosy","mucha","tkanina","marakuja","mocarnie","skrzydla","mlot","Mikolaj","paranoja","wojsko"};
    DrzewoRB2 mojeBST=new DrzewoRB2(tablica[0]);
    for(int i=1;i<tablica.length;i++) {
    	mojeBST.dodajStringa(tablica[i],null);
    }
    Element sprawdz=mojeBST.wyszukajStringa("hej");
   if(sprawdz!=null) System.out.println("Jest taki element "+sprawdz.getValue());
    System.out.println(mojeBST.root.getValue());
		   mojeBST.inneProcenty( mojeBST.root);
		   System.out.println(mojeBST.inneIleProcent());
		  // mojeBST.procenty(mojeBST.root);
		   //System.out.println(mojeBST.ileProcent());
	   mojeBST.InOrder();
	   mojeBST.drukuj();

	}

}
