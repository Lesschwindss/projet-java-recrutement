package view;

import dao.CandidatDAO;
import model.Candidat;

import javax.swing.*;
import java.awt.*;

public class ProfilCandidatView extends JPanel {

    public ProfilCandidatView(int idCandidat) {
        setLayout(new BorderLayout(10, 10));

        // Titre
        JLabel titre = new JLabel("Profil du Candidat", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        add(titre, BorderLayout.NORTH);

        // Récupérer le candidat
        Candidat candidat = CandidatDAO.getById(idCandidat);

        // Panel des infos
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        if (candidat != null) {
            infoPanel.add(new JLabel("Nom :"));
            infoPanel.add(new JLabel(candidat.getNom()));

            infoPanel.add(new JLabel("Email :"));
            infoPanel.add(new JLabel(candidat.getEmail()));

            infoPanel.add(new JLabel("Compétences :"));
            infoPanel.add(new JLabel(candidat.getCompetences()));

            infoPanel.add(new JLabel("Expérience :"));
            infoPanel.add(new JLabel(candidat.getExperience()));

            infoPanel.add(new JLabel("Sexe :"));
            infoPanel.add(new JLabel(candidat.getSexe() ? "Homme" : "Femme"));

            infoPanel.add(new JLabel("Région :"));
            infoPanel.add(new JLabel(candidat.getRegion()));

            infoPanel.add(new JLabel("Tranche d'âge :"));
            infoPanel.add(new JLabel(candidat.getTrancheAge()));
        } else {
            infoPanel.add(new JLabel("Erreur :"));
            infoPanel.add(new JLabel("Candidat non trouvé."));
        }

        add(infoPanel, BorderLayout.CENTER);

        // Bouton retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new CandidaturesView());
            frame.revalidate();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnRetour);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
