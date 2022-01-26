
public class PQ{

    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ

    private car[] keys;      // keys[i] = priority of i

    public boolean loaded = false;

    public PQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (new car[maxN + 1]); 
    }


    public void insert(car key, boolean price, boolean mm) {//insert into car where price means lowest price and mm means makemodel
        n++;
        keys[n] = key;
        swim(n, price, mm);
    }

    public void delete(int i, boolean price, boolean mm) {//delete car
        validateIndex(i);

        exch(i, n--, price, mm);
        swim(i, price, mm);
        sink(i, price, mm);
        keys[n+1] = null;
    }

    public car minKey() {// returns min 
        if (n == 0) return null;
        return keys[1];
    }

    public void update(int i, boolean price, boolean mm){//update PQ
        swim(i, price, mm);
        sink(i, price, mm);
    }

    private void swim(int k, boolean price, boolean mm) {
        while (k > 1 && greater(k/2, k, price)) {
            exch(k, k/2, price, mm);
            k = k/2;
        }
        setIndexes(k, price, mm);
    }

    private void sink(int k, boolean price, boolean mm) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1, price)) j++;
            if (!greater(k, j, price)) break;
            exch(k, j, price, mm);
            k = j;
        }
        setIndexes(k, price, mm);
    }

    private boolean greater(int i, int j, boolean price) {
        if(price){
            return keys[i].getPrice() > keys[j].getPrice();
        }
        else{
            return keys[i].getMileage() > keys[j].getMileage();
        }
    }

    private void exch(int i, int j, boolean price, boolean mm) {
        car swap = keys[i];
        keys[i] = keys[j];
        keys[j] = swap;

        if(price){
            if(mm){
                keys[i].setIndexPMap(i);
                keys[j].setIndexPMap(j);
            }else{
                keys[i].setIndexP(i);
                keys[j].setIndexP(j);
            }
        }
        else{
            if(mm){
               keys[i].setIndexMMap(i);
                keys[j].setIndexMMap(j); 
            }else{
                keys[i].setIndexM(i);
                keys[j].setIndexM(j);
            }
        }
        
    }

    private void validateIndex(int i) {
        if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
        if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
    }

    private void setIndexes(int k, boolean price, boolean mm){//set the indexes accordingly when sink and swim
        if(price){
            if(mm){
                keys[k].setIndexPMap(k);
            }else{
                keys[k].setIndexP(k);
            }
        }
        else{
            if(mm){
                keys[k].setIndexMMap(k);
            }else{
                keys[k].setIndexM(k);
            }
        }
    }

    public void print(){
        System.out.println("\nPRINTING LIST");
        for(int i = 1; i < n+1; i++){
            System.out.println(keys[i].toString());
        }

        System.out.println();
    }

}