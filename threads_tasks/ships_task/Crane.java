public class Crane extends Thread{

    private String name;
    private Harbour harbour;
    private int id;

    public Crane(String name,Harbour harbour,int id) {
        this.name = name;
        this.harbour = harbour;
        this.id = id;
    }

    public String getCraneName(){
        return this.name;
    }

    @Override
    public void run() {
        while (true){
            this.harbour.unloadShips(this);
        }
    }


    public int getCraneId() {
        return id;
    }
}
