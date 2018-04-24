package Waluty

import org.jfree.ui.RefineryUtilities

import javax.swing.*

class Main {
    static void main(String[] args) {
        def frame = new JFrame("Waluty")
        frame.setContentPane(new Okno().plane)
        frame.setSize(800,600)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        RefineryUtilities.centerFrameOnScreen(frame)
        frame.setVisible(true)
        JOptionPane.showMessageDialog(null,"Program walutowy(beta)\nObowiązkowym polem jest pole rodzaju tabeli.\n" +
                "W przypadku nie podania daty, wyświetlane są najświeższe dane.\nMaksymalny przedział to 91dni\nDane archiwalne są dostępne od 2 stycznia 2002r. (2002-01-02)",
                "Program",1
        )
    }
    }
