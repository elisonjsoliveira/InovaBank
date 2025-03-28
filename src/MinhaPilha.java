//Metodos que precisam ter em uma pilha:
// push() adicionar elemnento na pilha
// pop() retirar o ultimo elemento e o retorna
// peek() retorna o ultimo item sem removelo
// estaVazia() verifica se estar vazia
// tamanho() retorna o tamnho da lista

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MinhaPilha<T> {
    private ArrayList<T> elementos;

    public MinhaPilha(){
        this.elementos = new ArrayList<>();
    }

    public void adicionar(T item){
        elementos.add(item);
    }

    public T tirarDoTopo(){
        if(estaVazia()){
            throw new EmptyStackException();
        }
        return elementos.remove(tamanho() - 1);
    }

    public T topo(){
        if(estaVazia()){
            throw new EmptyStackException();
        }
        return elementos.get(tamanho() - 1);
    }

    public boolean estaVazia(){
        return elementos.isEmpty();
    }

    public int tamanho(){
        return elementos.size();
    }
}
