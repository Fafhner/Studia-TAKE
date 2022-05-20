package com.spatial.views;


import com.spatial.models.figure.WKTGeometry;
import com.spatial.models.figure.WKTType;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class DrawPanelUI extends JPanel {
    DrawPanel drawPanel;
    JPanel borderPanel;
    JPanel ui;
    JLabel xPos;
    JLabel yPos;


    public DrawPanelUI(WKTGeometry geometry) {

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();

        drawPanel = new DrawPanel(geometry);
        ui = new JPanel();

        borderPanel = new JPanel();
        borderPanel.setLayout(gridBagLayout);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 0.99;
        c.weightx = 0.99;
        borderPanel.add(drawPanel, c);
        borderPanel.setBorder(BorderFactory.createLineBorder(Color.black));


        c.anchor = GridBagConstraints.NORTH;
        this.add(borderPanel, c);
        c.fill = GridBagConstraints.NONE;
        c.gridy  = 1;
        c.weighty = 0.01;
        c.weightx = 0.01;
        c.anchor = GridBagConstraints.SOUTH;
        this.add(ui, c);

        SpinnerModel model = new SpinnerNumberModel(1.f, 0.01f, 2.f, 0.01f);

        JSpinner spinner = new JSpinner(model);

        ui.add(spinner);

        ChangeListener spinnerListener =
                e -> {
                    drawPanel.scale = ((Double) spinner.getModel().getValue()).floatValue();
                    drawPanel.doCentering();
                    drawPanel.repaint();
                };

        model.addChangeListener(spinnerListener);

        JButton lButton = new JButton("\u2190");
        JButton rButton = new JButton("\u2192");
        JButton upButton = new JButton("\u2191");
        JButton dwButton = new JButton("\u2193");

        ui.add(lButton);
        ui.add(rButton);
        ui.add(upButton);
        ui.add(dwButton);

        lButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.xPos += 10;
                drawPanel.repaint();
            }
        });

        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.xPos -= 10;
                drawPanel.repaint();
            }
        });

        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.yPos += 10;
                drawPanel.repaint();
            }
        });

        dwButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.yPos -= 10;
                drawPanel.repaint();
            }
        });

        JPanel xyPanel = new JPanel();

        xPos = new JLabel("X = 0.0");
        yPos = new JLabel("Y = 0.0");
        xyPanel.add(xPos);
        xyPanel.add(yPos);
        xyPanel.setLayout(new GridLayout(2, 1));
        ui.add(xyPanel);

        drawPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                String x = "X = " + (e.getX() - drawPanel.xPos)/(drawPanel.getScale()) ;
                String y = "Y = " + (e.getY() - drawPanel.yPos)/(-drawPanel.getScale()) ;
                xPos.setText(x);
                yPos.setText(y);
            }
        });

    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public static JFrame createFrame(WKTGeometry figure) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Visualization");
        DrawPanelUI panel = new DrawPanelUI(figure);
        frame.add(panel);

        return frame;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String data = "LINESTRING (18.914459 49.70644, 18.832359 49.801899, 18.803619 49.845089, 18.7995 49.899811, 18.84873 49.941078, 18.90906 49.93676, 18.976561 49.92104, 19.047501 49.914391, 19.132481 49.94194, 19.16074 49.98304, 19.213921 50.021881, 19.282881 50.02301)\n" +
                "LINESTRING (14.80079 52.079731, 14.89331 52.037868, 14.9572 52.031139, 15.08692 52.0359, 15.15142 52.02224, 15.24632 52.041569, 15.31121 52.064362, 15.37798 52.066631, 15.42409 52.049911, 15.50333 52.036549, 15.56495 52.011162, 15.60371 51.991859, 15.68539 51.992149, 15.74982 51.97588, 15.80506 51.934189, 15.83837 51.887272, 15.83312 51.811798, 15.82839 51.777431, 15.84542 51.748268, 15.89942 51.71793, 16.05117 51.66069, 16.13081 51.628681, 16.197281 51.625931, 16.267269 51.625511, 16.307699 51.626572, 16.39106 51.58992, 16.43644 51.57053, 16.473049 51.5144, 16.48702 51.466839, 16.4907 51.407612, 16.489441 51.36879, 16.47822 51.313759, 16.482309 51.2477, 16.495899 51.204689, 16.5291 51.198662, 16.5875 51.197781, 16.67827 51.204472, 16.747351 51.208321, 16.83502 51.205681, 16.88674 51.1931, 16.93961 51.157711, 16.970341 51.126419, 17.01557 51.095402, 17.049391 51.073269, 17.116301 51.042641, 17.160179 51.036629, 17.25939 51.011101, 17.304211 50.982239, 17.395981 50.956459, 17.444189 50.92989, 17.540791 50.86758, 17.573931 50.852131, 17.59252 50.83873, 17.64747 50.819031, 17.676769 50.808048, 17.71336 50.794891, 17.75016 50.774879, 17.807989 50.77338, 17.840839 50.762421, 17.87027 50.744549, 17.928329 50.733879, 17.93231 50.715759)\n" +
                "LINESTRING (14.56134 50.28437, 14.60777 50.249771, 14.64611 50.223949, 14.66837 50.215729, 14.69864 50.1987, 14.71755 50.188049, 14.74401 50.17313, 14.79847 50.16391, 14.88913 50.149231, 14.91759 50.150341, 15.04231 50.152851, 15.11784 50.146549, 15.16232 50.12764, 15.19097 50.085312, 15.21207 50.04726, 15.25165 50.000751, 15.2715 49.976349, 15.33962 49.96965, 15.4417 49.982342, 15.51454 50.005371, 15.58816 50.019249, 15.68754 50.022491, 15.79792 50.021431, 15.86875 50.025909, 15.8952 50.0541, 15.90696 50.088692, 15.88443 50.154202, 15.88876 50.19313, 15.92664 50.262741, 15.93896 50.290501, 15.93164 50.34277, 15.90823 50.369461, 15.86349 50.393211, 15.82977 50.41272, 15.81292 50.44643, 15.80662 50.48275, 15.78921 50.5233, 15.7516 50.544949, 15.71895 50.548481, 15.689 50.563511, 15.66569 50.58559, 15.66303 50.61974, 15.66018 50.65617, 15.63807 50.707951)\n" +
                "MULTILINESTRING ((25.565729 49.843349, 25.784321 49.85622, 25.88303 49.87571, 25.99531 49.889721, 26.105709 49.890011, 26.180241 49.912991, 26.24844 49.94088, 26.354759 49.9366, 26.369824 49.938701), (26.369824 50.685736, 26.3055 50.688278, 26.24156 50.696739, 26.144119 50.748348, 26.10339 50.791969, 26.111389 50.84642, 26.172729 50.867962, 26.20101 50.886879, 26.2372 50.909889, 26.288719 50.913639))\n" +
                "LINESTRING (17.93231 50.715759, 17.940821 50.67701, 17.964729 50.597488, 17.9625 50.549568, 17.977909 50.51326, 18.01499 50.470371, 18.054991 50.450298, 18.11668 50.421329, 18.182039 50.385521, 18.23315 50.342701, 18.273439 50.299751, 18.29561 50.265751, 18.322081 50.19529, 18.323259 50.13829, 18.302799 50.094769, 18.29962 50.0765, 18.30044 50.037739, 18.32616 49.996922, 18.35133 49.981171, 18.38385 49.951809, 18.388309 49.90395, 18.3682 49.844479, 18.27463 49.75013, 18.077049 49.59985, 18.01433 49.574039, 17.961611 49.571129, 17.91494 49.602482, 17.8857 49.638599, 17.838989 49.667641, 17.71903 49.668259, 17.663071 49.653759, 17.60379 49.634609, 17.58305 49.62289, 17.56946 49.60899, 17.52141 49.574009)\n" +
                "LINESTRING (17.22123 48.915642, 17.20652 48.914059, 17.14031 48.90374, 17.12566 48.890968, 17.10495 48.872261, 17.094 48.862061, 17.05862 48.83308, 17.04163 48.816929, 17.021299 48.78904, 16.98877 48.752571, 16.963511 48.704239, 16.960871 48.699181, 16.958229 48.694118, 16.955601 48.689079, 16.93 48.639969)\n" +
                "LINESTRING (17.066219 48.128929, 17.069241 48.12899)\n" +
                "LINESTRING (17.0611 48.128799, 17.066219 48.128929)\n" +
                "LINESTRING (17.034031 48.128159, 17.0611 48.128799)\n" +
                "LINESTRING (22.64699 48.091751, 22.63686 48.087029, 22.58176 48.08102, 22.532619 48.047958, 22.499571 48.043831, 22.47076 48.051819, 22.438181 48.05743, 22.391239 48.068241, 22.34169 48.087551)\n" +
                "LINESTRING (17.25149 48.0256, 17.25152 48.025589)\n" +
                "LINESTRING (20.3561 48.29813, 20.42868 48.287739, 20.483219 48.273491, 20.568939 48.27256, 20.637211 48.260361, 20.687981 48.236919, 20.714479 48.204651, 20.75724 48.144779, 20.800249 48.096298, 20.843531 48.0592, 20.89045 48.031158, 20.916479 47.992001, 20.96686 47.96843, 21.034781 47.960548)\n" +
                "LINESTRING (14.47812 49.91774, 14.47866 49.901661, 14.49192 49.840549, 14.48297 49.753349, 14.43699 49.682819, 14.34965 49.637859, 14.29995 49.60593, 14.26485 49.57008, 14.21137 49.50824, 14.21807 49.444561, 14.25719 49.373219, 14.2943 49.320061, 14.3453 49.267509, 14.36622 49.234169, 14.37852 49.216431, 14.43615 49.200699, 14.46101 49.19722, 14.49446 49.070728, 14.47683 49.001419, 14.44884 48.931641, 14.40241 48.838181, 14.35942 48.781422, 14.37126 48.697399, 14.38927 48.62051, 14.33387 48.586021, 14.28245 48.58139, 14.20721 48.60339, 14.1513 48.611881, 14.06729 48.653622, 14.01248 48.712711, 13.96274 48.788029, 13.90048 48.848999, 13.822 48.897701, 13.71267 48.940231)\n" +
                "LINESTRING (19.95549 48.813141, 19.95491 48.73111, 19.98237 48.70821, 20.009769 48.680752, 20.02331 48.648788, 20.008921 48.575932, 20.011869 48.514389, 20.028669 48.468731, 20.04899 48.43446, 20.04882 48.416229, 20.0417 48.386631, 20.010651 48.363979, 19.97962 48.34132, 19.9883 48.315411, 20.096149 48.301971, 20.168209 48.301449, 20.25054 48.300789, 20.34317 48.299969, 20.3561 48.29813)\n" +
                "LINESTRING (24.534109 48.20406, 24.53801 48.306751, 24.54447 48.36816, 24.55319 48.418041, 24.578609 48.462551, 24.6171 48.499569, 24.661539 48.527149, 24.701599 48.545769, 24.83803 48.55962, 24.916901 48.55336, 24.963499 48.53727, 25.041269 48.521832, 25.160219 48.504169, 25.275101 48.48201, 25.346661 48.473549, 25.50316 48.42598, 25.67366 48.38195, 25.81365 48.344028, 25.926689 48.312111, 26.01535 48.279282, 26.08963 48.242668, 26.19356 48.197289, 26.31925 48.191929, 26.36376 48.19809)\n" +
                "LINESTRING (17.069241 48.12899, 17.08053 48.131321, 17.092159 48.132542, 17.103951 48.133141, 17.11598 48.133869, 17.12826 48.134819, 17.13983 48.134529, 17.149929 48.13158, 17.15884 48.126129, 17.166401 48.119579, 17.17231 48.112869, 17.176649 48.105511, 17.18042 48.097931, 17.184349 48.090469, 17.18791 48.082932, 17.191771 48.075481, 17.19664 48.068329, 17.202311 48.061378, 17.20866 48.05471, 17.215719 48.048439, 17.2237 48.042648, 17.232121 48.03709, 17.240669 48.03167, 17.24971 48.026611, 17.25149 48.0256)\n" +
                "LINESTRING (22.69544 47.81538, 22.69278 47.81702, 22.62077 47.860321, 22.58502 47.886509, 22.53903 47.912991, 22.506611 47.939049, 22.460239 47.960941, 22.39765 47.99696, 22.34169 48.087551)\n" +
                "LINESTRING (23.955271 57.011379, 24.02837 56.973541, 24.044491 56.958431, 24.071239 56.94788, 24.09796 56.937271, 24.12447 56.926498, 24.150579 56.915459, 24.17626 56.904148, 24.2017 56.89267, 24.22706 56.881142, 24.25252 56.869678, 24.268709 56.862572)\n"
                ;

        WKTGeometry main = new WKTGeometry(WKTType.MULTIPOLYGON);
        String[] f = data.split("\n");
        for(String ff : f) {
            WKTGeometry fig = WKTGeometry.readFromText(ff);
            main.addFigure(fig);
        }

        DrawPanelUI panel = new DrawPanelUI(main);
        frame.add(panel);
        frame.setVisible(true);
    }

}