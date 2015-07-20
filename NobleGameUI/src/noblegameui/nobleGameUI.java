//Thong Truong - 0356860
//CS 9053 - hw06

package noblegameui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class nobleGameUI {

    public static void main(String[] args) 
    {
        Game myGame = new Game();
        myGame.setVisible(true);
    }
}

class Game extends JFrame
{
    JTabbedPane tabs;
    JPanel addTab, hireTab, battleTab, displayTab;
    JTextArea outputArea;
    JScrollPane display;
    ArrayList<Noble> nobles;
    ArrayList<Warrior> warriors;
    JComboBox hireNobles, hireWarriors, runawayWarriors, battleNoble1, battleNoble2, displayNobles;

    
    //================================CONSTRUCTOR==============================
    Game()
    {
        //-----------INITIALIZE VARIABLES---------------
        nobles = new ArrayList<Noble>();
        warriors = new ArrayList<Warrior>();
        hireNobles = new JComboBox();
        hireWarriors = new JComboBox();
        runawayWarriors = new JComboBox();
        battleNoble1 = new JComboBox();
        battleNoble1.setMaximumSize(new Dimension(200,20));
        battleNoble2 = new JComboBox();
        battleNoble2.setMaximumSize(new Dimension(200,20));
        displayNobles = new JComboBox();
        displayNobles.setMaximumSize(new Dimension(200,20));

        
        //---------------SET UP TABS---------------
        setAddTab();
        setHireTab();
        setBattleTab();
        setDisplayTab();

        tabs = new JTabbedPane();
        tabs.addTab("Add Noble/Warrior", addTab);
        tabs.addTab("Hire/Runaway", hireTab);
        tabs.addTab("Battle", battleTab);
        tabs.addTab("Display Noble", displayTab);

        //-------------SET UP OUTPUT AREA-----------------
        outputArea = new JTextArea();
        outputArea.setEditable(false);
                
        display = new JScrollPane(outputArea);
        
        //-------------SET UP THE FRAME------------------
        setTitle("Noble Game");
        setSize(400,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        add(tabs);
        add(display);
    }


    //===============================TABS=======================================

    // ADD TAB =================================================================
    private void setAddTab()
    {
        //-----------------------CREATE PANEL TO ADD NOBLE----------------------
        JPanel addNoble = new JPanel();
        addNoble.setLayout(new BoxLayout(addNoble, BoxLayout.Y_AXIS));

        //make components for the panel
        JLabel jl1 = new JLabel("Noble");
        jl1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jl2 = new JLabel("Name");
        jl2.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JTextField inputNobleName = new JTextField();
        inputNobleName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addNobleButton = new JButton("Add New Noble");
        addNobleButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ActionListener addNobleAction = new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                if(!inputNobleName.getText().equals(""))
                {
                    nobles.add(new Noble(inputNobleName.getText()));
                    outputArea.append("Noble " + inputNobleName.getText() + " is created.\n");
                    updateNoblesList(inputNobleName.getText());
                }
            }            
        };
        addNobleButton.addActionListener(addNobleAction);
        
        //put everything together
        addNoble.add(jl1);
        addNoble.add(Box.createRigidArea(new Dimension(0,10)));
        addNoble.add(jl2);
        addNoble.add(inputNobleName);
        addNoble.add(Box.createRigidArea(new Dimension(0,10)));
        addNoble.add(addNobleButton);
        addNoble.add(Box.createRigidArea(new Dimension(0,120)));

        //--------------------CREATE PANEL TO ADD WARRIOR-----------------------
        JPanel addWarrior = new JPanel();
        addWarrior.setLayout(new BoxLayout(addWarrior, BoxLayout.Y_AXIS));

        //create components for the panel
        JLabel jl3 = new JLabel("Warrior");
        jl3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jl4 = new JLabel("Name");
        jl4.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JTextField inputWarriorName = new JTextField();
        inputWarriorName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jl5 = new JLabel("Strength");
        jl5.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JTextField inputWarriorStr = new JTextField();
        inputWarriorStr.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addWarriorButton = new JButton("Add New Warrior");
        addWarriorButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ActionListener addWarriorAction = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!inputWarriorName.getText().equals("") && !inputWarriorStr.getText().equals(""))
                {
                    warriors.add(new Warrior(inputWarriorName.getText(), Integer.parseInt(inputWarriorStr.getText().trim())));
                    outputArea.append("Warrior " + inputWarriorName.getText() + " with strength of " + inputWarriorStr.getText() + " is created.\n");
                    updateWarriorsList(inputWarriorName.getText());
                }
            }
        };
        addWarriorButton.addActionListener(addWarriorAction);

        //put everything together
        addWarrior.add(jl3);
        addWarrior.add(Box.createRigidArea(new Dimension(0,10)));
        addWarrior.add(jl4);
        addWarrior.add(inputWarriorName);
        addWarrior.add(Box.createRigidArea(new Dimension(0,10)));
        addWarrior.add(jl5);
        addWarrior.add(inputWarriorStr);
        addWarrior.add(Box.createRigidArea(new Dimension(0,10)));
        addWarrior.add(addWarriorButton);
        addWarrior.add(Box.createRigidArea(new Dimension(0,85)));

        //--------------------ASSEMBLE THE ADD TAB-----------------------------
        addTab = new JPanel(new GridLayout(1,2,10,0));

        addTab.add(addNoble);
        addTab.add(addWarrior);
    }

    // HIRE TAB ================================================================
    private void setHireTab()
    {
        //------------------CREATE PANEL TO HIRE-------------------
        JPanel hirePanel = new JPanel();
        hirePanel.setLayout(new BoxLayout(hirePanel, BoxLayout.Y_AXIS));

        //create components for the hire panel
        JLabel hireLabel = new JLabel("Hire");
        hireLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel chooseNoble = new JLabel("Choose a noble:");
        chooseNoble.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel chooseWarrior1 = new JLabel("Choose a warrior:");
        chooseWarrior1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton hireButton = new JButton("Hire");
        hireButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ActionListener hireAction = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int i = getNobleIndex((String)hireNobles.getSelectedItem());
                int j = getWarriorIndex((String)hireWarriors.getSelectedItem());
                
                outputArea.append(nobles.get(i).hire(warriors.get(j)));
            }
        };
        hireButton.addActionListener(hireAction);

        //assemble the hire panel
        hirePanel.add(hireLabel);
        hirePanel.add(Box.createRigidArea(new Dimension(0,10)));
        hirePanel.add(chooseNoble);
        hirePanel.add(hireNobles);
        hirePanel.add(Box.createRigidArea(new Dimension(0,10)));
        hirePanel.add(chooseWarrior1);
        hirePanel.add(hireWarriors);
        hirePanel.add(Box.createRigidArea(new Dimension(0,10)));
        hirePanel.add(hireButton);
        hirePanel.add(Box.createRigidArea(new Dimension(0,90)));

        //----------------------CREATE PANEL FOR RUNAWAY------------------------
        JPanel quitPanel = new JPanel();
        quitPanel.setLayout(new BoxLayout(quitPanel, BoxLayout.Y_AXIS));

        //create components for the runaway panel
        JLabel runawayLabel = new JLabel("Runaway");
        runawayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel chooseWarrior2 = new JLabel("Choose a warrior:");
        chooseWarrior2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton runawayButton = new JButton("Runaway");
        runawayButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ActionListener runawayAction = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int i = getWarriorIndex((String)runawayWarriors.getSelectedItem());
                outputArea.append(warriors.get(i).runaway());
            }
        };
        runawayButton.addActionListener(runawayAction);

        //assemble the runaway panel
        quitPanel.add(runawayLabel);
        quitPanel.add(Box.createRigidArea(new Dimension(0,10)));
        quitPanel.add(chooseWarrior2);
        quitPanel.add(runawayWarriors);
        quitPanel.add(Box.createRigidArea(new Dimension(0,10)));
        quitPanel.add(runawayButton);
        quitPanel.add(Box.createRigidArea(new Dimension(0,110)));

        //-----------------------ASSEMBLE THE HIRE TAB--------------------------
        hireTab = new JPanel( new GridLayout(1,2,10,0));
        hireTab.add(hirePanel);
        hireTab.add(quitPanel);

    }

    // BATTLE TAB ==============================================================
    private void setBattleTab()
    {
        //create components for battle tab
        JLabel chooseNoble1 = new JLabel("Choose a noble:");
        chooseNoble1.setAlignmentX(Component.CENTER_ALIGNMENT);
      
        JLabel chooseNoble2 = new JLabel("Choose a noble:");
        chooseNoble2.setAlignmentX(Component.CENTER_ALIGNMENT);
      
        JButton battleButton = new JButton("Battle!");
        battleButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ActionListener battleAction = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int i = getNobleIndex((String)battleNoble1.getSelectedItem());
                int j = getNobleIndex((String)battleNoble2.getSelectedItem());

                outputArea.append(nobles.get(i).battle(nobles.get(j)));
            }
        };
        battleButton.addActionListener(battleAction);

        //assemble the battle tab
        battleTab = new JPanel();
        battleTab.setLayout(new BoxLayout(battleTab, BoxLayout.Y_AXIS));

        battleTab.add(chooseNoble1);
        battleTab.add(battleNoble1);
        battleTab.add(Box.createRigidArea(new Dimension(0,10)));
        battleTab.add(chooseNoble2);
        battleTab.add(battleNoble2);
        battleTab.add(Box.createRigidArea(new Dimension(0,10)));
        battleTab.add(battleButton);
        battleTab.add(Box.createRigidArea(new Dimension(0,120)));
    }

    // DISPLAY TAB =============================================================
    private void setDisplayTab()
    {
        //create components for the display tab
        JLabel jl = new JLabel("Choose a noble:");
        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
      
        JButton displayButton = new JButton("View Noble");
        displayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        ActionListener displayAction = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int i = getNobleIndex((String)displayNobles.getSelectedItem());
                outputArea.append(nobles.get(i).toString());
            }
        };
        displayButton.addActionListener(displayAction);

        //assemble the display tab
        displayTab = new JPanel();
        displayTab.setLayout(new BoxLayout(displayTab, BoxLayout.Y_AXIS));

        displayTab.add(jl);
        displayTab.add(displayNobles);
        displayTab.add(Box.createRigidArea(new Dimension(0,10)));
        displayTab.add(displayButton);
        displayTab.add(Box.createRigidArea(new Dimension(0,160)));
    }


    //==========================MISC. FUNCTIONS=================================
     private void updateNoblesList(String s)
    {
        hireNobles.addItem(s);
        battleNoble1.addItem(s);
        battleNoble2.addItem(s);
        displayNobles.addItem(s);
    }

    private void updateWarriorsList(String s)
    {
        hireWarriors.addItem(s);
        runawayWarriors.addItem(s);
    }

    private int getNobleIndex(String s)
    {
        int i = 0;
        while(!nobles.get(i).getName().equals(s))
        {
                    i++;
        }
        return i;
    }

    private int getWarriorIndex(String s)
    {
        int i = 0;
        while(!warriors.get(i).getName().equals(s))
        {
                    i++;
        }
        return i;
    }
}

//====================NOBLES AND WARRIORS FROM HW03=============================
class Noble
{
    private String name;
    private double strength;
    private ArrayList<Warrior> warriors;

    Noble(String s)
    {
        name = s;
        strength = 0;
        warriors = new ArrayList<Warrior>();
    }

    @Override public String toString()
    {
        String s;
        String newLine = System.getProperty("line.separator");

        s = name + " has an army of " + warriors.size() + newLine;

        for(int i = 0; i < warriors.size(); i++)
        {
            s += "   " + warriors.get(i).getName() + ": " + warriors.get(i).getStrength() + newLine;
        }

        return s;
    }

    public String getName()
    {
        return name;
    }

    public double getStrength()
    {
        return strength;
    }

    public void updateWarriorStrength(double d)
    {
        for(int i = 0; i < warriors.size(); i++)
        {
            //if d=0 then set all strength to 0
            if(d==0)
            {
                warriors.get(i).setStrength(0);
            }
            else //reduce the strength of each warrior to a ratio of d
            {
                double newStrength = warriors.get(i).getStrength() * d;
                warriors.get(i).setStrength(newStrength);
            }
        }
    }

    public void updateNobleStrength()
    {
        strength = 0;
        for(int i = 0; i < warriors.size(); i++)
        {
           strength += warriors.get(i).getStrength();
        }
    }

    public String hire(Warrior w)
    {
        if(!w.isHired())
        {
            warriors.add(w);
            strength += w.getStrength();
            w.setNoble(this);
            return (name + " just hire " + w.getName() + "!\n");
        }
        else
        {
            return (w.getName() + " is hired by another noble already!\n");
        }
    }

    public void unhire(Warrior w)
    {
        warriors.remove(w);
        strength -= w.getStrength();
    }

    public String battle(Noble n)
    {
        String result = name + " battles " + n.getName() + "\n";

        if(n.getStrength()==0 && strength!=0) //if the enemy dead
        {
            result += "He's dead " + name + "\n";
        }
        else if(strength==0 && n.getStrength()==0) //if both dead
        {
            result += "Oh NO! They're both dead! Yuck!\n";
        }
        else if(strength == n.getStrength()) //if both strengths are equal
        {
           this.updateWarriorStrength(0);
           this.updateNobleStrength();
           n.updateWarriorStrength(0);
           n.updateNobleStrength();

           result += "Mutual Annihilation: ";
           result += name + " and " + n.getName() + " die at each other's hands\n";
        }
        else if(strength > n.getStrength()) //if winning
        {
            double d = n.getStrength() / strength;
            this.updateWarriorStrength(1-d);
            this.updateNobleStrength();
            n.updateWarriorStrength(0);
            n.updateNobleStrength();

            result += name + " defeats " + n.getName() + "\n";
        }
        else if(strength < n.getStrength()) //if losing
        {
            double d = strength /  n.getStrength();
            n.updateWarriorStrength(1-d);
            n.updateNobleStrength();
            this.updateWarriorStrength(0);
            this.updateNobleStrength();

            result += n.getName() + " defeats " + name + "\n";
        }
        return result;
    }
}

class Warrior
{
    private String name;
    private double strength;
    private Noble noble;

    Warrior(String s, int i)
    {
        name = s;
        strength = i;
    }

    public String getName()
    {
        return name;
    }

    public double getStrength()
    {
        return strength;
    }

    public void setStrength(double i)
    {
        strength = i;
    }

    public void setNoble(Noble n)
    {
        noble = n;
    }

    public boolean isHired()
    {
        if(noble==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public String runaway()
    {
        if(noble!=null)
        {
            String s = "So long " + noble.getName() + ". I'm out'a here! -- " + name + "\n";
            noble.unhire(this);
            noble = null;
            return s;
        }
        else
        {
            return(name + " is not hired by any noble!\n");
        }
    }
}