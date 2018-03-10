package by.tc.task02.main;


import by.tc.task02.presenter.ShopPresenter;
import by.tc.task02.presenter.ShopPresenterImpl;
import by.tc.task02.service.exception.ServiceException;

public class Main {
    public static void main(String[] args) throws ServiceException {
        ShopPresenter presenter = new ShopPresenterImpl();
        presenter.mainMenu();
    }
}