import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class MainFrame extends JFrame{

	private BtTimer bt_timer;

    private  JTextField timerSize = new JTextField(3);

	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(150,50));
        setLocationRelativeTo(null);
        setTitle("Pomodoro");
        setAlwaysOnTop(true);
		setBackground(new Color(118,236,166));
        // Prepare JPanel and add to the JFrame
        JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(118,236,166));
        getContentPane().add(panel,BorderLayout.NORTH);

/* Window opacity trying - not working:
setUndecorated(true);
setBackground(new Color(255,255, 255, 179));
panel.setOpaque(false); // ??
*/

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.insets = new Insets(5,5,5,5); //Insets(int top, int left, int bottom, int right)
        c.gridx = 0;
        c.gridy = 0;

        bt_timer = new BtTimer(this);
        panel.add(bt_timer, c);

        // timerSize = new JTextField();

        c.gridy++;
        panel.add(timerSize, c);

        bt_timer.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                bt_timer.restart();
            }
        });

        pack();
	}

    public Integer getTimerSize(){
        String v = this.timerSize.getText();
        Integer res = 0;
        if( ! v.equals("")){
            res = Integer.parseInt(v);
        }
        return res;
    }
}