package experimentlist;// EMRE KAVAK 151044085 HW2
import java.util.Iterator;


// NOT : ExperimentList classının add metodu , objeleri listeye sıralı ekler. O yuzden test sonucları farklı cıkabilir.

class ExperimentList implements Iterable {
    Node head;
    static int index = 0;   // for hold index
    public Node getHead(){ return this.head;}
    public void incrementIndex(){this.index++;}
    public int getStaticIndex(){ return index;}
    public ExperimentList() { head = null; }
    @Override
    public Iterator iterator() {
        return new ExperimentListIterator(head);        // create a new ıterator class object
    }
    public int compareDay(Experiment obj1, Experiment obj2){ // for compare object
        if(obj1.getDay() > obj2.getDay()) return 1;
        if(obj1.getDay() == obj2.getDay()) return 0;
        if(obj1.getDay() < obj2.getDay()) return -1;
        return 0;
    }
    public void addExp(Experiment ob){  // this class add Experiment objects ordered to list. So, if you add not ordered, it will be ordered in list.
        if (this.head == null){     // head hold first node
            Node newNode = new Node(ob);
            newNode.next = null;
            this.head = newNode;
            this.head.setIndex(index);
        }else{
            Node temp = head;
            Node temp2 = temp;
            int flag = 0, flag2 = 0;
            while (flag == 0) {
                if(compareDay(temp.getObj(),ob) == 0){
                    while(temp.getObj().getDay() == ob.getDay()){       // when find experiment equal the entered exp, it will go end of day
                        temp2 = temp;
                        flag2 = 1;
                        temp = temp.getNext();
                        if(temp == null ) {
                            flag = 1;
                            break;
                        }
                    }
                    break;
                }
                if(compareDay(temp.getObj(),ob) == 1) { // for ordered add
                    break;
                }
                if (flag != 1 && flag2 == 0) {  // flags for innet loop
                    temp2 = temp;
                    temp = temp.getNext();
                    if(temp == null ) {
                        flag = 1;
                        break;
                    }
                }
            }// while end
            Node add = new Node(ob);
            Node backupNode = temp2.next;
            temp2.next = add;   // add to new experiment object in the list
            add.setNext(backupNode);

            int res = compareDay(ob,temp2.getObj()); // compare last element and new element day in the list.
            if(res == 0) {this.incrementIndex();}; // index ++
            if(res == 1 || res == -1 ) {
                this.index = 0; // if index ==0 this obj is a new day,so it should be linked with before last day first object
                setNextDay(add); // for add nextDay to between first elements
            }
            if(flag2 == 1) this.index = temp2.getIndex() + 1; // if the object added an existing day, index will ++ and add this object index
            temp2.next.setIndex(this.index);
        }
    }
    public void setNextDay(Node obj2){ // for linked different days
        Node temp = head;
        Node temp2 = new Node();
        int flag = 0, backDay = 0;
        while(temp != null && flag ==0 ){
            if(compareObjects(temp.getObj(), obj2.getObj())){ // continue until obje2, hold day before object
                backDay = temp2.getObj().getDay();  // catch last day
                flag = 1;
            }
            temp2 = temp;
            temp = temp.getNext();
        }
        temp = getFirstDay(backDay); // the first obje the old last day,linked new day first object
        temp.setNextDay(obj2);
    }
    public void removeExp(int day, int index){  // removes an object in the list
        if(head.getObj().getDay() == day && head.getIndex() == index){ // first element remove
            head = head.next;
        }else{
            Node temp = head;
            Node temp2 = head;
            int flag = 0;
            Iterator itera = this.iterator();
            while (itera.hasNext() && flag == 0) {      // when find experiment equal the entered exp, it will go end of end until not equal day
                if(temp.getObj().getDay() == day && temp.getIndex() == index){
                    flag = 1;
                    temp2.next =temp.next;
                    temp = null;
                }else {
                    temp2 = temp; // before object
                    temp = temp.getNext();
                }
            }
        }
    }
    public void setExp(int day, int index, Experiment e){   // change the object with new object
        Node temp = head;
        while(temp != null){
            if(temp.getIndex() == index && temp.getObj().getDay() == day){
                temp.setObj(e);
            }
            temp = temp.next;
        }
    }
    public Experiment getExp(int day, int index){   // return an experiment if it there is in the list
        Node temp = head;
        while (temp.getNext() != null) {
            if(temp.getObj().getDay() == day && temp.getIndex() == index)
                return temp.getObj();
            temp = temp.getNext();
        }
        return new Experiment();
    }
    public void listExp(int day) { //list all completed experiments in a given day
        Node temp =new Node();
        temp = head;
        int flag = 0;
        while (flag == 0) {
            if(temp.getObj().getDay() == day && temp.getObj().getCompleted() == true){
                System.out.println(temp);   // print all experiment according to given day
            }
            temp = temp.getNext();
            if(temp.getNext() == null ) flag = 1;
        }
    }

    public void removeDay(int day) {    // remove all experiments in a given day
        Node temp = head;
        Node temp2 = head;
        int flag = 0;
        while (flag == 0) {
            if(temp.getObj().getDay() == day ){
                while(temp.getObj().getDay() == day && flag == 0){ // when find experiment equal the entered exp, it will go end of end
                    temp = temp.getNext();                          // until not equal day
                    if(temp.getNext() == null ) flag = 1;
                }
                if(day == 0 || day == 1) head = temp;
                else if(temp == null){ temp2.next = temp;}
                else temp2.next = temp.next;    //change the list, it tears connection objects
                break;
            }
            temp2 = temp;
            temp = temp.getNext();
            if(temp.getNext() == null ) flag = 1;
        } // while end
    }
    public void copyNode(Node ob1, Node ob2){    // for copy a nod with another node information
        ob1.getObj().setSetup(ob2.getObj().getSetup());
        ob1.getObj().setDay(ob2.getObj().getDay());
        ob1.getObj().setAccuracy(ob2.getObj().getAccuracy());
        ob1.getObj().setCompleted( ob2.getObj().getCompleted());
        ob1.getObj().setTime(ob2.getObj().getTime());
    }
    public void orderDay(int day) { //sorts the experiments in a given day according to the accuracy, the changes will be done on the list
        Node tempSearch= head;
        Node tempNext =tempSearch;
        Node tempBack =tempSearch;
        while(tempSearch != null){
            tempNext = tempSearch.getNext();
            if(tempSearch.getObj().getDay() == day && tempNext.getObj().getDay() == day){  // checking day information
                while(tempSearch.getObj().getDay() == day && tempNext.getObj().getDay() == day) {   // this while for loop just given day
                    if (tempSearch.getObj().getAccuracy() < tempNext.getObj().getAccuracy()) {
                        Node backup = tempNext.next;    // swap linked linkes;
                        tempBack.next = tempNext;
                        tempNext.next = tempSearch;
                        tempSearch.next = backup;
                    }
                    tempNext = tempNext.getNext();
                }
            }
            tempBack = tempSearch; // holds before object
            tempSearch =tempSearch.next;
        }
    }
    public ExperimentList orderExperiments() { // sorts all the experiments in the list according to the accuracy, the original list should not be changed since the day list may be damage
        ExperimentList temp = new ExperimentList();
        Node temp2 = head;
        Node temp3 = head;
        while (temp2.next != null) {
            temp.addExp(temp2.getObj());
            temp2=temp2.getNext();
        }
        temp2 = temp.getHead();
        while (temp2!= null) {
            temp3 = temp2;
            while (temp3.next != null) {
                if (temp3.getObj().getAccuracy() < temp3.next.getObj().getAccuracy()) {
                    Experiment bac = temp3.getObj();    // swap linkes
                    temp3.setObj(temp3.next.getObj());
                    temp3.next.setObj(bac);
                }
                temp3=temp3.next;
            }
            temp2 = temp2.next;
        }
        return temp;
    }
    public Node getFirstDay(int day){   // return  first object a day
        Node temp =new Node();
        temp = head;
        while (temp != null){
            if(temp.getObj().getDay() == day){
                return  temp;
            }
            temp = temp.getNext();
        }
        return  temp;
    }
    public boolean compareObjects(Experiment obj1, Experiment obj2){    // compera two experiment objeccts according to data member
        if(obj1.setup == obj2.setup && obj1.completed == obj2.completed && obj1.time == obj2.time && obj1.accuracy == obj2.accuracy
                && obj1.day == obj2.day) return true;
        else return false;
    }
    public void listAll()
    {
        System.out.println("List experiment view:");
        Node last = head;
        while( last != null) {
            System.out.println(last.getObj().toString());
            last = last.next;
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.getObj().toString());
            last = last.nextDay;
        }
    }
}

