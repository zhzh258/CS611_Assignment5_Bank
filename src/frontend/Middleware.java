package frontend;

import java.util.*;

import javax.swing.JOptionPane;

import account.*;
import role.AccountListener;
import stock.StockListener;

import java.io.*;

public class Middleware {
  // singleton
  private static Middleware instance;
  private Middleware() {}
  public static Middleware getInstance() {
    if (instance == null) {
      instance = new Middleware();
    }
    return instance;
  }

  // ****************************************************
  private List<DeleteAccountListener> daListeners = new ArrayList<DeleteAccountListener>();

  public void addDeleteAccountListener(DeleteAccountListener listener) {
    daListeners.add(listener);
  }
  public void removeDeleteAccountListener(DeleteAccountListener listener) {
    daListeners.remove(listener);
  }
  public void notifyAccountDeleted(String customerName) {
    List<DeleteAccountListener> localListeners = new ArrayList<DeleteAccountListener>(daListeners);
    for (DeleteAccountListener listener : localListeners) {
      // System.out.println(listener.toString());
      listener.accountDeleted(customerName);
    }
  }

  // ****************************************************

  private List<AccountListener> listeners = new ArrayList<AccountListener>();

  public void addAccountListener(AccountListener listener) {
      // System.out.println("listener added: " + listener);
      listeners.add(listener);
  }

  public void removeAccountListener(AccountListener listener) {
      listeners.remove(listener);
  }

  public void notifyAccountUpdated(String customerName) {
    List<AccountListener> localListeners = new ArrayList<AccountListener>(listeners);
    // System.out.println("Is there any listeners?");
    for (AccountListener listener : localListeners) {
        // System.out.println(listener.toString());
        listener.accountUpdated(customerName);
    }
  }

  // ****************************************************

  private List<BalanceListener> accountListeners = new ArrayList<BalanceListener>();

  public void addBalanceListener(BalanceListener listener) {
      // System.out.println("listener added: " + listener);
      accountListeners.add(listener);
  }

  public void removeBalanceListener(BalanceListener listener) {
    accountListeners.remove(listener);
  }

  public void notifyBalanceUpdated(String customerName) {
    List<BalanceListener> localListeners = new ArrayList<BalanceListener>(accountListeners);
    System.out.println("Is there any listeners? " + accountListeners.size());
    // String msg = "There is a balance update to one of " + customerName +
    // "'s accounts";
    // PopupFrame popup = new PopupFrame(msg);
    // popup.showWindow();
    for (BalanceListener listener : localListeners) {
        // System.out.println(listener.toString());
        listener.balanceUpdated(customerName);
    }
  }

  // ****************************************************

  private List<StockListener> stockListeners = new ArrayList<StockListener>();

  public void addStockListener(StockListener listener) {
      // System.out.println("listener added: " + listener);
      stockListeners.add(listener);
  }

  public void removeStockListener(StockListener listener) {
    stockListeners.remove(listener);
  }

  public void notifyStockUpdated() {
    List<StockListener> localListeners = new ArrayList<StockListener>(stockListeners);
    System.out.println("Is there any listeners? " + stockListeners.size());
    for (StockListener listener : localListeners) {
        // System.out.println(listener.toString());
        listener.stockUpdated();
    }
  }
}
