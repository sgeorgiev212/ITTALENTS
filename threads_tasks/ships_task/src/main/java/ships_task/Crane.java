package ships_task;

public class Crane extends Thread{

    private String name;
    private static int number = 1;
    private int id;
    private Harbour harbour;

    public Crane(Harbour harbour) {
        this.name = "Crane "+number;
        this.id = number++;
        this.harbour = harbour;
    }

    @Override
    public void run() {
        while (true) {
            this.harbour.unloadShips(this);
        }
    }

    public String getCraneName(){
        return name;
    }

    public int getCraneId() {
        return id;
    }
}
