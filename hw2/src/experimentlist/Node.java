package experimentlist;// EMRE KAVAK 151044085 HW2


// NOT : ExperimentList classının add metodu , objeleri listeye sıralı ekler. O yuzden test sonucları farklı cıkabilir.

class Node{ // node class for hold experiment objects and next,nextDay for ExperimentList class
    public Experiment obj;
    public Node next;
    public Node nextDay;
    public int index;
    public Node(){}
    public Node(Experiment obj1 ){
        this.obj = obj1;
        this.next = null;
        this.nextDay = null;
    }
    public Node(Experiment obj1, Node next ){
        this.obj = obj1;
        this.next = next;
    }
    public void setIndex(int indx){ this.index = indx;}
    public int getIndex(){return this.index;};
    public void setNext(Node ob){ this.next = ob;}
    public Node getNext(){ return next;}
    public void setObj(Experiment ob){ this.obj = ob;}
    public Experiment getObj(){ return this.obj;}
    public void setNextDay(Node ob){ this.nextDay = ob;}
    public String toString() {
        //return "index = "+this.index+" Setup :" + obj.setup + " Day :" + obj.day + " Time :" + obj.time + " completed :" + obj.completed+ " accuracy :" + obj.accuracy;
        return "Experiment{" +
                "setup='" + obj.setup + '\'' +
                ", day=" + obj.day +
                ", time='" + obj.time + '\'' +
                ", accuracy=" + obj.accuracy +
                ", completed=" + obj.completed +
                '}';
    }
}

