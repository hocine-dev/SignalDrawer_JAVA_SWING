import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame {

    private JPanel panel2; // Panel pour le tracé
    private String binarySequence = ""; // Chaîne binaire saisie par l'utilisateur
    private String selectedEncoding = "NRZ"; // Type de codage sélectionné

    public FenetrePrincipale() {
        super("Interface : Tracer le signal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        pack();
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        // Panneau supérieur : Entrée utilisateur
        JPanel panel = new JPanel(new BorderLayout()); // Utiliser BorderLayout pour une organisation plus flexible
        panel.setBackground(Color.LIGHT_GRAY);

// Conteneur central pour les entrées utilisateur
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centre les composants
        JLabel label = new JLabel("Votre suite binaire ?");
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Police stylisée pour le texte
        JTextField text = new JTextField(10);
        String[] type = {"NRZ", "NRZI", "manshester", "manchester différentiel", "miller"};
        JComboBox<String> combo = new JComboBox<>(type);
        JButton button = new JButton("Valider");

// Ajout des composants au panneau central
        inputPanel.add(label);
        inputPanel.add(text);
        inputPanel.add(combo);
        inputPanel.add(button);

// Style visuel : ajout d'une bordure avec espacement
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marges internes

// Ajout du panneau central au panneau supérieur
        inputPanel.setBackground(Color.LIGHT_GRAY);
        panel.add(inputPanel, BorderLayout.CENTER);


// Ajout du panneau supérieur à la fenêtre
        add(panel, BorderLayout.NORTH);


        // Panneau central : Tracé du signal
        panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g); // Dessiner la grille
                drawSignal(g); // Dessiner le signal
            }
        };

        panel2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2), // Bordure extérieure noire de 2 pixels
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1), // Bordure intermédiaire grise de 1 pixel
                        BorderFactory.createEmptyBorder(10, 10, 10, 10) // Bordure intérieure vide de 10 pixels
                )
        ));


        add(panel2, BorderLayout.CENTER);


        // Créer le JLabel avec un texte stylé
        JLabel label3 = new JLabel("Réalisé par Hocine HAMAMA");
        label3.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte horizontalement
        label3.setFont(new Font("Arial", Font.BOLD, 14)); // Définit une police en gras, taille 14
        label3.setForeground(Color.DARK_GRAY); // Couleur du texte

// Créer un panneau pour le footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout()); // Utilise BorderLayout pour faciliter le centrage
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Ajoute une marge intérieure
        footerPanel.setBackground(Color.LIGHT_GRAY); // Couleur de fond du footer

// Ajouter le JLabel au panneau
        footerPanel.add(label3, BorderLayout.CENTER);

// Ajouter le panneau footer à la fenêtre
        add(footerPanel, BorderLayout.SOUTH);


        // Créer des panneaux vides pour WEST et EAST
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();

// Ajouter une bordure vide pour créer un espace intérieur
        panelWest.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Marge à gauche
        panelEast.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Marge à droite

// Définir une couleur de fond pour visualiser (facultatif, peut être retiré)
        panelWest.setBackground(Color.LIGHT_GRAY);
        panelEast.setBackground(Color.LIGHT_GRAY);

// Ajouter les panneaux à la fenêtre
        add(panelWest, BorderLayout.WEST); // Ajout au WEST
        add(panelEast, BorderLayout.EAST); // Ajout au EAST


        // Action du bouton "Valider"
        button.addActionListener(e -> {
            binarySequence = text.getText(); // Récupérer la chaîne binaire
            selectedEncoding = (String) combo.getSelectedItem(); // Récupérer le codage
            panel2.repaint(); // Redessiner panel2
        });
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int width = panel2.getWidth();
        int height = panel2.getHeight();

        // Position des niveaux de tension
        int yZero = height / 2; // Niveau 0V
        int yPlusNV = yZero - 50; // Niveau +nV
        int yMinusNV = yZero + 50; // Niveau -nV

        // Définir le style des lignes pointillées
        float[] dashPattern = {5, 5}; // Longueur des segments et des espaces (pixels)
        BasicStroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0);

        // Appliquer le style des lignes pointillées
        g2d.setStroke(dashedStroke);
        g2d.setColor(Color.BLACK);

        // Dessiner les lignes horizontales pointillées pour les niveaux de tension
        g2d.drawLine(0, yZero, width, yZero); // Ligne 0V
        g2d.drawLine(0, yPlusNV, width, yPlusNV); // Ligne +nV
        g2d.drawLine(0, yMinusNV, width, yMinusNV); // Ligne -nV

        // Dessiner les labels à gauche des lignes
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString("+nV", 10, yPlusNV - 5); // Label pour +nV
        g2d.drawString("0V", 10, yZero - 5); // Label pour 0V
        g2d.drawString("-nV", 10, yMinusNV - 5); // Label pour -nV

        // Couleur des lignes de la grille (gris clair)
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(1)); // Lignes fines pour la grille

        // Dessiner les lignes verticales pour séparer les bits
        for (int x = 50; x < width; x += 50) {
            g2d.drawLine(x, 0, x, height);
        }
    }


    private void drawSignal(Graphics g) {
        if (binarySequence.isEmpty()) {
            return; // Ne rien dessiner si la chaîne est vide
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3)); // Lignes épaisses pour le signal
        g2d.setColor(Color.BLACK);

        int panelHeight = panel2.getHeight();
        int panelWidth = panel2.getWidth();

        // Définir les niveaux de tension
        int yZero = panelHeight / 2; // Niveau 0V
        int yPlus5 = yZero - 50; // Niveau +5V
        int yMinus5 = yZero + 50; // Niveau -5V

        int x = 50; // Position initiale en x
        int step = 50; // Largeur de chaque bit
        int currentLevel = yZero; // Niveau initial pour NRZI et autres

        boolean previousBit = false; // Utilisé pour certains codages (NRZI, Manchester différentiel)

        for (int i = 0; i < binarySequence.length(); i++) {
            char bit = binarySequence.charAt(i);
            int nextLevel = currentLevel;

            switch (selectedEncoding) {
                case "NRZ":
                    if (bit == '1') {
                        nextLevel = yPlus5; // Niveau haut pour "1"
                    } else {
                        nextLevel = yMinus5; // Niveau bas pour "0"
                    }

                    // Si le niveau change, dessiner une ligne verticale
                    if (i > 0 && nextLevel != currentLevel) {
                        g2d.drawLine(x, currentLevel, x, nextLevel); // Ligne verticale
                    }

                    // Dessiner la ligne horizontale pour le bit
                    g2d.drawLine(x, nextLevel, x + step, nextLevel);

                    currentLevel = nextLevel; // Mettre à jour le niveau actuel
                    break;


                case "NRZI":
                    if (bit == '0') {
                        nextLevel = yPlus5; // Niveau haut pour "1"
                    } else {
                        nextLevel = yMinus5; // Niveau bas pour "0"
                    }

                    // Si le niveau change, dessiner une ligne verticale
                    if (i > 0 && nextLevel != currentLevel) {
                        g2d.drawLine(x, currentLevel, x, nextLevel); // Ligne verticale
                    }

                    // Dessiner la ligne horizontale pour le bit
                    g2d.drawLine(x, nextLevel, x + step, nextLevel);

                    currentLevel = nextLevel; // Mettre à jour le niveau actuel
                    break;

                    case "manshester":
                    if (bit == '1') {
                        // 1 : Transition de +nV à -nV
                        g2d.drawLine(x, yPlus5, x + step / 2, yPlus5); // Ligne horizontale haute
                        g2d.drawLine(x + step / 2, yPlus5, x + step / 2, yMinus5); // Transition verticale descendante
                        g2d.drawLine(x + step / 2, yMinus5, x + step, yMinus5); // Ligne horizontale basse
                    } else {
                        // 0 : Transition de -nV à +nV
                        g2d.drawLine(x, yMinus5, x + step / 2, yMinus5); // Ligne horizontale basse
                        g2d.drawLine(x + step / 2, yMinus5, x + step / 2, yPlus5); // Transition verticale montante
                        g2d.drawLine(x + step / 2, yPlus5, x + step, yPlus5); // Ligne horizontale haute
                    }
                    break;




                case "miller":
                    if (bit == '1') {
                        // Transition au milieu pour "1"
                        g2d.drawLine(x, currentLevel, x + step / 2, currentLevel); // Ligne horizontale
                        nextLevel = (currentLevel == yZero) ? yPlus5 : yZero; // Transition de niveau
                        g2d.drawLine(x + step / 2, currentLevel, x + step / 2, nextLevel); // Ligne verticale
                        g2d.drawLine(x + step / 2, nextLevel, x + step, nextLevel); // Ligne horizontale
                    } else {
                        // Pas de transition pour "0"
                        g2d.drawLine(x, currentLevel, x + step, currentLevel); // Ligne horizontale
                    }
                    currentLevel = nextLevel; // Mettre à jour le niveau actuel
                    break;

                case "manchester différentiel":
                    if (bit == '1') {
                        previousBit = !previousBit; // Inversion si "1"
                    }
                    nextLevel = previousBit ? yPlus5 : yMinus5;
                    g2d.drawLine(x, currentLevel, x, nextLevel); // Ligne verticale si nécessaire
                    g2d.drawLine(x, nextLevel, x + step / 2, nextLevel); // Ligne horizontale
                    g2d.drawLine(x + step / 2, nextLevel, x + step / 2, yZero); // Ligne centrale
                    g2d.drawLine(x + step / 2, yZero, x + step, yZero); // Ligne horizontale finale
                    currentLevel = nextLevel;
                    break;
            }

            x += step; // Passer au bit suivant
        }
    }


}
