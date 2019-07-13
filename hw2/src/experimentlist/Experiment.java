package experimentlist;// EMRE KAVAK 151044085 HW2


// NOT : ExperimentList classının add metodu , objeleri listeye sıralı ekler. O yuzden test sonucları farklı cıkabilir.

class Experiment{
    protected String setup;
    protected Integer day;
    protected String time;
    protected boolean completed;
    protected float accuracy;
    public void setDay(int val){ this.day =val; }
    public void setSetup(String val){ this.setup =val;}
    public void setTime(String val){ this.time =val;}
    public void setCompleted(Boolean val){ this.completed =val;}
    public void setAccuracy(Float val){ this.accuracy =val;}
    public String getSetup(){ return this.setup;}
    public Integer getDay(){ return day;}
    public String getTime(){return this.time;}
    public boolean getCompleted(){return this.completed;}
    public float getAccuracy(){return this.accuracy;}
    public Experiment(Experiment ob){ this.setup = ob.setup; } // default constructor
    public Experiment(){}
    public Experiment(String setup, Integer day, String time, boolean completed, float accuracy){
        this.setup = setup;
        this.day = day;
        this.time = time;
        this.completed = completed;
        this.accuracy = accuracy;
    }
    //public String toString() {
      //  return "Setup :" + this.setup + " Day :" + this.day + " Time :" + this.time + " completed :" + this.completed+ " accuracy :" + this.accuracy;
    //}
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }
}

