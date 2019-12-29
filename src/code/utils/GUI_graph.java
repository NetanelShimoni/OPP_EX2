package code.utils;
import code.*;
import code.dataStructure.DGraph;
import code.dataStructure.graph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.JFrame;

public class GUI_graph extends JFrame implements ActionListener, MouseListener {
       public DGraph graph = new DGraph();


    private void initGUi() {
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    public void paint(){
        Point3D prev = null;
        int i=0;
        for (int j = 0; j <this.graph.nodeSize() ; j++) {
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
