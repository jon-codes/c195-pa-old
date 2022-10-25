package controllers;

public abstract class ContentController {
    protected MainController parentController;

    public void setParentController(MainController mainController) {
        this.parentController = mainController;
    }
}
