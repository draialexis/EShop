package viewmodel;

import model.Perfume;

public class PerfumeVM extends ProductVM {

    private Perfume model;

    public PerfumeVM(Object obj) {
        super(obj);
    }

    public PerfumeVM() {
        super();
    }

    public PerfumeVM(String name, Double price) {
        super(name, price);
    }

}
