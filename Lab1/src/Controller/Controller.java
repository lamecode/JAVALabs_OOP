package Controller;

import View.ProgramView;

public class Controller {

    private int option;
    private ProgramView start;
    private InputStream input;

    public Controller() {
        start = new ProgramView();
        input = new InputStream();
    }

    public void run() {
        start.show();
        setOption(input.option());
    }



    public void setOption(String userOption) {
        Validator validator = new Validator();
        while (!validator.checkOption(userOption)) {
            System.out.println("Невірно введені дані. Будь ласка, оберіть одну з опцій 1 або 2.");
            userOption = input.option();
        }
        option = Integer.parseInt(userOption);
    }
}
