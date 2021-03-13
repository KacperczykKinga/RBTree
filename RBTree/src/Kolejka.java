interface JQueue<T>{
	public boolean isEmpty();
	public T dequeue();
	public void enqueue(T elem);
	public int size();
	public T first();
}
public class Kolejka<T> implements JQueue<T> {
int begin=0;
int end=0;
T[] kolejka;
int size=5;

public Kolejka() {
	kolejka=(T[])(new Object[size]);
}

//sprawdza czy kolejka jest pusta
public boolean isEmpty() {
	return end==begin;
}

//sprawdza czy kolejka jest pe³na
private boolean isFull() {
	return begin==(end+1)%kolejka.length;
}

//zwraca pierwszy element w kolejce jeœli nie jest pusta
public T dequeue() {
	if(!isEmpty()) {
		int pomoc=begin;
		begin++;
		begin=begin%kolejka.length;
		return kolejka[pomoc];
	}
	else throw new NullPointerException();
}

//dodaje nowy element na koniec kolejki, a jeœli kolejka jest pe³na to ja powiêksza i dodaje element
public void enqueue(T elem) {
	if(isFull()) {
		more();
	}
	 
		kolejka[end]=elem;
		end++;
		end=end%kolejka.length;
	

}

//powiêksza kolejke
public void more() {
	T[] pusta= (T[])(new Object[kolejka.length+10]);
	for(int i=0;i<kolejka.length;i++) {
		pusta[i]=kolejka[(i+begin)%kolejka.length];
		//System.out.println(pusta[i]+" "+kolejka[(i+begin)%kolejka.length]);
	}
	begin=0;
	end=kolejka.length-1;
	kolejka=(T[])(new Object[end+10]);
	kolejka=pusta;
	pusta=null;
}

//pokazuje ile jest elementów w kolejce
public int size() {
	return (end-begin+kolejka.length)%kolejka.length;
}

//zwraca pierwszy element, ale nie usuwa go
public T first() {
	if(!isEmpty()) {
		return kolejka[begin];
	}
	else throw new NullPointerException();
}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
       
	}

}
