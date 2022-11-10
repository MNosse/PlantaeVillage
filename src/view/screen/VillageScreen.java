package view.screen;

import controller.controller.VillageScreenController;
import controller.obsever.GameFrameObserver;
import controller.obsever.VillageScreenObserver;
import global.GlobalVariables;
import model.Repository;
import view.components.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class VillageScreen extends JPanel implements VillageScreenObserver{
    
    private VillageScreenController controller;
    private ButtonGroup btgOptionsQ1, btgOptionsQ2, btgOptionsQ3, btgOptionsQ4, btgOptionsQ5, btgOptionsQ6, btgOptionsQ7, btgOptionsQ8;
    private JButton btnQuit, btnContinue, btnCancel, btnFinish;
    private JLabel lblPlayer, lblTeleport, lblInteractive, lblDialog, lblPause, lblTest;
    private JScrollPane scpQuestions;
    private JTextArea txaDialogText;
    private boolean interactiveEnabled, teleportEnabled;
    private Thread threadWalk;
    
    public VillageScreen() {
        controller = new VillageScreenController(this);
        threadWalk = new Thread();
        interactiveEnabled = false;
        teleportEnabled = false;
        initialize();
        controller.isFirstEnter();
    }
    
    public VillageScreen(int plawerRow, int playerColumn) {
        controller = new VillageScreenController(this, plawerRow, playerColumn);
        threadWalk = new Thread();
        interactiveEnabled = false;
        teleportEnabled = false;
        initialize();
    }
    
    private void initialize() {
        //JLABEL lblPlayer
        lblPlayer = new JLabel(GlobalVariables.IMAGES.get(controller.getPlayerImageIdleName()));
        lblPlayer.setBounds(controller.getPlayerColumn()*GlobalVariables.TILE_SIZE, (controller.getPlayerRow()-1)*GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblFountain
        JLabel lblFountain = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.FOUNTAIN_KEY));
        lblFountain.setBounds(GlobalVariables.TILE_SIZE*18, GlobalVariables.TILE_SIZE*4, GlobalVariables.TILE_SIZE*3, GlobalVariables.TILE_SIZE*3);
        //JLABEL lblLeftLamp
        JLabel lblLeftLamp = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.LEFT_LAMP_KEY));
        lblLeftLamp.setBounds(GlobalVariables.TILE_SIZE*11, -GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*5, GlobalVariables.TILE_SIZE*5);
        //JLABEL lblRightLamp
        JLabel lblRightLamp = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.RIGHT_LAMP_KEY));
        lblRightLamp.setBounds(GlobalVariables.TILE_SIZE*23, -GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*5, GlobalVariables.TILE_SIZE*5);
        //JLABEL lblFlowers
        JLabel lblFlowers = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.FLOWERS_KEY));
        lblFlowers.setBounds(GlobalVariables.TILE_SIZE*29, GlobalVariables.TILE_SIZE*12, GlobalVariables.TILE_SIZE*7, GlobalVariables.TILE_SIZE*4);
        //JLABEL lblNpc1
        JLabel lblNpc1 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC7_KEY));
        lblNpc1.setBounds(GlobalVariables.TILE_SIZE*33, GlobalVariables.TILE_SIZE*15, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc2
        JLabel lblNpc2 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC4_KEY));
        lblNpc2.setBounds(GlobalVariables.TILE_SIZE*16, GlobalVariables.TILE_SIZE*12, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc3
        JLabel lblNpc3 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC3_KEY));
        lblNpc3.setBounds(GlobalVariables.TILE_SIZE*15, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc4
        JLabel lblNpc4 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC8_KEY));
        lblNpc4.setBounds(GlobalVariables.TILE_SIZE*21, GlobalVariables.TILE_SIZE*2, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc5
        JLabel lblNpc5 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC5_KEY));
        lblNpc5.setBounds(GlobalVariables.TILE_SIZE*26, GlobalVariables.TILE_SIZE*4, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblNpc6
        JLabel lblNpc6 = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.NPC6_KEY));
        lblNpc6.setBounds(GlobalVariables.TILE_SIZE*8, GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*2);
        //JLABEL lblTeleport
        lblTeleport = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.TELEPORT_ICON_KEY));
        lblTeleport.setBounds(0, 0, 0, 0);
        //JLABEL lblInteractive
        lblInteractive = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.INTERACTIVE_ICON_KEY));
        lblTeleport.setBounds(0, 0, 0, 0);
        //JTEXTAREA txaDialogText
        txaDialogText = new JTextArea();
        txaDialogText.setFont(GlobalVariables.FONT_3.deriveFont(GlobalVariables.ONE_PIXEL_SIZE*17f));
        txaDialogText.setLineWrap(true);
        txaDialogText.setWrapStyleWord(true);
        txaDialogText.setVisible(true);
        txaDialogText.setEditable(false);
        txaDialogText.setMinimumSize(new Dimension((GlobalVariables.TILE_SIZE*12), (GlobalVariables.TILE_SIZE*2)));
        txaDialogText.setPreferredSize(new Dimension((GlobalVariables.TILE_SIZE*12), (GlobalVariables.TILE_SIZE*2)));
        //JLABEL lblDialog
        lblDialog = new JLabel();
        lblDialog.setVisible(true);
        lblDialog.setBounds(GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE*3);
        lblDialog.setIcon(GlobalVariables.IMAGES.get(GlobalVariables.DIALOG_BOX_KEY));
        lblDialog.setLayout(new GridBagLayout());
        lblDialog.setVisible(false);
        lblDialog.add(txaDialogText, new GridBagConstraints());
        //JLabel lblPlant
        JLabel lblPlant = new JLabel(GlobalVariables.IMAGES.get(Repository.getInstance().getPlant().getIMAGE_NAME()));
        //JBUTTON btnContinue
        btnContinue = new Button((GlobalVariables.TILE_SIZE * 7), (GlobalVariables.TILE_SIZE * 2), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2), "Continuar", 30f);
        //JBUTTON btnQuit
        btnQuit = new Button((GlobalVariables.TILE_SIZE * 7), (GlobalVariables.TILE_SIZE * 2), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2), "Sair", 30f);
        //JLABEL lblPause
        GridBagConstraints constraints = new GridBagConstraints();
        lblPause = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.PAUSE_SCREEN_MAP_KEY));
        lblPause.setBounds(0, 0, GlobalVariables.SCREEN_WIDTH, GlobalVariables.SCREEN_HEIGHT);
        lblPause.setLayout(new GridBagLayout());
        lblPause.add(lblPlant);
        constraints.insets = new Insets(GlobalVariables.TILE_SIZE,  0, 0, 0);
        constraints.gridy = 1;
        lblPause.add(btnContinue, constraints);
        constraints.gridy = 2;
        lblPause.add(btnQuit, constraints);
        lblPause.setVisible(false);
        //FONT testFont
        Font testFont = GlobalVariables.FONT_3.deriveFont(GlobalVariables.ONE_PIXEL_SIZE*14f);
        //JTEXTAREA txaQuestion1
        JTextArea txaQuestion1 = new JTextArea("1) A qual grupo pertence a planta recebida?");
        txaQuestion1.setLayout(new GridBagLayout());
        txaQuestion1.setLineWrap(true);
        txaQuestion1.setWrapStyleWord(true);
        txaQuestion1.setEditable(false);
        txaQuestion1.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*20));
        txaQuestion1.setMinimumSize(txaQuestion1.getPreferredSize());
        txaQuestion1.setMaximumSize(txaQuestion1.getPreferredSize());
        txaQuestion1.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q1
        JRadioButton jrbOption1Q1 = new JRadioButton("Angiosperma");
        jrbOption1Q1.setActionCommand("Angiosperma");
        jrbOption1Q1.setFont(testFont);
        jrbOption1Q1.setBackground(Color.white);
        //JRADIOBUTTON jrbOption2Q1
        JRadioButton jrbOption2Q1 = new JRadioButton("Briófita");
        jrbOption2Q1.setActionCommand("Briófita");
        jrbOption2Q1.setFont(testFont);
        jrbOption2Q1.setBackground(Color.white);
        //JRADIOBUTTON jrbOption3Q1
        JRadioButton jrbOption3Q1 = new JRadioButton("Gimnosperma");
        jrbOption3Q1.setActionCommand("Gimnosperma");
        jrbOption3Q1.setFont(testFont);
        jrbOption3Q1.setBackground(Color.white);
        //JRADIOBUTTON jrbOption4Q1
        JRadioButton jrbOption4Q1 = new JRadioButton("Pteridófita");
        jrbOption4Q1.setActionCommand("Pteridófita");
        jrbOption4Q1.setFont(testFont);
        jrbOption4Q1.setBackground(Color.white);
        //BUTTONGROUP btgOptionsQ1
        btgOptionsQ1 = new ButtonGroup();
        btgOptionsQ1.add(jrbOption1Q1);
        btgOptionsQ1.add(jrbOption2Q1);
        btgOptionsQ1.add(jrbOption3Q1);
        btgOptionsQ1.add(jrbOption4Q1);
        //JTEXTAREA txaQuestion2
        JTextArea txaQuestion2 = new JTextArea("2) A planta recebida é uma planta vascular.");
        txaQuestion2.setLineWrap(true);
        txaQuestion2.setWrapStyleWord(true);
        txaQuestion2.setEditable(false);
        txaQuestion2.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*20));
        txaQuestion2.setMinimumSize(txaQuestion2.getPreferredSize());
        txaQuestion2.setMaximumSize(txaQuestion2.getPreferredSize());
        txaQuestion2.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q2
        JRadioButton jrbOption1Q2 = new JRadioButton("Verdadeiro");
        jrbOption1Q2.setActionCommand("Verdadeiro");
        jrbOption1Q2.setFont(testFont);
        jrbOption1Q2.setBackground(Color.white);
        //JRADIOBUTTON jrbOption2Q2
        JRadioButton jrbOption2Q2 = new JRadioButton("False");
        jrbOption2Q2.setActionCommand("False");
        jrbOption2Q2.setFont(testFont);
        jrbOption2Q2.setBackground(Color.white);
        //BUTTONGROUP btgOptionsQ2
        btgOptionsQ2 = new ButtonGroup();
        btgOptionsQ2.add(jrbOption1Q2);
        btgOptionsQ2.add(jrbOption2Q2);
        //JTEXTAREA txaQuestion3
        JTextArea txaQuestion3 = new JTextArea("3) Quanto ao ambiente para reprodução da planta recebida, selecione a opção correta:");
        txaQuestion3.setLineWrap(true);
        txaQuestion3.setWrapStyleWord(true);
        txaQuestion3.setEditable(false);
        txaQuestion3.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*40));
        txaQuestion3.setMinimumSize(txaQuestion3.getPreferredSize());
        txaQuestion3.setMaximumSize(txaQuestion3.getPreferredSize());
        txaQuestion3.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q3
        JRadioButton jrbOption1Q3 = new JRadioButton("Dependente da água");
        jrbOption1Q3.setActionCommand("Dependente da água");
        jrbOption1Q3.setFont(testFont);
        jrbOption1Q3.setBackground(Color.white);
        //JRADIOBUTTON jrbOption2Q3
        JRadioButton jrbOption2Q3 = new JRadioButton("Dependente de insetos");
        jrbOption2Q3.setActionCommand("Dependente de insetos");
        jrbOption2Q3.setFont(testFont);
        jrbOption2Q3.setBackground(Color.white);
        //JRADIOBUTTON jrbOption3Q3
        JRadioButton jrbOption3Q3 = new JRadioButton("Dependente da ação do vento");
        jrbOption3Q3.setActionCommand("Dependente da ação do vento");
        jrbOption3Q3.setFont(testFont);
        jrbOption3Q3.setBackground(Color.white);
        //BUTTONGROUP btgOptionsQ3
        btgOptionsQ3 = new ButtonGroup();
        btgOptionsQ3.add(jrbOption1Q3);
        btgOptionsQ3.add(jrbOption2Q3);
        btgOptionsQ3.add(jrbOption3Q3);
        //JTEXTAREA txaQuestion4
        JTextArea txaQuestion4 = new JTextArea("4) A planta recebida possui raízes.");
        txaQuestion4.setLineWrap(true);
        txaQuestion4.setWrapStyleWord(true);
        txaQuestion4.setEditable(false);
        txaQuestion4.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*20));
        txaQuestion4.setMinimumSize(txaQuestion4.getPreferredSize());
        txaQuestion4.setMaximumSize(txaQuestion4.getPreferredSize());
        txaQuestion4.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q4
        JRadioButton jrbOption1Q4 = new JRadioButton("Verdadeiro");
        jrbOption1Q4.setActionCommand("Verdadeiro");
        jrbOption1Q4.setFont(testFont);
        jrbOption1Q4.setBackground(Color.white);
        //JRADIOBUTTON jrbOption2Q4
        JRadioButton jrbOption2Q4 = new JRadioButton("Falso");
        jrbOption2Q4.setActionCommand("Falso");
        jrbOption2Q4.setFont(testFont);
        jrbOption2Q4.setBackground(Color.white);
        //BUTTONGROUP btgOptionsQ4
        btgOptionsQ4 = new ButtonGroup();
        btgOptionsQ4.add(jrbOption1Q4);
        btgOptionsQ4.add(jrbOption2Q4);
        //JTEXTAREA txaQuestion5
        JTextArea txaQuestion5 = new JTextArea("5) A planta recebida possui caule.");
        txaQuestion5.setLineWrap(true);
        txaQuestion5.setWrapStyleWord(true);
        txaQuestion5.setEditable(false);
        txaQuestion5.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*20));
        txaQuestion5.setMinimumSize(txaQuestion5.getPreferredSize());
        txaQuestion5.setMaximumSize(txaQuestion5.getPreferredSize());
        txaQuestion5.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q5
        JRadioButton jrbOption1Q5 = new JRadioButton("Verdadeiro");
        jrbOption1Q5.setActionCommand("Verdadeiro");
        jrbOption1Q5.setFont(testFont);
        jrbOption1Q5.setBackground(Color.white);
        //JRADIOBUTTON jrbOption2Q5
        JRadioButton jrbOption2Q5 = new JRadioButton("Falso");
        jrbOption2Q5.setActionCommand("Falso");
        jrbOption2Q5.setFont(testFont);
        jrbOption2Q5.setBackground(Color.white);
        //BUTTONGROUP btgOptionsQ5
        btgOptionsQ5 = new ButtonGroup();
        btgOptionsQ5.add(jrbOption1Q5);
        btgOptionsQ5.add(jrbOption2Q5);
        //JTEXTAREA txaQuestion6
        JTextArea txaQuestion6 = new JTextArea("6) A planta recebida possui folhas.");
        txaQuestion6.setLineWrap(true);
        txaQuestion6.setWrapStyleWord(true);
        txaQuestion6.setEditable(false);
        txaQuestion6.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*20));
        txaQuestion6.setMinimumSize(txaQuestion6.getPreferredSize());
        txaQuestion6.setMaximumSize(txaQuestion6.getPreferredSize());
        txaQuestion6.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q6
        JRadioButton jrbOption1Q6 = new JRadioButton("Verdadeiro");
        jrbOption1Q6.setActionCommand("Verdadeiro");
        jrbOption1Q6.setFont(testFont);
        jrbOption1Q6.setBackground(Color.white);
        //JRADIOBUTTON jrbOption2Q6
        JRadioButton jrbOption2Q6 = new JRadioButton("Falso");
        jrbOption2Q6.setActionCommand("Falso");
        jrbOption2Q6.setFont(testFont);
        jrbOption2Q6.setBackground(Color.white);
        //BUTTONGROUP btgOptionsQ6
        btgOptionsQ6 = new ButtonGroup();
        btgOptionsQ6.add(jrbOption1Q6);
        btgOptionsQ6.add(jrbOption2Q6);
        //JTEXTAREA txaQuestion7
        JTextArea txaQuestion7 = new JTextArea("7) Escolha a alternativa que represente corretamente TODAS as características da sua planta:\ni) Possui sementes.  ii) Possui frutos.  iii) Possui flores.\niv) Possui esporos.");
        txaQuestion7.setLineWrap(true);
        txaQuestion7.setWrapStyleWord(true);
        txaQuestion7.setEditable(false);
        txaQuestion7.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*80));
        txaQuestion7.setMinimumSize(txaQuestion7.getPreferredSize());
        txaQuestion7.setMaximumSize(txaQuestion7.getPreferredSize());
        txaQuestion7.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q7
        JRadioButton jrbOption1Q7 = new JRadioButton("V - V - V - V");
        jrbOption1Q7.setActionCommand("V - V - V - V");
        jrbOption1Q7.setFont(testFont);
        jrbOption1Q7.setBackground(Color.white);
        //JRADIOBUTTON jrbOption2Q7
        JRadioButton jrbOption2Q7 = new JRadioButton("V - F - F - V");
        jrbOption2Q7.setActionCommand("V - F - F - V");
        jrbOption2Q7.setFont(testFont);
        jrbOption2Q7.setBackground(Color.white);
        //JRADIOBUTTON jrbOption3Q7
        JRadioButton jrbOption3Q7 = new JRadioButton("F - F - F - V");
        jrbOption3Q7.setActionCommand("F - F - F - V");
        jrbOption3Q7.setFont(testFont);
        jrbOption3Q7.setBackground(Color.white);
        //JRADIOBUTTON jrbOption4Q7
        JRadioButton jrbOption4Q7 = new JRadioButton("F - F - F - F");
        jrbOption4Q7.setActionCommand("F - F - F - F");
        jrbOption4Q7.setFont(testFont);
        jrbOption4Q7.setBackground(Color.white);
        //BUTTONGROUP btgOptionsQ7
        btgOptionsQ7 = new ButtonGroup();
        btgOptionsQ7.add(jrbOption1Q7);
        btgOptionsQ7.add(jrbOption2Q7);
        btgOptionsQ7.add(jrbOption3Q7);
        btgOptionsQ7.add(jrbOption4Q7);
        //JTEXTAREA txaQuestion8
        JTextArea txaQuestion8 = new JTextArea("8) Onde é encontrado seu tipo de planta?");
        txaQuestion8.setLineWrap(true);
        txaQuestion8.setWrapStyleWord(true);
        txaQuestion8.setEditable(false);
        txaQuestion8.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*20));
        txaQuestion8.setMinimumSize(txaQuestion8.getPreferredSize());
        txaQuestion8.setMaximumSize(txaQuestion8.getPreferredSize());
        txaQuestion8.setFont(testFont);
        //JRADIOBUTTON jrbOption1Q8
        JRadioButton jrbOption1Q8 = new JRadioButton();
        jrbOption1Q8.setActionCommand("Pode facilmente ser encontrada em quase todos os cantos do planeta visto que compões 90% do reino Plantae.");
        jrbOption1Q8.setBackground(Color.white);
        //JTEXTAREA txaOption1Q8
        JTextArea txaOption1Q8 = new JTextArea("Pode facilmente ser encontrada em quase todos os cantos do planeta visto que compões 90% do reino Plantae.");
        txaOption1Q8.setLineWrap(true);
        txaOption1Q8.setWrapStyleWord(true);
        txaOption1Q8.setEditable(false);
        txaOption1Q8.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*40));
        txaOption1Q8.setMinimumSize(txaOption1Q8.getPreferredSize());
        txaOption1Q8.setMaximumSize(txaOption1Q8.getPreferredSize());
        txaOption1Q8.setFont(testFont);
        //JRADIOBUTTON jrbOption2Q8
        JRadioButton jrbOption2Q8 = new JRadioButton();
        jrbOption2Q8.setActionCommand("Normalmente encontrada em regiões temperadas e frias, porém também pode-se encontrar em zonas tropicais.");
        jrbOption2Q8.setBackground(Color.white);
        //JTEXTAREA txaOption2Q8
        JTextArea txaOption2Q8 = new JTextArea("Normalmente encontrada em regiões temperadas e frias, porém também pode-se encontrar em zonas tropicais.");
        txaOption2Q8.setLineWrap(true);
        txaOption2Q8.setWrapStyleWord(true);
        txaOption2Q8.setEditable(false);
        txaOption2Q8.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*40));
        txaOption2Q8.setMinimumSize(txaOption2Q8.getPreferredSize());
        txaOption2Q8.setMaximumSize(txaOption2Q8.getPreferredSize());
        txaOption2Q8.setFont(testFont);
        //JRADIOBUTTON jrbOption3Q8
        JRadioButton jrbOption3Q8 = new JRadioButton();
        jrbOption3Q8.setActionCommand("Normalmente localizadas em locais úmidos das florestas e ao longe de cursos de água.");
        jrbOption3Q8.setBackground(Color.white);
        //JTEXTAREA txaOption3Q8
        JTextArea txaOption3Q8 = new JTextArea("Normalmente localizadas em locais úmidos das florestas e ao longe de cursos de água.");
        txaOption3Q8.setLineWrap(true);
        txaOption3Q8.setWrapStyleWord(true);
        txaOption3Q8.setEditable(false);
        txaOption3Q8.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*40));
        txaOption3Q8.setMinimumSize(txaOption3Q8.getPreferredSize());
        txaOption3Q8.setMaximumSize(txaOption3Q8.getPreferredSize());
        txaOption3Q8.setFont(testFont);
        //JRADIOBUTTON jrbOption4Q8
        JRadioButton jrbOption4Q8 = new JRadioButton();
        jrbOption4Q8.setActionCommand("Pode viver em ambientes secos, todavia preferem ambientes úmidos.");
        jrbOption4Q8.setBackground(Color.white);
        //JTEXTAREA txaOption4Q8
        JTextArea txaOption4Q8 = new JTextArea("Pode viver em ambientes secos, todavia preferem ambientes úmidos.");
        txaOption4Q8.setLineWrap(true);
        txaOption4Q8.setWrapStyleWord(true);
        txaOption4Q8.setEditable(false);
        txaOption4Q8.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*450, GlobalVariables.ONE_PIXEL_SIZE*40));
        txaOption4Q8.setMinimumSize(txaOption4Q8.getPreferredSize());
        txaOption4Q8.setMaximumSize(txaOption4Q8.getPreferredSize());
        txaOption4Q8.setFont(testFont);
        //BUTTONGROUP btgOptionsQ8
        btgOptionsQ8 = new ButtonGroup();
        btgOptionsQ8.add(jrbOption1Q8);
        btgOptionsQ8.add(jrbOption2Q8);
        btgOptionsQ8.add(jrbOption3Q8);
        btgOptionsQ8.add(jrbOption4Q8);
        //JLABEL lblQuestions
        JLabel lblQuestions = new JLabel();
        SpringLayout layout = new SpringLayout();
        lblQuestions.setLayout(layout);
        lblQuestions.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*480, GlobalVariables.ONE_PIXEL_SIZE*600));
        lblQuestions.setMinimumSize(lblQuestions.getPreferredSize());
        lblQuestions.setOpaque(true);
        lblQuestions.setBackground(Color.white);
        lblQuestions.setForeground(Color.white);
        lblQuestions.add(txaQuestion1);
        lblQuestions.add(jrbOption1Q1);
        lblQuestions.add(jrbOption2Q1);
        lblQuestions.add(jrbOption3Q1);
        lblQuestions.add(jrbOption4Q1);
        lblQuestions.add(txaQuestion2);
        lblQuestions.add(jrbOption1Q2);
        lblQuestions.add(jrbOption2Q2);
        lblQuestions.add(txaQuestion3);
        lblQuestions.add(jrbOption1Q3);
        lblQuestions.add(jrbOption2Q3);
        lblQuestions.add(jrbOption3Q3);
        lblQuestions.add(txaQuestion4);
        lblQuestions.add(jrbOption1Q4);
        lblQuestions.add(jrbOption2Q4);
        lblQuestions.add(txaQuestion5);
        lblQuestions.add(jrbOption1Q5);
        lblQuestions.add(jrbOption2Q5);
        lblQuestions.add(txaQuestion6);
        lblQuestions.add(jrbOption1Q6);
        lblQuestions.add(jrbOption2Q6);
        lblQuestions.add(txaQuestion7);
        lblQuestions.add(jrbOption1Q7);
        lblQuestions.add(jrbOption2Q7);
        lblQuestions.add(jrbOption3Q7);
        lblQuestions.add(jrbOption4Q7);
        lblQuestions.add(txaQuestion8);
        lblQuestions.add(jrbOption1Q8);
        lblQuestions.add(txaOption1Q8);
        lblQuestions.add(jrbOption2Q8);
        lblQuestions.add(txaOption2Q8);
        lblQuestions.add(jrbOption3Q8);
        lblQuestions.add(txaOption3Q8);
        lblQuestions.add(jrbOption4Q8);
        lblQuestions.add(txaOption4Q8);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q1, 0, SpringLayout.SOUTH, txaQuestion1);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q1, 0, SpringLayout.SOUTH, txaQuestion1);
        layout.putConstraint(SpringLayout.NORTH, jrbOption3Q1, 0, SpringLayout.SOUTH, txaQuestion1);
        layout.putConstraint(SpringLayout.NORTH, jrbOption4Q1, 0, SpringLayout.SOUTH, txaQuestion1);
        layout.putConstraint(SpringLayout.WEST, jrbOption2Q1, 0, SpringLayout.EAST, jrbOption1Q1);
        layout.putConstraint(SpringLayout.WEST, jrbOption3Q1, 0, SpringLayout.EAST, jrbOption2Q1);
        layout.putConstraint(SpringLayout.WEST, jrbOption4Q1, 0, SpringLayout.EAST, jrbOption3Q1);
        layout.putConstraint(SpringLayout.NORTH, txaQuestion2, 0, SpringLayout.SOUTH, jrbOption1Q1);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q2, 0, SpringLayout.SOUTH, txaQuestion2);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q2, 0, SpringLayout.SOUTH, txaQuestion2);
        layout.putConstraint(SpringLayout.WEST, jrbOption2Q2, 0, SpringLayout.EAST, jrbOption1Q2);
        layout.putConstraint(SpringLayout.NORTH, txaQuestion3, 0, SpringLayout.SOUTH, jrbOption1Q2);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q3, 0, SpringLayout.SOUTH, txaQuestion3);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q3, 0, SpringLayout.SOUTH, txaQuestion3);
        layout.putConstraint(SpringLayout.WEST, jrbOption2Q3, 0, SpringLayout.EAST, jrbOption1Q3);
        layout.putConstraint(SpringLayout.NORTH, jrbOption3Q3, 0, SpringLayout.SOUTH, jrbOption1Q3);
        layout.putConstraint(SpringLayout.NORTH, txaQuestion4, 0, SpringLayout.SOUTH, jrbOption3Q3);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q4, 0, SpringLayout.SOUTH, txaQuestion4);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q4, 0, SpringLayout.SOUTH, txaQuestion4);
        layout.putConstraint(SpringLayout.WEST, jrbOption2Q4, 0, SpringLayout.EAST, jrbOption1Q4);
        layout.putConstraint(SpringLayout.NORTH, txaQuestion5, 0, SpringLayout.SOUTH, jrbOption1Q4);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q5, 0, SpringLayout.SOUTH, txaQuestion5);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q5, 0, SpringLayout.SOUTH, txaQuestion5);
        layout.putConstraint(SpringLayout.WEST, jrbOption2Q5, 0, SpringLayout.EAST, jrbOption1Q5);
        layout.putConstraint(SpringLayout.NORTH, txaQuestion6, 0, SpringLayout.SOUTH, jrbOption1Q5);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q6, 0, SpringLayout.SOUTH, txaQuestion6);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q6, 0, SpringLayout.SOUTH, txaQuestion6);
        layout.putConstraint(SpringLayout.WEST, jrbOption2Q6, 0, SpringLayout.EAST, jrbOption1Q6);
        layout.putConstraint(SpringLayout.NORTH, txaQuestion7, 0, SpringLayout.SOUTH, jrbOption1Q6);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q7, 0, SpringLayout.SOUTH, txaQuestion7);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q7, 0, SpringLayout.SOUTH, txaQuestion7);
        layout.putConstraint(SpringLayout.NORTH, jrbOption3Q7, 0, SpringLayout.SOUTH, txaQuestion7);
        layout.putConstraint(SpringLayout.NORTH, jrbOption4Q7, 0, SpringLayout.SOUTH, txaQuestion7);
        layout.putConstraint(SpringLayout.WEST, jrbOption2Q7, 0, SpringLayout.EAST, jrbOption1Q7);
        layout.putConstraint(SpringLayout.WEST, jrbOption3Q7, 0, SpringLayout.EAST, jrbOption2Q7);
        layout.putConstraint(SpringLayout.WEST, jrbOption4Q7, 0, SpringLayout.EAST, jrbOption3Q7);
        layout.putConstraint(SpringLayout.NORTH, txaQuestion8, 0, SpringLayout.SOUTH, jrbOption1Q7);
        layout.putConstraint(SpringLayout.NORTH, jrbOption1Q8, 0, SpringLayout.SOUTH, txaQuestion8);
        layout.putConstraint(SpringLayout.NORTH, txaOption1Q8, 0, SpringLayout.SOUTH, txaQuestion8);
        layout.putConstraint(SpringLayout.WEST, txaOption1Q8, 0, SpringLayout.EAST, jrbOption1Q8);
        layout.putConstraint(SpringLayout.NORTH, jrbOption2Q8, 0, SpringLayout.SOUTH, txaOption1Q8);
        layout.putConstraint(SpringLayout.NORTH, txaOption2Q8, 0, SpringLayout.SOUTH, txaOption1Q8);
        layout.putConstraint(SpringLayout.WEST, txaOption2Q8, 0, SpringLayout.EAST, jrbOption2Q8);
        layout.putConstraint(SpringLayout.NORTH, jrbOption3Q8, 0, SpringLayout.SOUTH, txaOption2Q8);
        layout.putConstraint(SpringLayout.NORTH, txaOption3Q8, 0, SpringLayout.SOUTH, txaOption2Q8);
        layout.putConstraint(SpringLayout.WEST, txaOption3Q8, 0, SpringLayout.EAST, jrbOption3Q8);
        layout.putConstraint(SpringLayout.NORTH, jrbOption4Q8, 0, SpringLayout.SOUTH, txaOption3Q8);
        layout.putConstraint(SpringLayout.NORTH, txaOption4Q8, 0, SpringLayout.SOUTH, txaOption3Q8);
        layout.putConstraint(SpringLayout.WEST, txaOption4Q8, 0, SpringLayout.EAST, jrbOption4Q8);
        //JBUTTON btnCancel
        btnCancel = new Button((GlobalVariables.TILE_SIZE * 4), ((int)(GlobalVariables.TILE_SIZE * 1.5)), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2), "Cancelar", 15f);
        //JBUTTON btnFinish
        btnFinish = new Button((GlobalVariables.TILE_SIZE * 4), ((int)(GlobalVariables.TILE_SIZE * 1.5)), GlobalVariables.TILE_SIZE, new Color(145, 9, 9), new Color(127, 7, 7), new Color(109, 5, 5), new Color(90, 2, 2), "Finalizar", 15f);
        //JSCROLLPANE scpQuestions
        scpQuestions = new JScrollPane(lblQuestions, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scpQuestions.setBorder(new EmptyBorder(0, 0, 0, 0));
        scpQuestions.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*480, GlobalVariables.ONE_PIXEL_SIZE*520));
        //JLABEL lblBase
        JLabel lblBase = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.TEST_BASE_KEY));
        SpringLayout baseLayout = new SpringLayout();
        lblBase.setLayout(baseLayout);
        lblBase.setBorder(new EmptyBorder(GlobalVariables.ONE_PIXEL_SIZE*10, GlobalVariables.ONE_PIXEL_SIZE*10, GlobalVariables.ONE_PIXEL_SIZE*10, GlobalVariables.ONE_PIXEL_SIZE*10));
        lblBase.setMinimumSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*500, GlobalVariables.ONE_PIXEL_SIZE*600));
        lblBase.setPreferredSize(new Dimension(GlobalVariables.ONE_PIXEL_SIZE*500, GlobalVariables.ONE_PIXEL_SIZE*600));
        lblBase.add(scpQuestions);
        lblBase.add(btnCancel);
        lblBase.add(btnFinish);
        baseLayout.putConstraint(SpringLayout.NORTH, btnCancel, GlobalVariables.ONE_PIXEL_SIZE*10, SpringLayout.SOUTH, scpQuestions);
        baseLayout.putConstraint(SpringLayout.NORTH, btnFinish, GlobalVariables.ONE_PIXEL_SIZE*10, SpringLayout.SOUTH, scpQuestions);
        baseLayout.putConstraint(SpringLayout.WEST, btnFinish, GlobalVariables.ONE_PIXEL_SIZE*10, SpringLayout.EAST, btnCancel);
        //JLABEL lblTest
        lblTest = new JLabel(GlobalVariables.IMAGES.get(GlobalVariables.PAUSE_SCREEN_MAP_KEY));
        lblTest.setBounds(0, 0, GlobalVariables.SCREEN_WIDTH, GlobalVariables.SCREEN_HEIGHT);
        lblTest.setLayout(new GridBagLayout());
        lblTest.add(lblBase);
        lblTest.setVisible(false);
        //THIS
        setLayout(null);
        add(lblPause);
        add(lblTest);
        add(lblDialog);
        add(lblTeleport);
        add(lblInteractive);
        add(lblPlayer);
        add(lblNpc1);
        add(lblNpc2);
        add(lblNpc3);
        add(lblNpc4);
        add(lblNpc5);
        add(lblNpc6);
        add(lblFountain);
        add(lblLeftLamp);
        add(lblRightLamp);
        add(lblFlowers);
        initializeActions();
    
        SwingUtilities.invokeLater(() -> scpQuestions.getViewport().setViewPosition( new Point(0, 0) ));
    }
    
    private void initializeActions() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                GameFrameObserver gameFrameObserver = (GameFrameObserver) getParent().getParent().getParent();
                if (!lblTest.isVisible()) {
                    if(!lblPause.isVisible()) {
                        if(!threadWalk.isAlive()) {
                            if(!lblDialog.isVisible()) {
                                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                                    controller.walkUp();
                                } else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                                    controller.walkDown();
                                } else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                                    controller.walkLeft();
                                } else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                                    controller.walkRight();
                                }
                            }
                            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                                if(teleportEnabled) {
                                    controller.teleport(gameFrameObserver);
                                } else if(interactiveEnabled) {
                                    controller.interact();
                                }
                            }
                        }
                    }
                    if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        lblPause.setVisible(true);
                    }
                }
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
                new Thread(() -> {
                    try {
                        threadWalk.join();
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    } finally {
                        updatePlayerImage(controller.getPlayerImageIdleName());
                    }
                }).start();
            }
        });
        
        btnContinue.addActionListener(click -> {
            lblPause.setVisible(false);
        });
        
        btnQuit.addActionListener(click -> {
            GameFrameObserver gameFrameObserver = (GameFrameObserver) getParent().getParent().getParent();
            gameFrameObserver.navigateToInitialScreen();
        });
        
        btnCancel.addActionListener(click -> closeTest());
        
        btnFinish.addActionListener(click -> finishTest());
    }
    
    public void closeTest() {
        btgOptionsQ1.clearSelection();
        btgOptionsQ2.clearSelection();
        btgOptionsQ3.clearSelection();
        btgOptionsQ4.clearSelection();
        btgOptionsQ5.clearSelection();
        btgOptionsQ6.clearSelection();
        btgOptionsQ7.clearSelection();
        btgOptionsQ8.clearSelection();
        scpQuestions.getViewport().setViewPosition( new Point(0, 0));
        lblTest.setVisible(false);
    }
    
    public void finishTest() {
        Object[] answers = new Object[8];
        ButtonModel bm;
        bm = btgOptionsQ1.getSelection();
        if (bm != null) {
            answers[0] = bm.getActionCommand();
        }
        bm = btgOptionsQ2.getSelection();
        if (bm != null) {
            if (bm.getActionCommand().equals("Verdadeiro")) {
                answers[1] = true;
            } else {
                answers[1] = false;
            }
        }
        bm = btgOptionsQ3.getSelection();
        if (bm != null) {
            answers[2] = bm.getActionCommand();
        }
        bm = btgOptionsQ4.getSelection();
        if (bm != null) {
            if (bm.getActionCommand().equals("Verdadeiro")) {
                answers[3] = true;
            } else {
                answers[3] = false;
            }
        }
        bm = btgOptionsQ5.getSelection();
        if (bm != null) {
            if (bm.getActionCommand().equals("Verdadeiro")) {
                answers[4] = true;
            } else {
                answers[4] = false;
            }
        }
        bm = btgOptionsQ6.getSelection();
        if (bm != null) {
            if (bm.getActionCommand().equals("Verdadeiro")) {
                answers[5] = true;
            } else {
                answers[5] = false;
            }
        }
        bm = btgOptionsQ7.getSelection();
        if (bm != null) {
            answers[6] = bm.getActionCommand();
        }
        bm = btgOptionsQ8.getSelection();
        if (bm != null) {
            answers[7] = bm.getActionCommand();
        }
        boolean[] feedback = controller.validateTest(answers);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < feedback.length; i++) {
            sb.append((i+1) + ") " + (feedback[i] ? "Correto" : "Errado"));
            if (i < feedback.length-1) {
                sb.append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        closeTest();
        GameFrameObserver gameFrameObserver = (GameFrameObserver) getParent().getParent().getParent();
        gameFrameObserver.navigateToInitialScreen();
    }

    @Override
    public void updatePlayerImage(String imageKey) {
        lblPlayer.setIcon(GlobalVariables.IMAGES.get(imageKey));
    }

    @Override
    public void updatePlayerRowUp(int row) {
        int rowLabel = lblPlayer.getY();
        int newRowLabel = (row-1) * GlobalVariables.TILE_SIZE;
        int rowsDifference = rowLabel - newRowLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < rowsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX(), lblPlayer.getY()-GlobalVariables.ONE_PIXEL_SIZE);
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(lblPlayer.getX(), newRowLabel);
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void updatePlayerRowDown(int row) {
        int rowLabel = lblPlayer.getY();
        int newRowLabel = (row-1) * GlobalVariables.TILE_SIZE;
        int rowsDifference = newRowLabel - rowLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < rowsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX(), lblPlayer.getY()+GlobalVariables.ONE_PIXEL_SIZE);
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(lblPlayer.getX(), newRowLabel);
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void updatePlayerColumnLeft(int column) {
        int columnLabel = lblPlayer.getX();
        int newColumnLabel = column * GlobalVariables.TILE_SIZE;
        int columnsDifference = columnLabel - newColumnLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < columnsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX()-GlobalVariables.ONE_PIXEL_SIZE, lblPlayer.getY());
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(newColumnLabel, lblPlayer.getY());
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void updatePlayerColumnRight(int column) {
        int columnLabel = lblPlayer.getX();
        int newColumnLabel = column * GlobalVariables.TILE_SIZE;
        int columnsDifference = newColumnLabel - columnLabel;
        updatePlayerImage(controller.getPlayerImageAnimationName());
        threadWalk = new Thread(() -> {
            try {
                for (int count = 0; count < columnsDifference; count++) {
                    lblPlayer.setLocation(lblPlayer.getX()+GlobalVariables.ONE_PIXEL_SIZE, lblPlayer.getY());
                    Thread.sleep(8);
                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lblPlayer.setLocation(newColumnLabel, lblPlayer.getY());
            }
        });
        threadWalk.start();
    }
    
    @Override
    public void enableTeleport(int row, int column) {
        lblTeleport.setBounds(GlobalVariables.TILE_SIZE*column, GlobalVariables.TILE_SIZE*row, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE);
        teleportEnabled = true;
    }
    
    @Override
    public void disableTeleport() {
        lblTeleport.setBounds(0, 0, 0, 0);
        teleportEnabled = false;
    }
    
    @Override
    public void enableInteractive(int row, int column) {
        lblInteractive.setBounds(GlobalVariables.TILE_SIZE*column, GlobalVariables.TILE_SIZE*row, GlobalVariables.TILE_SIZE, GlobalVariables.TILE_SIZE);
        interactiveEnabled = true;
    }
    
    @Override
    public void disableInteractive() {
        lblInteractive.setBounds(0, 0, 0, 0);
        interactiveEnabled = false;
    }
    
    @Override
    public void openTest() {
        lblTest.setVisible(true);
    }
    
    @Override
    public void openDialog() {
        int row = 1;
        if (lblPlayer.getY()/GlobalVariables.TILE_SIZE < 10) {
            row = 17;
        }
        lblDialog.setBounds(GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE*row, GlobalVariables.TILE_SIZE*13, GlobalVariables.TILE_SIZE*3);
        lblDialog.setVisible(true);
    }
    
    @Override
    public void updateDialogMessage(String message) {
        txaDialogText.setText(message);
        if (!message.isEmpty()) {
            lblDialog.setText(message);
        } else {
            if (Repository.getInstance().isFirstEnterInVillage()) {
                Repository.getInstance().disableFirstEnterInVillage();
            }
            lblDialog.setVisible(false);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(GlobalVariables.IMAGES.get(GlobalVariables.VILLAGE_SCREEN_MAP_KEY).getImage(), 0, 0, null);
    }
}
