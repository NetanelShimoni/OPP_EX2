package gui;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Point3D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import java.io.Serializable;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class GUI_graph extends JFrame implements ActionListener, MouseListener,Serializable,GUI_Running
{
    private graph g;
    private int mc;
    private Graph_Algo ga = new Graph_Algo();
    private static final long serialVersionUID = 1L;

    private static final String SAVE = "Save";
    private static final String LOAD = "Load";
    private static final String IS_CONNECTED = "Graph connected?";
    private static final String ADD_NODE = "Add node";
    private static final String ADD_EDGE = "Add edge";

    public GUI_graph()
    {
        this.g=new DGraph();
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
        this.ga.g.listen(this);
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.initMenuBar();
        this.initMcThread();
    }

    private void initMenuBar() {

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu algo = new Menu("Algorithms");
        menuBar.add(file);
        menuBar.add(algo);
   /*     menuBar.add(node);
        menuBar.add(edge);*/
        this.setMenuBar(menuBar);
        MenuItem save = new MenuItem(SAVE);
        save.addActionListener(this);
        MenuItem load = new MenuItem(LOAD);
        load.addActionListener(this);

        MenuItem edge = new MenuItem(ADD_EDGE);
        edge.addActionListener(this);



        MenuItem ic = new MenuItem(IS_CONNECTED);
        ic.addActionListener(this);



        file.add(save);
        file.add(load);
        algo.add(ic);
        algo.add(edge);


        this.addMouseListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        super.setPreferredSize(new Dimension(700,700));
        this.draeGraph(g);
    }
    public void initMcThread(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (this){
                        if(g.getMC()!=mc){
                            mc=g.getMC();
                            repaint();

                        }
                    }
                }
            }
        });
        t.start();
    }
    private void draeGraph(Graphics g) {
        super.paint(g);


        g.setFont(new Font("David",1,17));
        Iterator it = this.g.getV().iterator();

        while (it.hasNext()){
            node_data v = (node_data) it.next();
            Point3D p=v.getLocation();
            g.setColor(Color.BLACK);
            g.fillOval(p.ix(),p.iy(),10,10);
            g.drawString(""+(v.getKey()),p.ix(),p.iy());
            Collection <edge_data> edge =this.g.getE(v.getKey());
            if (edge!=null){
                Iterator iterator =edge.iterator();
                while (iterator.hasNext()){
                    edge_data e = (edge_data) iterator.next();
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
                    g.fillOval((int) ((int)(p.ix()*0.2)+(0.8*y.getLocation().ix())+2),(int)((p.iy()*0.2)+(0.8*y.getLocation().iy())),9,9);
                }
            }
        }
    }
    public static double placeontheline(Point3D p1,Point3D p2,double x0) {    //return the f(x0)
        double m=((p2.y()-p1.y())/(p2.ix()-p1.x()));
        return m*(x0-p2.x())+p2.y();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case SAVE: {
                Graph_Algo t = new Graph_Algo();
                t.init((DGraph) this.g);
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
                j.setDialogTitle("Save graph to txt file");
                int i = j.showSaveDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {
                    System.out.println("path: " + j.getSelectedFile().getAbsolutePath());
                    t.save(j.getSelectedFile().getAbsolutePath());

                }

            }

            break;


            case LOAD: {
                Graph_Algo t = new Graph_Algo();
                t.init((DGraph) this.g);
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
                j.setDialogTitle("graph load");
                int i = j.showSaveDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {
                    System.out.println("path: " + j.getSelectedFile().getAbsolutePath());
                    t.init(j.getSelectedFile().getAbsolutePath());
                    graph y = t.copy();
                    initGUI(y);

                }
                break;
            }
                case IS_CONNECTED: {
                    JFrame isIt = new JFrame();
                    Graph_Algo isCga = new Graph_Algo();
                    isCga.init(this.g);
                    if (isCga.isConnected()) {
                        //System.out.println("The graph is Connected !");
                        JOptionPane.showMessageDialog(isIt, " Connected !");
                    } else {
                        // System.out.println("The graph is not Connected !");
                        JOptionPane.showMessageDialog(isIt, "not Connected !");
                    }
                    break;
                }
            case ADD_EDGE: {
                String src = JOptionPane.showInputDialog("insert source node");
                String dest = JOptionPane.showInputDialog("insert destention node");
                String weight = JOptionPane.showInputDialog("insert edge weight");
                int src1, dest1;
                double weight1;
                try {
                    src1 = Integer.parseInt(src);
                    dest1 = Integer.parseInt(dest);
                    weight1 = Double.parseDouble(weight);
                    g.connect(src1, dest1, weight1);
                    repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "error:" + ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
                default:
                    throw new IllegalStateException("Unexpected value: " + s);


        }
        repaint();

    }

    private void initGUI(graph g) {
        this.g=g;
        this.setSize(1280, 720);
        this.setTitle("GRAPH");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("mari.png");
        this.setIconImage(img.getImage());
        this.setResizable(true);
        this.initMenuBar();
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();


    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point3D p = new Point3D(x,y);
        Node temp = new Node(p);
        this.g.addNode(temp);
        repaint();
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

    @Override
    public void graph_change() {
      this.repaint();
    }
}
