import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JPanel panel;
    private JProgressBar progressBar;
    private JLabel bestVectorLabel;
    private JLabel infoLabel;
    private JLabel value;
    private DataToShow dataToShow;
    GUI thisGUI = this;
    boolean finish;


    public GUI(String name) {
        super(name);
        new Thread(() -> {
            finish = false;
           thisGUI.setBackground(Color.LIGHT_GRAY);
           thisGUI.progressBar.setValue(0);
           thisGUI.progressBar.setForeground(Color.GREEN);
           thisGUI.add(panel);
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ignored) {}
           thisGUI.setSize(400,200);
           thisGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
           thisGUI.setVisible(true);
            try {
                while (!finish) {
                    thisGUI.updateInfo();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        progressBar.setValue(100);
    }

    public void updateData (DataToShow dataToShow){
        this.dataToShow = dataToShow;
    }

    public synchronized void updateInfo() throws InterruptedException {
            if (dataToShow != null) {
                progressBar.setValue( (int) dataToShow.percentage);
                infoLabel.setText("Used space: "+ dataToShow.usedSpace + "/"+dataToShow.totalSpace);
                bestVectorLabel.setText("Best vector: "+ dataToShow.bestVector);
                value.setText("Best value: "+ dataToShow.bestValue);
        }
    }
}
