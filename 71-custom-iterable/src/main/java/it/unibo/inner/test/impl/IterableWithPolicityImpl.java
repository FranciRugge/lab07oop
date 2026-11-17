package it.unibo.inner.test.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;
/**
 * IterableWithPolicityImpl  implements {@link Predicate} create collection iterable for array
 * 
 * @param <T> elements of array to iterate over 
 */

public class IterableWithPolicityImpl<T> implements IterableWithPolicy<T> {
    private final T[] elements;
    private  Predicate <T> filter; //se ho l'import posso utilizzarla la classe Predicate 
    /**
     * Construct of ItetableWithPolicityImpl.
     * @param elements of array 
     */
    public IterableWithPolicityImpl(final T[]elements, Predicate <T> filter){
        this.elements = Arrays.copyOf(elements,elements.length);
        this.filter = filter;
    }
  
    public IterableWithPolicityImpl(final T[]elements){
        //delega tra costruttori, elements è il costruttore dell'array
        //elem è il paramentro in ingresso del metodo test(T elem), lafreccia separa i parametri del corpo del metodo
        //truue valore restituito dal metodo test
        this(elements,elem -> true ); 
   }
   
    @Override
    public Iterator<T> iterator() {
        
        return new ArrayIterator();
    }
    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }
    
    
    private class ArrayIterator implements Iterator<T>{
        private int count = 0;
        @Override
        public boolean hasNext() {
            while(this.count<elements.length){
                final T currentElements = elements[this.count];
                final boolean isAccept = filter.test(currentElements);
                if(isAccept){
                    return true;
                }
                this.count++;
                }
            return false;
        }

        @Override
        public T next() {
            if(!this.hasNext()){
               throw new NoSuchElementException();
            }
            else{
                final T Elementstoreturn = elements[this.count];
                this.count++;
                return Elementstoreturn;
            }
        }

    }

}
