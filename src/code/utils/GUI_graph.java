package code.utils;

import code.algorithms.Graph_Algo;
import code.dataStructure.*;
import code.utils.Point3D;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.LinkedList;

import java.io.Serializable;
import javax.swing.JFrame;

public class GUI_graph extends JFrame implements ActionListener, MouseListener,Serializable
{
    private static final String RM_EDGE = "Remove egde";
    private graph g;
    private int mc;
    private Graph_Algo ga = new Graph_Algo();

    private static final String SAVE = "Save";
    private static final String LOAD = "Load";
    private static final String SHORTEST_PATH_DISTANCE = "Shortest Path Distance";
    private static final String SHORTEST_PATH = "Shortest Path";
    private static final String IS_CONNECTED = "Graph connected?";
    private static final String TSP = "TSP";
    private static final String ADD_NODE = "Add node";
    private static final String RM_NODE = "Remove node";
    private static final String ADD_EDGE = "Add edge";

    public GUI_graph()
    {
        this.g=new DGraph(20);
        this.mc=this.g.getMC();
        this.ga.init(this.g);
        initGUI();
    }

    public GUI_graph( DGraph g) {
            if (g == null)
                throw new RuntimeException("graph cannot be null");
            this.g = g;
            this.mc = g.getMC();
            this.ga.init(this.g);
            initGUI();

    }

    private void initGUI()
    {
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.initMenuBar();
        this.initMCT();
    }

    private void initMCT() {
    }

    private void initMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu algo = new Menu("Algorithms");
        Menu node = new Menu("Node");
        Menu edge = new Menu("Edge");
        menuBar.add(file);
        menuBar.add(algo);
        menuBar.add(node);
        menuBar.add(edge);
        this.setMenuBar(menuBar);
        MenuItem save = new MenuItem(SAVE);
        save.addActionListener(this);
        MenuItem load = new MenuItem(LOAD);
        load.addActionListener(this);

        MenuItem spd = new MenuItem(SHORTEST_PATH_DISTANCE);
        spd.addActionListener(this);
        MenuItem sp = new MenuItem(SHORTEST_PATH);
        sp.addActionListener(this);
        MenuItem ic = new MenuItem(IS_CONNECTED);
        ic.addActionListener(this);
        MenuItem tsp = new MenuItem(TSP);
        tsp.addActionListener(this);

        MenuItem nAdd = new MenuItem(ADD_NODE);
        nAdd.addActionListener(this);
        MenuItem nRm = new MenuItem(RM_NODE);
        nRm.addActionListener(this);

        MenuItem eAdd = new MenuItem(ADD_EDGE);
        eAdd.addActionListener(this);
        MenuItem eRm = new MenuItem(RM_EDGE);
        eRm.addActionListener(this);
        file.add(save);
        file.add(load);
        algo.add(spd);
        algo.add(sp);
        algo.add(ic);
        algo.add(tsp);
        node.add(nAdd);
        node.add(nRm);
        edge.add(eAdd);
        edge.add(eRm);
        this.addMouseListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.draeGraph(g);
    }

    private void draeGraph(Graphics g) {
        g.setFont(new Font("David",1,17));
        for (node_data v: this.g.getV()) {
            Point3D p=v.getLocation();
            g.setColor(Color.BLACK);
            g.fillOval(p.ix(),p.iy()-5,10,10);
            g.drawString(""+(v.getKey()),p.ix(),p.iy()-5);
            Collection <edge_data> edge =this.g.getE(v.getKey());
            if (edge!=null){
                for (edge_data e: edge) {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Ariel",Font.BOLD,19));
                    node_data y =  this.g.getNode(e.getDest());
                    g.drawLine(p.ix(),p.iy(),y.getLocation().ix(),y.getLocation().iy());
                    g.drawString(""+e.getWeight(),(p.ix()+y.getLocation().ix())/2,(p.iy()+y.getLocation().iy())/2);
                    g.setColor(Color.YELLOW);
                    int c1=0,c2=0;
                    for (int i = 0; i <2 ; i++) {
                         c1=(p.ix()+y.getLocation().ix())/2;
                        c2=(p.iy()+y.getLocation().iy())/2;
                    }
                    g.fillOval(c1-2,c2-2,7,7);
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        switch (s) {
            case SAVE: {
                //   FileDialog f =new FileDialog(thi)
                String p = lsDilaog("Save txt file", FileDialog.SAVE);
                this.ga.save(p);
                break;
            }

            case LOAD: {
                String p =lsDilaog("Load the txt",FileDialog.LOAD);
                this.ga.init(p);
                this.g=this.ga.copy();
                repaint();
                break;
            }
            case SHORTEST_PATH_DISTANCE: {
                this.shortpath_gui("DISTANCE");
            }


        }
       repaint();

    }

    private void shortpath_gui(String distance) {

    }

    private String lsDilaog(String save_txt_file, int save) {
        FileDialog f = new FileDialog(this, save_txt_file, save);
        f.setFile("*.txt");
        f.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.endsWith(".txt");
            }
        });
        f.setVisible(true);
        System.out.println(f.getFile()+f.getDirectory());
        return f.getFile();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {

        System.out.println("mousePressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }
}