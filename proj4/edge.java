public class edge{//used the book's edge implementation as reference
    private final int COPPER_SPEED = 230000000;
    private final int FIBER_SPEED = 200000000;
    private int bandwidth;
    private int length;
    private String material;
    private int start;
    private int end;
    private double time;

    public edge(String[] info){
        this.start = Integer.parseInt(info[0]);
        this.end = Integer.parseInt(info[1]);
        this.material = info[2];
        this.bandwidth = Integer.parseInt(info[3]);
        this.length = Integer.parseInt(info[4]);

        if(this.material.equals("copper")){
            this.time = this.length * (1.0/COPPER_SPEED) * Math.pow(10,9);
        }else{
            this.time = this.length * (1.0/FIBER_SPEED) * Math.pow(10,9);
        }

    }

    public void setBandwidth(int bandwidth){
        this.bandwidth = bandwidth;
    }

    public int getBandwidth(){
        return bandwidth;
    }

    public void setLength(int length){
        this.length = length;
    }

    public int getLength(){
        return length;
    }

    public void setMaterial(String material){
        this.material = material;
    }

    public String getMaterial(){
        return material;
    }

    public void setStart(int start){
        this.start = start;
    }

    public int getStart(){
        return start;
    }

    public void setEnd(int end){
        this.end = end;
    }

    public int getEnd(){
        return end;
    }

    public void setTime(double time){
        this.time = time;
    }

    public double getTime(){
        return time;
    }

    public int other(int vertex) {
        if      (vertex == start) return start;
        else if (vertex == end) return end;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    public String toString() {
        return String.format("%d-%d %d %d", start, end, bandwidth, length);
    }
}